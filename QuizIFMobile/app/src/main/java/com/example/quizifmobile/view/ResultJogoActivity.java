package com.example.quizifmobile.view;

import android.os.Bundle;

import com.example.quizifmobile.adapters.ProvaAdapter;
import com.example.quizifmobile.adapters.ResultadoAdapter;
import com.example.quizifmobile.controller.InfoApp;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizifmobile.R;

import ModelDominio.Pergunta;
import ModelDominio.Prova;

public class ResultJogoActivity extends AppCompatActivity {

    InfoApp infoApp;

    RecyclerView rvResultado;
    Button btSalvarResultado;
    TextView tvProvaResult, tvDificuldadeResult, tvRespondidasResult, tvAcertosResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_jogo);

        rvResultado = findViewById(R.id.rvResultado);

        btSalvarResultado = findViewById(R.id.btSalvarResultado);

        tvProvaResult = findViewById(R.id.tvProvaResult);
        tvDificuldadeResult = findViewById(R.id.tvDificuldadeResult);
        tvRespondidasResult = findViewById(R.id.tvRespondidasResult);
        tvAcertosResult = findViewById(R.id.tvAcertosResult);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        infoApp = (InfoApp) getApplication();

        Prova p = infoApp.getProvaAtual();

        tvProvaResult.setText(p.getNomeProva());
        tvDificuldadeResult.setText(p.getDificuldadeLiteral());
        tvRespondidasResult.setText(Integer.toString(infoApp.getListaPerguntas().size()));

        int acertos = 0;

        for(int i = 0; i < infoApp.getListaPerguntas().size(); i++){
            if(infoApp.getRespostas().get(i))
                acertos++;
        }

        tvAcertosResult.setText(Integer.toString(acertos));

        ResultadoAdapter resultadoAdapter = new ResultadoAdapter(infoApp.getListaPerguntas(), infoApp.getRespostas());

        rvResultado.setLayoutManager(new LinearLayoutManager(ResultJogoActivity.this));
        rvResultado.setItemAnimator(new DefaultItemAnimator());
        rvResultado.setAdapter(resultadoAdapter);
    }

}
