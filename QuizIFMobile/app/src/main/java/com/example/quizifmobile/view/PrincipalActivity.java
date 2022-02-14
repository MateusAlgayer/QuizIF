package com.example.quizifmobile.view;

import android.content.Intent;
import android.os.Bundle;

import com.example.quizifmobile.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class PrincipalActivity extends AppCompatActivity {

    Button btAcessoPerfil, btAcessoRanking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
        btAcessoPerfil = findViewById(R.id.btAcessoPerfil);
        btAcessoRanking = findViewById(R.id.btAcessoRanking);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btAcessoRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent acessoRanking = new Intent(PrincipalActivity.this, RankingActivity.class);

                startActivity(acessoRanking);
            }
        });
    }

}
