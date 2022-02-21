package com.example.quizifmobile.view;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizifmobile.R;
import com.example.quizifmobile.controller.InfoApp;

import java.util.List;

import ModelDominio.Jogo;

public class RankingActivity extends AppCompatActivity {

    Spinner spFiltro ;
    RecyclerView rvListaRanking;
    //RankingAdapter rankingAdapter;


    List<Jogo> listaJogos;
    InfoApp infoApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking);

        spFiltro = findViewById(R.id.spFiltro);
        rvListaRanking = findViewById(R.id.rvListaRanking);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        infoApp = (InfoApp) getApplicationContext();

        //para filtro
        String[] filtro = {"Média de Acertos", "Nº Acertos", "Nº Perguntas"};
        spFiltro.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, filtro));

        //para adapter
        rvListaRanking = (RecyclerView) findViewById(R.id.rvListaRanking);

        //CONTINUAR

    }

}
