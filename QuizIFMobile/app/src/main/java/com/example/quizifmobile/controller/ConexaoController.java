package com.example.quizifmobile.controller;

import com.example.quizifmobile.util.CriptoHash;

import ModelDominio.Pergunta;
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

            if(!msg.equals("ok"))
                throw new IOException("Servidor não pode processar a requisição");

            pUsu.setSenha(CriptoHash.Cripto(pUsu.getSenha(), sal, 0));

            out.writeObject(pUsu);

            wUsu = (Usuario) in.readObject();
            gravaLog("LOG", 0, "Efetuar login - FIM");

        } catch (IOException | ClassNotFoundException e) {
            gravaLogErro("ERR", 0, "Erro ao enviar o form de login\n"+e.toString());
        }

        return wUsu;
    }

    public List<Pergunta> getPerguntasProva(int codProva){
        String msg;
        try{
            out.writeObject("GETPERGUNTASJOGO");

            msg = (String) in.readObject();

            if(!msg.equals("ok"))
                throw new IOException("Servidor não pode processar a requisição");

            out.writeObject(codProva);

            List<Pergunta> listaSai = (List<Pergunta>) in.readObject();

            return listaSai;
        } catch(IOException | ClassNotFoundException e){
            gravaLogErro("ERR", 0, "Erro na requisição de perguntas jogo\n"+e.toString());
            return null;
        }
    }

    public List<Prova> getProvasHist() {

        gravaLog("GET", 0, "ProvasHist - INI");
        String msg;
        try {
            out.writeObject("GETLISTAPROVASHIST");

            msg = (String) in.readObject();

            if(!msg.equals("ok"))
                throw new IOException("Servidor não pode processar a requisição");

            List<Prova> listaProvas = (List<Prova>) in.readObject();

            gravaLog("GET", 0, "ProvasHist - FIM");
            return listaProvas;

        } catch (IOException | ClassNotFoundException e) {
            gravaLogErro("ERR", 0, "Erro ao enviar ProvasHist\n" + e.toString());
        }

        return null;
    }
}
