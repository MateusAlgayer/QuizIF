package com.example.quizifmobile.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
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
    Button btAltSenhaUsu, btDeletaUsu;
    RecyclerView rvHistorico;

    ProvaAdapter provaAdapter;
    InfoApp infoApp;

    final int TELAPERFIL = 1;
    final int ALTSENHA   = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tvNomePerfil = findViewById(R.id.tvNomePerfil);
        tvApelidoPerfil = findViewById(R.id.tvApelidoPerfil);
        tvEmailPerfil = findViewById(R.id.tvEmailPerfil);
        btAltSenhaUsu = findViewById(R.id.btAltSenhaUsu);
        btDeletaUsu = findViewById(R.id.btDeletaUsu);
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

        btAltSenhaUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent alterarSenha = new Intent(PerfilActivity.this, ConfirmaSenhaActivity.class);

                alterarSenha.putExtra("TelaPai", ALTSENHA);

                startActivity(alterarSenha);
            }
        });

        btDeletaUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder confirmarDelUsu = new AlertDialog.Builder(PerfilActivity.this);
                confirmarDelUsu.setMessage("Deseja realmente excluir o usu??rio?");
                confirmarDelUsu.setCancelable(true);

                confirmarDelUsu.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent it = new Intent(PerfilActivity.this, ConfirmaCodigoEmailActivity.class);

                        it.putExtra("TelaPai", TELAPERFIL);
                        dialogInterface.dismiss();

                        startActivity(it);
                    }
                });

                confirmarDelUsu.setNegativeButton("N??o", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog confDelUsu = confirmarDelUsu.create();
                confDelUsu.show();

            }
        });
    }

}
