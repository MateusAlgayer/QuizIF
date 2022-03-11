package com.example.quizifmobile.view;

import android.content.Intent;
import android.os.Bundle;

import com.example.quizifmobile.controller.InfoApp;
import com.example.quizifmobile.util.Metodos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.quizifmobile.R;

import ModelDominio.Usuario;

public class CadastroActivity extends AppCompatActivity {

    EditText etEmailUsuCad, etNomeUsuCad, etApelidoUsuCad;
    Button btCadastrar;

    InfoApp infoApp;

    //constantes
    final int TELACADASTRO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btCadastrar = findViewById(R.id.btCadastrar);

        etNomeUsuCad = findViewById(R.id.etNomeUsuCad);
        etEmailUsuCad = findViewById(R.id.etEmailUsuCad);
        etApelidoUsuCad = findViewById(R.id.etApelidoUsuCad);

        infoApp = (InfoApp) getApplicationContext();

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validaEmail()){
                    if (!etNomeUsuCad.getText().toString().isEmpty()){
                        if (!etApelidoUsuCad.getText().toString().isEmpty()){
                            Intent enviaEmail = new Intent(CadastroActivity.this, ConfirmaCodigoEmailActivity.class);

                            infoApp.setUsuCad(new Usuario(etNomeUsuCad.getText().toString(), etApelidoUsuCad.getText().toString(), etEmailUsuCad.getText().toString(), "", ""));
                            enviaEmail.putExtra("TelaPai", TELACADASTRO);

                            startActivity(enviaEmail);
                        }
                    }
                }
            }
        });
    }

    private boolean validaEmail(){
        String recNome = Metodos.pedaco(etEmailUsuCad.getText().toString(),"@", 1);
        String dominio = Metodos.pedaco(etEmailUsuCad.getText().toString(),"@", 2);

        //receptor é vazio
        if(recNome.equals("")){
            return false;
        }

        //dominio é vazio
        if(dominio.equals("")){
            return false;
        }

        int i = 0;
        int idx = 0;

        //conta o número de vezes que o . aparece no dominio
        while(idx != -1){
            idx = dominio.indexOf(".", idx+1);

            if(idx != -1)
                i++;
        }

        //se i é 0 então não tem subdominio
        if(i == 0){
            return false;
        }

        //ve se algo entre os pontos está vazio, se estiver o email é inválido
        for (int x = 1; x <= i+1; x++) {
            if(Metodos.pedaco(dominio,".", x).equals("")){
                return false;
            }
        }
        return true;
    }
}
