package com.example.quizifmobile.controller;

import android.app.Application;

import ModelDominio.Pergunta;
import ModelDominio.Prova;
import ModelDominio.Usuario;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

public class InfoApp extends Application {
    public Socket socket;
    public ObjectOutputStream out;
    public ObjectInputStream in;

    private Usuario GUsuLogado;
    private List<Pergunta> ListaPerguntas;
    private HashMap<Integer, Boolean> Respostas;
    private Prova provaAtual;

    public HashMap<Integer, Boolean> getRespostas() {
        return Respostas;
    }

    public void setRespostas(HashMap<Integer, Boolean> respostas) {
        Respostas = respostas;
    }

    public List<Pergunta> getListaPerguntas() {
        return ListaPerguntas;
    }

    public void setListaPerguntas(List<Pergunta> listaPerguntas) {
        ListaPerguntas = listaPerguntas;
    }

    public Prova getProvaAtual() {
        return provaAtual;
    }

    public void setProvaAtual(Prova provaAtual) {
        this.provaAtual = provaAtual;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public Usuario getGUsuLogado() {
        return GUsuLogado;
    }

    public void setGUsuLogado(Usuario GUsuLogado) {
        this.GUsuLogado = GUsuLogado;
    }

    public void setResultJogo(Prova p, List<Pergunta> listaPerguntas, HashMap<Integer, Boolean> respostas){
        this.setProvaAtual(p);
        this.setListaPerguntas(listaPerguntas);
        this.setRespostas(respostas);
    }
}
