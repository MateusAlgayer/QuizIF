package com.example.quizifmobile.view;

import android.content.Intent;
import android.os.Bundle;
import android.system.Os;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.quizifmobile.R;
import com.example.quizifmobile.controller.ConexaoController;
import com.example.quizifmobile.controller.InfoApp;

import java.lang.reflect.Array;
import java.util.List;

import ModelDominio.Pergunta;
import ModelDominio.Prova;

public class JogoActivity extends AppCompatActivity {

    TextView tvProgProva, tvPerguntaProva;

    Button btJogoAlt1, btJogoAlt2, btJogoAlt3, btJogoAlt4;

    InfoApp infoApp;
    Prova GProvaAtual;
    List<Pergunta> GlistaPerguntas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jogo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvProgProva     = findViewById(R.id.tvProgProva);
        tvPerguntaProva = findViewById(R.id.tvPerguntaProva);

        btJogoAlt1 = findViewById(R.id.btJogoAlt1);
        btJogoAlt2 = findViewById(R.id.btJogoAlt2);
        btJogoAlt3 = findViewById(R.id.btJogoAlt3);
        btJogoAlt4 = findViewById(R.id.btJogoAlt4);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        infoApp = (InfoApp) getApplication();

        final Intent it = getIntent();

        if(it != null && it.hasExtra("Prova")){

            new Thread(new Runnable() {
                @Override
                public void run() {
                    ConexaoController ccont = new ConexaoController(infoApp.in, infoApp.out);

                    GProvaAtual = (Prova) it.getSerializableExtra("Prova");

                    GlistaPerguntas = ccont.getPerguntasProva(GProvaAtual.getCodigoProva());

                    if(GlistaPerguntas != null){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvProgProva.setText("1/"+Integer.toString(GlistaPerguntas.size()));
                            }
                        });

                    }
                }
            }).start();
        }
    }

}
