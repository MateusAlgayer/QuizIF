package com.example.quizifmobile.view;

import android.content.Intent;
import android.os.Bundle;

import com.example.quizifmobile.controller.InfoApp;
import com.example.quizifmobile.util.Metodos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.quizifmobile.R;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    InfoApp infoApp;
    EditText etIPServ;
    Button btGravarIPServ;
    TextView tvTentandoConectar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        etIPServ = findViewById(R.id.etIPServ);
        btGravarIPServ = findViewById(R.id.btGravarIPServ);
        tvTentandoConectar = findViewById(R.id.tvTentandoConectar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        infoApp = (InfoApp) getApplicationContext();

        etIPServ.setText(Metodos.leConf(this, "IPServer"));

        if(!etIPServ.getText().toString().isEmpty())
            ConectaServidor();

        btGravarIPServ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gravaIPeConecta();
            }
        });
    }

    private void gravaIPeConecta(){
        String ip = etIPServ.getText().toString();

        if(ip.isEmpty()){
            etIPServ.setError("IP Vazio!");
            etIPServ.requestFocus();
            return;
        }

        Metodos.criaConf(this, "IPServer", ip);

        ConectaServidor();
    }

    private void ConectaServidor(){
        final String ip = Metodos.leConf(this, "IPServer");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // criando a conex??o socket
                    tvTentandoConectar.setVisibility(View.VISIBLE);
                    infoApp.socket = new Socket(ip, 12345);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Metodos.mensagem(infoApp,"Tentando efetuar a conex??o...");
                        }
                    });

                    infoApp.out = new ObjectOutputStream(infoApp.socket.getOutputStream());
                    infoApp.in = new ObjectInputStream(infoApp.socket.getInputStream());

                    Metodos.setTamanhoCampos((HashMap<String, Integer>) infoApp.in.readObject());

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Metodos.mensagem(infoApp,"Conex??o estabelecida com sucesso");

                            tvTentandoConectar.setVisibility(View.INVISIBLE);

                            Intent it = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(it);
                        }
                    });
                } catch (IOException | ClassNotFoundException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Metodos.mensagem(infoApp,"Erro na conex??o:\n"+e.toString());
                            tvTentandoConectar.setVisibility(View.INVISIBLE);
                        }
                    });

                    Metodos.gravaLogErro("ERR", 0, "Erro na conex??o:\n"+e.toString());
                }
            }
        });
        thread.start();
    }}
