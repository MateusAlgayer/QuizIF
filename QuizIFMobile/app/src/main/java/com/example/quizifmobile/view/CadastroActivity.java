package com.example.quizifmobile.view;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.quizifmobile.R;

public class CadastroActivity extends AppCompatActivity {

    EditText etEmailUsuCad, etNomeUsuCad, etApelidoUsuCad;
    Button btCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btCadastrar = findViewById(R.id.btCadastrar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etEmailUsuCad.getText() != null){
                    if (etNomeUsuCad.getText() != null){
                        if (etApelidoUsuCad.getText() != null){
                            Intent enviaEmail = new Intent(CadastroActivity.this, ConfirmaCodigoEmailActivity.class);
                            startActivity(enviaEmail);


                        }
                    }
                }
            }
        });
    }

}
