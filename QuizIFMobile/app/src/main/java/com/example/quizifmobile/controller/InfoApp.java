package com.example.quizifmobile.controller;

import android.app.Application;

import ModelDominio.Usuario;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class InfoApp extends Application {
    public Socket socket;
    public ObjectOutputStream out;
    public ObjectInputStream in;

    private Usuario GUsuLogado;

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
}
