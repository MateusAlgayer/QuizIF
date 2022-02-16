package com.example.quizifmobile.view;

import android.content.Intent;
import android.os.Bundle;

import com.example.quizifmobile.R;
import com.example.quizifmobile.adapters.ProvaAdapter;
import com.example.quizifmobile.controller.ConexaoController;
import com.example.quizifmobile.controller.InfoApp;
import ModelDominio.Prova;
import ModelDominio.Usuario;
import com.example.quizifmobile.util.Metodos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;

import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    Button btAcessoPerfil, btAcessoRanking;
    RecyclerView rvProvas;

    ProvaAdapter.ProvaOnClickListener provaClick;
    List<Prova> listaProvas;
    ProvaAdapter provaAdapter;
    InfoApp infoApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
        btAcessoPerfil = findViewById(R.id.btAcessoPerfil);
        btAcessoRanking = findViewById(R.id.btAcessoRanking);
        rvProvas = findViewById(R.id.rvProvas);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        infoApp = (InfoApp) getApplicationContext();

        provaClick = new ProvaAdapter.ProvaOnClickListener() {
            @Override
            public void onClickProva(View view, int position) {
                Intent acessoJogo = new Intent(PrincipalActivity.this, JogoActivity.class);

                Prova jogo = provaAdapter.getProva(position);
                acessoJogo.putExtra("Prova",jogo);

                startActivity(acessoJogo);
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                ConexaoController ccont = new ConexaoController(infoApp.in, infoApp.out);

                List<Prova> listaProvas = ccont.getListaProvas(infoApp.getGUsuLogado());

                provaAdapter = new ProvaAdapter(listaProvas, provaClick);

                if(listaProvas != null){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                        rvProvas.setLayoutManager(new LinearLayoutManager(PrincipalActivity.this));
                        rvProvas.setItemAnimator(new DefaultItemAnimator());
                        rvProvas.setAdapter(provaAdapter);
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                        Metodos.mensagem(infoApp,"Erro ao carregar a lista de provas!");
                        }
                    });
                }
            }
        }).start();

        btAcessoRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent acessoRanking = new Intent(PrincipalActivity.this, RankingActivity.class);

                startActivity(acessoRanking);
            }
        });

        btAcessoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent acessoPerfil = new Intent(PrincipalActivity.this, PerfilActivity.class);

                startActivity(acessoPerfil);
            }
        });
    }

}
