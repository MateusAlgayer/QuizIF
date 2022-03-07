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
    final int TELAPERFIL = 1;
    final int TELACADASTRO = 2;
    final int TELALOGIN = 3;

    int telaPai;
    String sal;
    ConexaoController ccont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirma_codigo_email);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        infoApp = (InfoApp) getApplicationContext();
        etCodigoEmail = findViewById(R.id.etCodigoEmail);
        btConfirmaCod = findViewById(R.id.btConfirmaCod);

        ccont = new ConexaoController(infoApp.in, infoApp.out);

        Intent it = getIntent();

        telaPai = it.getIntExtra("TelaPai", TELALOGIN);
        final String acao = it.getStringExtra("Acao");

        new Thread(new Runnable() {
            @Override
            public void run() {




                switch (telaPai){
                    case TELAPERFIL: {
                        sal = ccont.enviaDelUsu(infoApp.getGUsuLogado().getEmail());
                        break;
                    }

                    //case TELACADASTRO: {
                    //cadUsu();
                    //break;
                    //}

                    //case TELALOGIN: {
                    //redefSenhaUsu();
                    //break;
                    //}
                }

                btConfirmaCod.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switch (telaPai){
                            case TELAPERFIL: {
                                ccont.respondeDelUsu(etCodigoEmail.getText().toString(), sal);
                                Metodos.mensagem(infoApp,"Código confirmado! O usuário será deletado!");
                            }
                        }
                    }
                });
            }
        });


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
                Intent it = retIntentPai();

                switch (telaPai){
                    case TELAPERFIL: ccont.respondeDelUsu("Cancelar", "");
                }

                startActivity(it);
                finish();
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

