package com.example.quizifmobile.controller;

import com.example.quizifmobile.util.CriptoHash;

import ModelDominio.Prova;
import ModelDominio.Usuario;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import static com.example.quizifmobile.util.Metodos.gravaLog;
import static com.example.quizifmobile.util.Metodos.gravaLogErro;

public class ConexaoController {
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ConexaoController(ObjectInputStream in, ObjectOutputStream out) {
        this.in = in;
        this.out = out;
    }

    public List<Prova> getListaProvas(Usuario usuLogado){

        gravaLog("GET", 0, "Provas - INI");
        String msg = "";
        try {
            out.writeObject("GETLISTAPROVAS");

            msg = (String) in.readObject();
            out.writeObject(usuLogado);

            msg = (String) in.readObject();
            out.writeObject(0);

            List<Prova> listaProvas = (List<Prova>) in.readObject();

            gravaLog("GET", 0, "Provas - FIM");
            return listaProvas;

        } catch (IOException | ClassNotFoundException e) {
            gravaLogErro("ERR", 0, "Erro ao enviar listaProvas\n"+e.toString());
        }

        return null;
    }

    public Usuario login(Usuario pUsu) {
        Usuario wUsu = null;

        String msg = "";
        try {
            gravaLog("LOG", 0, "Efetuar login - INI");

            out.writeObject("EFETUARLOGIN");

            msg = (String) in.readObject();
            out.writeObject(pUsu.getEmail());

            String sal = (String)in.readObject();

            if(sal.isEmpty()){
                return null;
            }

            pUsu.setSenha(CriptoHash.Cripto(pUsu.getSenha(), sal, 0));

            out.writeObject(pUsu);

            wUsu = (Usuario) in.readObject();
            gravaLog("LOG", 0, "Efetuar login - FIM");

        } catch (IOException | ClassNotFoundException e) {
            gravaLogErro("ERR", 0, "Erro ao enviar o form de login\n"+e.toString());
        }

        return wUsu;
    }
}
