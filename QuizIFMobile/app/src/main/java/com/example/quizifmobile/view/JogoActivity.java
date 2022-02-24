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

import com.example.quizifmobile.R;
import com.example.quizifmobile.controller.ConexaoController;
import com.example.quizifmobile.controller.InfoApp;
import com.example.quizifmobile.util.Metodos;

import java.util.HashMap;
import java.util.List;

import ModelDominio.Pergunta;
import ModelDominio.Prova;

import static com.example.quizifmobile.util.Metodos.pedaco;

public class JogoActivity extends AppCompatActivity {

    TextView tvProgProva, tvPerguntaProva;

    Button btJogoAlt1, btJogoAlt2, btJogoAlt3, btJogoAlt4;

    InfoApp infoApp;
    Prova GProvaAtual;
    Pergunta GperAtual;
    List<Pergunta> GlistaPerguntas;
    HashMap<Integer, Boolean> GRespostas;
    int progresso;
    int numPerguntas;

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

        progresso = 0;
        GRespostas = new HashMap<>();

        if(it != null && it.hasExtra("Prova")) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    ConexaoController ccont = new ConexaoController(infoApp.in, infoApp.out);

                    GProvaAtual = (Prova) it.getSerializableExtra("Prova");

                    GlistaPerguntas = ccont.getPerguntasProva(GProvaAtual.getCodigoProva());

                    if (GlistaPerguntas != null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                numPerguntas = GlistaPerguntas.size();
                                proxPergunta();
                            }
                        });

                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Metodos.mensagem(infoApp, "Erro ao carregar a prova!");

                                Intent it = new Intent(JogoActivity.this, PrincipalActivity.class);
                                startActivity(it);
                            }
                        });
                    }
                }
            }).start();
        }

        btJogoAlt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                respondePergunta(1);
            }
        });

        btJogoAlt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                respondePergunta(2);
            }
        });

        btJogoAlt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                respondePergunta(3);
            }
        });

        btJogoAlt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                respondePergunta(4);
            }
        });
    }

    private void proxPergunta() {
        if(progresso == numPerguntas){
            terminaProva();
            return;
        }

        tvProgProva.setText((progresso+1)+"/"+numPerguntas);

        GperAtual = GlistaPerguntas.get(progresso);

        tvPerguntaProva.setText(GperAtual.getPergunta());

        //o html e o br são adições para formatar as linhas com quebra de linha.
        btJogoAlt1.setText(pedaco(GperAtual.getAlternativas(),"Ѫ", 1));
        btJogoAlt2.setText(pedaco(GperAtual.getAlternativas(),"Ѫ", 2));
        btJogoAlt3.setText(pedaco(GperAtual.getAlternativas(),"Ѫ", 3));
        btJogoAlt4.setText(pedaco(GperAtual.getAlternativas(),"Ѫ", 4));
    }

    private void terminaProva() {

        infoApp.setResultJogo(GProvaAtual, GlistaPerguntas, GRespostas);

        Intent it = new Intent(JogoActivity.this, ResultJogoActivity.class);
        startActivity(it);
    }

    private void respondePergunta(final int resposta){

        AlertDialog.Builder dialogResposta = new AlertDialog.Builder(JogoActivity.this);
        dialogResposta.setMessage(pedaco(GperAtual.getAlternativas(),"Ѫ", resposta)+"\n\nTem certeza que quer escolher esta resposta?");
        dialogResposta.setCancelable(true);

        dialogResposta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                GRespostas.put(progresso, GperAtual.getCorreta() == resposta);

                progresso++;
                proxPergunta();
            }
        });

        dialogResposta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog confDelUsu = dialogResposta.create();
        confDelUsu.show();

    }
}
