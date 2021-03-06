package com.example.quizifmobile.view;

import android.content.Intent;
import android.os.Bundle;

import com.example.quizifmobile.controller.ConexaoController;
import com.example.quizifmobile.controller.InfoApp;
import com.example.quizifmobile.util.Metodos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.quizifmobile.R;

import ModelDominio.Usuario;

public class LoginActivity extends AppCompatActivity {

    EditText etSenhaUsu, etEmailUsu;

    Button btCadUsu, btRecuperarSenha, btLogUsu;

    InfoApp infoApp;

    final int TELALOGIN = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        etSenhaUsu = findViewById(R.id.etSenhaUsu);
        etEmailUsu = findViewById(R.id.etEmailUsu);
        btCadUsu = findViewById(R.id.btCadUsu);
        btRecuperarSenha = findViewById(R.id.btRecuperarSenha);
        btLogUsu = findViewById(R.id.btLogUsu);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        infoApp = (InfoApp) getApplicationContext();
        
        btLogUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Usuario wLogin = new Usuario(etEmailUsu.getText().toString(), etSenhaUsu.getText().toString());

                        ConexaoController ccont = new ConexaoController(infoApp.in, infoApp.out);

                        Usuario wUsu = ccont.login(wLogin);

                        if (wUsu != null) {
                            infoApp.setGUsuLogado(wUsu);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Intent login = new Intent(LoginActivity.this, PrincipalActivity.class);
                                    startActivity(login);
                                }
                            });
                        } else {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Metodos.mensagem(infoApp, "Usu??rio ou senha incorretos!");
                                }
                            });
                        }

                    }
                }).start();

            }
        });

        btCadUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cadastroUsu = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(cadastroUsu);
            }
        });

        btRecuperarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etEmailUsu.getText().toString().equals("")){
                    Metodos.mensagem(LoginActivity.this, "Email n??o informado, n??o ?? poss??vel recuperar senha!");
                }

                Intent it = new Intent(LoginActivity.this, ConfirmaCodigoEmailActivity.class);

                it.putExtra("TelaPai", TELALOGIN);
                it.putExtra("Email", etEmailUsu.getText().toString());

                startActivity(it);
            }
        });
    }

}
