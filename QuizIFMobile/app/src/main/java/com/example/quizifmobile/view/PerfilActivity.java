package com.example.quizifmobile.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizifmobile.R;
import com.example.quizifmobile.adapters.ProvaAdapter;
import com.example.quizifmobile.controller.ConexaoController;
import com.example.quizifmobile.controller.InfoApp;
import com.example.quizifmobile.util.Metodos;

import java.util.List;

import ModelDominio.Prova;

public class PerfilActivity extends AppCompatActivity {

    TextView tvNomePerfil, tvApelidoPerfil, tvEmailPerfil;
    RecyclerView rvHistorico;

    ProvaAdapter.ProvaOnClickListener provaClick;
    ProvaAdapter provaAdapter;
    InfoApp infoApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tvNomePerfil = findViewById(R.id.tvNomePerfil);
        tvApelidoPerfil = findViewById(R.id.tvApelidoPerfil);
        tvEmailPerfil = findViewById(R.id.tvEmailPerfil);
        rvHistorico = findViewById(R.id.rvHistorico);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        infoApp = (InfoApp) getApplicationContext();

        tvNomePerfil.setText(infoApp.getGUsuLogado().getNomeUsuario());
        tvApelidoPerfil.setText(infoApp.getGUsuLogado().getApelido());
        tvEmailPerfil.setText(infoApp.getGUsuLogado().getEmail());

        new Thread(new Runnable() {
            @Override
            public void run() {
                ConexaoController ccont = new ConexaoController(infoApp.in, infoApp.out);

                List<Prova> listaProvas = ccont.getProvasHist();

                provaAdapter = new ProvaAdapter(listaProvas, null);

                if(listaProvas != null){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            rvHistorico.setLayoutManager(new LinearLayoutManager(PerfilActivity.this));
                            rvHistorico.setItemAnimator(new DefaultItemAnimator());
                            rvHistorico.setAdapter(provaAdapter);
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
    }

}
