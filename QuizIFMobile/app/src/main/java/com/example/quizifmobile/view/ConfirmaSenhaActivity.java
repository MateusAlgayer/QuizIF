package com.example.quizifmobile.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.quizifmobile.controller.ConexaoController;
import com.example.quizifmobile.controller.InfoApp;
import com.example.quizifmobile.util.CriptoHash;
import com.example.quizifmobile.util.Metodos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.quizifmobile.R;

public class ConfirmaSenhaActivity extends AppCompatActivity {

    TextView tvSenhaAntigaUsu, tvLabelSenha, tvSenhaIgual, tvSenhaMaisc, tvSenhaMinusc, tvSenhaCaracEspec, tvSenhaDigito, tvSenhaTamanho;

    EditText etSenhaAntigaUsu, etSenhaUsu, etConfirmaSenhaUsu;

    Button btMudarSenhaUsu, btCadastrarUsu;

    InfoApp infoApp;
    int telaPai;
    String sal;
    String email;
    ConexaoController ccont;

    //constantes
    final int REDEFSENHA = 1;
    final int CADSENHA   = 2;
    final int ALTSENHA   = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirma_senha);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        tvSenhaAntigaUsu  = findViewById(R.id.tvSenhaAntigaUsu);
        tvLabelSenha      = findViewById(R.id.tvLabelSenha);
        tvSenhaIgual      = findViewById(R.id.tvSenhaIgual);
        tvSenhaMaisc      = findViewById(R.id.tvSenhaMaisc);
        tvSenhaMinusc     = findViewById(R.id.tvSenhaMinusc);
        tvSenhaCaracEspec = findViewById(R.id.tvSenhaCaracEspec);
        tvSenhaDigito     = findViewById(R.id.tvSenhaDigito);
        tvSenhaTamanho    = findViewById(R.id.tvSenhaTamanho);

        etSenhaAntigaUsu   = findViewById(R.id.etSenhaAntigaUsu);
        etSenhaUsu         = findViewById(R.id.etSenhaUsu);
        etConfirmaSenhaUsu = findViewById(R.id.etConfirmaSenhaUsu);

        btMudarSenhaUsu = findViewById(R.id.btMudarSenhaUsu);
        btCadastrarUsu  = findViewById(R.id.btCadastrarUsu);

        infoApp = (InfoApp) getApplicationContext();

        Intent it = getIntent();

        if(it != null){

            telaPai = it.getIntExtra("TelaPai", 0);

            if(telaPai == REDEFSENHA) {
                email = it.getStringExtra("Email");
                sal = it.getStringExtra("sal");
            }
            else {
                email = "";
                sal = "";
            }

            if(telaPai == ALTSENHA){
                tvSenhaAntigaUsu.setVisibility(View.VISIBLE);
                tvLabelSenha.setText("Nova senha:");
                etSenhaAntigaUsu.setVisibility(View.VISIBLE);
                btMudarSenhaUsu.setVisibility(View.VISIBLE);
                btCadastrarUsu.setVisibility(View.INVISIBLE);
            } else {
                tvSenhaAntigaUsu.setVisibility(View.INVISIBLE);
                tvLabelSenha.setText("Senha:");
                etSenhaAntigaUsu.setVisibility(View.INVISIBLE);
                btMudarSenhaUsu.setVisibility(View.INVISIBLE);
                btCadastrarUsu.setVisibility(View.VISIBLE);
            }

            ccont = new ConexaoController(infoApp.in, infoApp.out);

        } else {
            Metodos.mensagem(infoApp, "Erro ao mostrar a tela de cadastro/alteração de senha");
            finish();
        }

        //faz a ação acontecer sempre que a senha mudar
        etSenhaUsu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                consistSenha();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etConfirmaSenhaUsu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                consistSenha();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btMudarSenhaUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String antigaSenha = CriptoHash.Cripto(etSenhaAntigaUsu.getText().toString(), infoApp.getGUsuLogado().getSal(), 0);

                if(antigaSenha.equals(infoApp.getGUsuLogado().getSenha())){
                    if(consistSenha()){
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                final String senhaCripto = CriptoHash.Cripto(etSenhaUsu.getText().toString(), infoApp.getGUsuLogado().getSal(), 0);

                                final String status = ccont.alteraSenhaUsu(infoApp.getGUsuLogado().getEmail(), senhaCripto);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if(Metodos.processaMsgServidor(ConfirmaSenhaActivity.this, status, "Sucesso ao alterar a senha!", "Erro na alteração de senha:")) {
                                            infoApp.getGUsuLogado().setSenha(senhaCripto);

                                            Intent it = new Intent(ConfirmaSenhaActivity.this, PerfilActivity.class);
                                            startActivity(it);
                                        }
                                    }
                                });
                            }
                        }).start();

                    } else {
                        Metodos.mensagem(infoApp, "Reveja a política de senha!");
                    }
                } else {
                    Metodos.mensagem(infoApp, "Senha antiga incorreta!");
                }
            }
        });

        btCadastrarUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(consistSenha()){
                    if(telaPai == CADSENHA){
                        cadUsu();
                    } else {
                        redefSenha();
                    }
                } else {
                    Metodos.mensagem(infoApp, "Reveja a política de senha!");
                }
            }
        });
    }

    private void cadUsu() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String senhaCripto = CriptoHash.Cripto(etSenhaUsu.getText().toString(), infoApp.getUsuCad().getSal(), 0);
                infoApp.getUsuCad().setSenha(senhaCripto);

                final String status = ccont.cadastraUsu(infoApp.getUsuCad());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(Metodos.processaMsgServidor(ConfirmaSenhaActivity.this, status, "Sucesso ao cadastrar usuário!", "Erro ao cadastrar usuário:")){
                            Intent it = new Intent(ConfirmaSenhaActivity.this, LoginActivity.class);
                            startActivity(it);
                        }
                    }
                });
            }
        }).start();
    }

    private void redefSenha(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String senhaCripto = CriptoHash.Cripto(etSenhaUsu.getText().toString(), sal, 0);

                final String status = ccont.alteraSenhaUsu(email, senhaCripto);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(Metodos.processaMsgServidor(ConfirmaSenhaActivity.this, status, "Sucesso ao alterar a senha!", "Erro ao alterar a senha!")){
                            Intent it = new Intent(ConfirmaSenhaActivity.this, LoginActivity.class);
                            startActivity(it);
                        }
                    }
                });
            }
        }).start();
    }

    public boolean consistSenha() {
        String senha = etSenhaUsu.getText().toString();

        String confirmaSenha = etConfirmaSenhaUsu.getText().toString();

        boolean validSenha = true;

        if(senha.equals("")){
            tvSenhaIgual.setTextColor(getResources().getColor(R.color.red));
            tvSenhaMinusc.setTextColor(getResources().getColor(R.color.red));
            tvSenhaMaisc.setTextColor(getResources().getColor(R.color.red));
            tvSenhaDigito.setTextColor(getResources().getColor(R.color.red));
            tvSenhaCaracEspec.setTextColor(getResources().getColor(R.color.red));
            tvSenhaTamanho.setTextColor(getResources().getColor(R.color.red));
            return false;
        }

        if(senha.equals(confirmaSenha)){
            tvSenhaIgual.setTextColor(getResources().getColor(R.color.green));
        } else{
            tvSenhaIgual.setTextColor(getResources().getColor(R.color.red));
            validSenha = false;
        }

        if(!senha.equals(senha.toUpperCase())){
            tvSenhaMinusc.setTextColor(getResources().getColor(R.color.green));
        } else{
            tvSenhaMinusc.setTextColor(getResources().getColor(R.color.red));
            validSenha = false;
        }

        if(!senha.equals(senha.toLowerCase())){
            tvSenhaMaisc.setTextColor(getResources().getColor(R.color.green));
        } else{
            tvSenhaMaisc.setTextColor(getResources().getColor(R.color.red));
            validSenha = false;
        }

        if(senha.matches("(.*\\d.*)")){
            tvSenhaDigito.setTextColor(getResources().getColor(R.color.green));
        } else{
            tvSenhaDigito.setTextColor(getResources().getColor(R.color.red));
            validSenha = false;
        }

        if(senha.matches(".*[^A-Za-z0-9 ].*")){
            tvSenhaCaracEspec.setTextColor(getResources().getColor(R.color.green));
        } else{
            tvSenhaCaracEspec.setTextColor(getResources().getColor(R.color.red));
            validSenha = false;
        }

        if(senha.length() >= 8 && senha.length() <= 20){
            tvSenhaTamanho.setTextColor(getResources().getColor(R.color.green));
        } else {
            tvSenhaTamanho.setTextColor(getResources().getColor(R.color.red));
            validSenha = false;
        }

        return validSenha;
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

        AlertDialog.Builder dialogSair = new AlertDialog.Builder(ConfirmaSenhaActivity.this);
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
                            case REDEFSENHA: {
                                status = "S^redefOk";//ccont.respondeDelUsu("Cancelar", "");
                                break;
                            }
                            case CADSENHA: {
                                status = "S^cadOk";//ccont.respondeRedefSenhaUsu("Cancelar", "");
                                break;
                            }
                            case ALTSENHA: {
                                status = "S^altOk";
                                break;
                            }
                            default: status = "E^Não foi possível encontrar a tela pai, reinicie a aplicação!";
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(Metodos.processaMsgServidor(ConfirmaSenhaActivity.this, status, "Processo de alteração de senha cancelado!", "Erro ao cancelar o processo!")){
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
            case REDEFSENHA: return new Intent(ConfirmaSenhaActivity.this, LoginActivity.class);
            case CADSENHA:return new Intent(ConfirmaSenhaActivity.this, CadastroActivity.class);
            case ALTSENHA: return new Intent(ConfirmaSenhaActivity.this, PerfilActivity.class);
            default:{
                finish();
                return null;
            }
        }
    }
}
