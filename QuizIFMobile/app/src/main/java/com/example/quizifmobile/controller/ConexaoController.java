package com.example.quizifmobile.controller;

import android.content.Context;

import com.example.quizifmobile.util.CriptoHash;
import com.example.quizifmobile.util.Metodos;

import ModelDominio.Jogo;
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

    public String postSalvarResult(Jogo result){

        gravaLog("INS", 0,"Grava jogo - INI");
        String msg;
        try{
            out.writeObject("GRAVARJOGO");

            msg = (String) in.readObject();

            if(!msg.equals("ok"))
                throw new IOException("Servidor não pode processar a requisição");

            out.writeObject(result);

            gravaLog("INS", 0,"Grava jogo - FIM");
            return (String)in.readObject();

        } catch(IOException | ClassNotFoundException e){
            gravaLogErro("ERR", 0, "Erro ao gravar jogo\n"+e.toString());
            return "Erro na comunicação entre cliente/servidor\n"+e.toString();
        }
    }

    public List<Jogo> getRanking(int filtro) {
        gravaLog("GET", 0, "Ranking - INI");
        String msg;

        try {
            out.writeObject("GETRANKING");

            msg = (String) in.readObject();

            if (!msg.equals("ok"))
                throw new IOException("Servidor não pode processar a requisição");

            out.writeObject(filtro);

            List<Jogo> listaJogos = (List<Jogo>) in.readObject();

            gravaLog("GET", 0, "Ranking - FIM");
            return listaJogos;

        } catch (IOException | ClassNotFoundException e) {
            gravaLogErro("ERR", 0, "Erro ao enviar Ranking\n" + e.toString());
        }

        return null;
    }

    public String enviaDelUsu(String email){
        String msg;
        try {
            gravaLog("DEL", 0, "Deletar Usuario - INI");

            out.writeObject("VALIDACODIGOEMAILDELUSU");

            msg = (String) in.readObject();

            if (!msg.equals("ok"))
                throw new IOException("Servidor não pode processar a requisição");

            out.writeObject(email);

            String sal = (String)in.readObject();

            if(sal.isEmpty()){
                return "E^Erro na comunicação entre cliente/servidor";
            }

            gravaLog("DEL", 0, "Codigo email rep:");

            return sal;

        }catch(IOException | ClassNotFoundException e){
            gravaLogErro("ERR", 0, "Erro ao deletar o usuário\n"+e.toString());
            return "E^Erro na comunicação cliente/servidor\n"+e.toString();
        }
    }

    public String respondeDelUsu(String codigo, String sal){

        try {

            if (sal.isEmpty()){
                out.writeObject(codigo);
            } else {
                out.writeObject(CriptoHash.Cripto(codigo, sal, 0));
            }

            String msg = (String) in.readObject();

            switch (msg) {
                case "S^ok": {
                    gravaLog("DEL", 0, "Excluir Usuario - email - FIM");
                    //continua = false;
                    return "S^ok";
                }
                case "Cancelei":{
                    gravaLog("DEL", 0, "Deletar Usuario - email - FIM");
                    return "A^Processo de exclusão de usuário cancelado!";
                }
                default:{
                    return "E^Código Inválido, Processo cancelado";
                }
            }
        } catch (IOException | ClassNotFoundException e){
            return "E^Erro ao deletar o usuário! \n" + e.toString();
        }
    }

    public String deletaUsu (Usuario usu){
        String msg;
        try {
            out.writeObject("Deletar Usuário");
            msg = (String) in.readObject();

            if(!msg.equals("ok"))
                throw new IOException("Servidor não pode processar a requisição");

            out.writeObject(usu);
            msg = (String) in.readObject();

            return msg;
        } catch (IOException | ClassNotFoundException e) {
            gravaLogErro("ERRO", 0, "Erro ao deletar Usuário \n" + e.toString());
            return "E^Erro na comunicação entre cliente/servidor\n"+e.toString();
        }
    }

    public String respondeRedefSenhaUsu(String codigo, String sal){

        try {

            if (sal.isEmpty()){
                out.writeObject(codigo);
            } else {
                out.writeObject(CriptoHash.Cripto(codigo, sal, 0));
            }

            String msg = (String) in.readObject();

            switch (msg) {
                case "S^ok": {
                    gravaLog("RED", 0, "Redefinir senha - email - FIM");
                    //continua = false;
                    return "S^ok";
                }
                case "Cancelei":{
                    gravaLog("RED", 0, "Redefinir senha - email - FIM");
                    return "A^Processo de redefinição de senha cancelado!";
                }
                default:{
                    return "E^Código Inválido, Processo cancelado";
                }
            }
        } catch (IOException | ClassNotFoundException e){
            return "E^Erro ao redefinir a senha! \n" + e.toString();
        }
    }

    public String respondeCadUsu(String codigo, String sal){

        try {

            if (sal.isEmpty()){
                out.writeObject(codigo);
            } else {
                out.writeObject(CriptoHash.Cripto(codigo, sal, 0));
            }

            String msg = (String) in.readObject();

            switch (msg) {
                case "S^ok": {
                    gravaLog("CAD", 0, "Cadastro usuario - email - FIM");
                    //continua = false;
                    return "S^ok";
                }
                case "Cancelei":{
                    gravaLog("CAD", 0, "Cadastro usuario - email - FIM");
                    return "A^Processo de cadastro de usuário cancelado!";
                }
                default:{
                    return "E^Código Inválido, Processo cancelado";
                }
            }
        } catch (IOException | ClassNotFoundException e){
            return "E^Erro ao cadastrar o usuário! \n" + e.toString();
        }
    }

    public String alteraSenhaUsu(InfoApp app, String criptoSenha){
        String msg;
        try {
            out.writeObject("Alterar Senha Usuário");
            msg = (String) in.readObject();

            if(!msg.equals("ok"))
                throw new IOException("Servidor não pode processar a requisição");

            Usuario usu = new Usuario(app.getGUsuLogado().getEmail(), criptoSenha);
            out.writeObject(usu);

            msg = (String) in.readObject();

            return msg;
        } catch (IOException | ClassNotFoundException e) {
            gravaLogErro("ERRO", 0, "Erro ao alterar senha \n" + e.toString());
            return "E^Erro na comunicação entre cliente/servidor\n"+e.toString();
        }
    }
}
