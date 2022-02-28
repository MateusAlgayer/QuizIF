package com.example.quizifmobile.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.quizifmobile.adapters.ProvaAdapter;
import com.example.quizifmobile.adapters.ResultadoAdapter;
import com.example.quizifmobile.controller.ConexaoController;
import com.example.quizifmobile.controller.InfoApp;
import com.example.quizifmobile.util.Metodos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizifmobile.R;

import ModelDominio.Jogo;
import ModelDominio.Pergunta;
import ModelDominio.Prova;
import ModelDominio.Usuario;

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

        final Prova p = infoApp.getProvaAtual();

        tvProvaResult.setText(p.getNomeProva());
        tvDificuldadeResult.setText(p.getDificuldadeLiteral());
        tvRespondidasResult.setText(Integer.toString(infoApp.getListaPerguntas().size()));

        int acertos = 0;

        for(int i = 0; i < infoApp.getListaPerguntas().size(); i++){
            if(infoApp.getRespostas().get(i))
                acertos++;
        }

        final int acertosConst = acertos;

        tvAcertosResult.setText(Integer.toString(acertos));

        ResultadoAdapter resultadoAdapter = new ResultadoAdapter(infoApp.getListaPerguntas(), infoApp.getRespostas());

        rvResultado.setLayoutManager(new LinearLayoutManager(ResultJogoActivity.this));
        rvResultado.setItemAnimator(new DefaultItemAnimator());
        rvResultado.setAdapter(resultadoAdapter);

        btSalvarResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogSalvar = new AlertDialog.Builder(ResultJogoActivity.this);
                dialogSalvar.setMessage("A tela será fechada após salvar\ntem certeza que deseja prosseguir?");
                dialogSalvar.setCancelable(true);

                dialogSalvar.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                ConexaoController ccont = new ConexaoController(infoApp.in, infoApp.out);

                                Jogo resultadoJogo = new Jogo(new Prova(p.getCodigoProva()), new Usuario(infoApp.getGUsuLogado().getCodUsuario()), infoApp.getListaPerguntas().size(), acertosConst);

                                final String status = ccont.postSalvarResult(resultadoJogo);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if(Metodos.processaMsgServidor(ResultJogoActivity.this,status, "Sucesso ao Gravar resultado!", "Erro ao gravar resultado!\n")){
                                            Intent it = new Intent(ResultJogoActivity.this, PrincipalActivity.class);

                                            startActivity(it);
                                            finish();
                                        }
                                    }
                                });
                            }
                        }).start();
                    }
                });

                dialogSalvar.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertSalvar = dialogSalvar.create();
                alertSalvar.show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            onBackPressed();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder dialogSair = new AlertDialog.Builder(ResultJogoActivity.this);
        dialogSair.setMessage("Tem certeza que deseja sair sem salvar?");
        dialogSair.setCancelable(true);

        dialogSair.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent it = new Intent(ResultJogoActivity.this, PrincipalActivity.class);

                startActivity(it);
                finish();
            }
        });

        dialogSair.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertSair = dialogSair.create();
        alertSair.show();
    }
}
