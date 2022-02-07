package com.example.quizifmobile.controller;

import android.app.Application;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class InfoApp extends Application {
    public Socket socket;
    public ObjectOutputStream out;
    public ObjectInputStream in;

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
