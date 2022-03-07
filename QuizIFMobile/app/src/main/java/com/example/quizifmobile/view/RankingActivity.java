package com.example.quizifmobile.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizifmobile.R;
import com.example.quizifmobile.adapters.RankingAdapter;
import com.example.quizifmobile.controller.ConexaoController;
import com.example.quizifmobile.controller.InfoApp;
import com.example.quizifmobile.util.Metodos;

import java.util.List;

import ModelDominio.Jogo;

public class RankingActivity extends AppCompatActivity {

    Spinner spFiltro ;
    private RecyclerView rvListaRanking;
    private RankingAdapter rankingAdapter;

    private List<Jogo> listaJogo;
    InfoApp infoApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spFiltro = findViewById(R.id.spFiltro);
        rvListaRanking = (RecyclerView) findViewById(R.id.rvListaRanking);

        infoApp = (InfoApp) getApplicationContext();

        //opções filtro
        final String[] filtro = {"Média de Acertos", "Nº Acertos", "Nº Perguntas"};
        spFiltro.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, filtro));


        //CONTINUAR
        obterFiltro(1); //carregando inicial sem filtro

        spFiltro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                obterFiltro(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                obterFiltro(0);
            }
        });

    }

    public void obterFiltro(final int filtro){
        new Thread(new Runnable() {
            @Override
            public void run() {

                ConexaoController ccont = new ConexaoController(infoApp.in, infoApp.out);
                List<Jogo> listaJogo = ccont.getRanking(filtro);

                final RankingAdapter rankingAdapter = new RankingAdapter(listaJogo);

                if(listaJogo != null){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            rvListaRanking.setLayoutManager(new LinearLayoutManager(RankingActivity.this));
                            rvListaRanking.setItemAnimator(new DefaultItemAnimator());
                            rvListaRanking.setAdapter(rankingAdapter);
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Metodos.mensagem(infoApp, "Erro ao carregar a lista de Ranking");
                        }
                    });
                }

            }
        }).start();
    }

}
