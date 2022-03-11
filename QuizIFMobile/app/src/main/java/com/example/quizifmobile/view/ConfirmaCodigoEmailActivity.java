package com.example.quizifmobile.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.quizifmobile.R;
import com.example.quizifmobile.controller.ConexaoController;
import com.example.quizifmobile.controller.InfoApp;
import com.example.quizifmobile.util.Metodos;


public class ConfirmaCodigoEmailActivity extends AppCompatActivity {

    EditText etCodigoEmail;
    Button btConfirmaCod;

    InfoApp infoApp;

    //constantes
    final int TELAPERFIL   = 1;
    final int TELACADASTRO = 2;
    final int TELALOGIN    = 3;
    final int REDEFSENHA   = 1;
    final int CADSENHA     = 2;

    int telaPai;
    String sal;
    String email;
    ConexaoController ccont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirma_codigo_email);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        infoApp       = (InfoApp) getApplicationContext();
        etCodigoEmail = findViewById(R.id.etCodigoEmail);
        btConfirmaCod = findViewById(R.id.btConfirmaCod);

        ccont = new ConexaoController(infoApp.in, infoApp.out);

        final Intent it = getIntent();

        telaPai = it.getIntExtra("TelaPai", TELALOGIN);
        email = "";

        new Thread(new Runnable() {
            @Override
            public void run() {

                switch (telaPai){
                    case TELAPERFIL: {
                        sal = ccont.enviaDelUsu(infoApp.getGUsuLogado().getEmail());
                        break;
                    }

                    case TELACADASTRO: {
                        sal = ccont.enviaCodigoEmail(infoApp.getUsuCad().getEmail());

                        //nesse caso "dhasieuqwehqhduirwuqiqhirfhquedq" continua e E^erro ou A^aviso caem no else
                        if(Metodos.pedaco(sal, "^", 1).isEmpty()){
                            //continua
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Metodos.mensagem(ConfirmaCodigoEmailActivity.this, Metodos.pedaco(sal, "^",2));
                                    finish();
                                }
                            });
                        }

                        break;
                    }

                    case TELALOGIN: {
                        email = it.getStringExtra("Email");
                        sal = ccont.redefSenhaUsu(email);

                        //nesse caso "dhasieuqwehqhduirwuqiqhirfhquedq" continua e E^erro ou A^aviso saem dessa função
                        if(Metodos.pedaco(sal, "^", 1).isEmpty()){
                            //continua
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Metodos.mensagem(ConfirmaCodigoEmailActivity.this, Metodos.pedaco(sal, "^",2));
                                    finish();
                                }
                            });
                        }

                        break;
                    }
                }

            }
        }).start();

        btConfirmaCod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        switch (telaPai){
                            case TELAPERFIL: {
                                final String status = ccont.respondeDelUsu(etCodigoEmail.getText().toString(), sal);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (Metodos.processaMsgServidor(ConfirmaCodigoEmailActivity.this, status, "", "Erro ao deletar:")){
                                            DeletaUsu();
                                        } else {
                                            Intent it = new Intent(ConfirmaCodigoEmailActivity.this, PerfilActivity.class);
                                            startActivity(it);
                                        }
                                    }
                                });
                            }
                            case TELALOGIN: {
                                final String status = ccont.respondeRedefSenhaUsu(etCodigoEmail.getText().toString(), sal);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (Metodos.processaMsgServidor(ConfirmaCodigoEmailActivity.this, status, "", "Erro ao redefinir:")){
                                            redefineSenha();
                                        } else {
                                            Intent it = new Intent(ConfirmaCodigoEmailActivity.this, LoginActivity.class);
                                            startActivity(it);
                                        }
                                    }
                                });
                            }
                            case TELACADASTRO: {
                                final String status = ccont.respondeCadUsu(etCodigoEmail.getText().toString(), sal);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (Metodos.processaMsgServidor(ConfirmaCodigoEmailActivity.this, status, "", "Erro ao cadastrar:")){
                                            cadastraUsu();
                                        } else {
                                            Intent it = new Intent(ConfirmaCodigoEmailActivity.this, CadastroActivity.class);
                                            startActivity(it);
                                        }
                                    }
                                });
                            }
                        }
                    }
                }).start();
            }
        });
    }

    private void cadastraUsu() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Intent it = new Intent(ConfirmaCodigoEmailActivity.this, ConfirmaSenhaActivity.class);

                infoApp.getUsuCad().setSal(sal);

                it.putExtra("TelaPai", CADSENHA);

                startActivity(it);
            }
        }).start();
    }

    private void DeletaUsu() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String status = ccont.deletaUsu(infoApp.getGUsuLogado());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (Metodos.processaMsgServidor(ConfirmaCodigoEmailActivity.this, status, "Usuário deletado com sucesso!", "Erro ao deletar:")){
                            Intent it = new Intent(ConfirmaCodigoEmailActivity.this, MainActivity.class);
                            startActivity(it);
                            finish();
                        } else {
                            Intent it = new Intent(ConfirmaCodigoEmailActivity.this, PerfilActivity.class);
                            startActivity(it);
                        }
                    }
                });
            }
        }).start();
    }

    private void redefineSenha(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Intent it = new Intent(ConfirmaCodigoEmailActivity.this, ConfirmaSenhaActivity.class);

                it.putExtra("TelaPai", REDEFSENHA);
                it.putExtra("Email", email);
                it.putExtra("sal", sal);

                startActivity(it);
            }
        }).start();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            onBackPressed();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder dialogSair = new AlertDialog.Builder(ConfirmaCodigoEmailActivity.this);
        dialogSair.setMessage("Tem certeza que deseja sair?\n o progresso será perdido");
        dialogSair.setCancelable(true);

        dialogSair.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final Intent it = retIntentPai();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final String status;

                        switch (telaPai) {
                            case TELAPERFIL: {
                                status = ccont.respondeDelUsu("Cancelar", "");
                                break;
                            }
                            case TELALOGIN: {
                                status = ccont.respondeRedefSenhaUsu("Cancelar", "");
                                break;
                            }
                            case TELACADASTRO: {
                                status = ccont.respondeCadUsu("Cancelar", "");
                                break;
                            }
                            default: status = "E^Não foi possível encontrar a tela pai, reinicie a aplicação!";
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(Metodos.processaMsgServidor(ConfirmaCodigoEmailActivity.this, status, "", "Erro ao cancelar a validação de código!")){
                                    startActivity(it);
                                }
                            }
                        });
                    }
                }).start();
            }
        });

        dialogSair.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertSair = dialogSair.create();
        alertSair.show();
    }

    private Intent retIntentPai() {
        switch(telaPai){
            case TELAPERFIL: return new Intent(ConfirmaCodigoEmailActivity.this, PerfilActivity.class);
            case TELACADASTRO:return new Intent(ConfirmaCodigoEmailActivity.this, CadastroActivity.class);
            default: return new Intent(ConfirmaCodigoEmailActivity.this, LoginActivity.class);
        }
    }
}

