package com.example.quizifmobile.view;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.quizifmobile.R;

public class LoginActivity extends AppCompatActivity {

    EditText etSenhaUsu, etEmailUsu;

    Button btCadUsu, btRecuperarSenha, btLogUsu;

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
        
        btLogUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(LoginActivity.this, PrincipalActivity.class);
                startActivity(it);
            }
        });
    }

}
