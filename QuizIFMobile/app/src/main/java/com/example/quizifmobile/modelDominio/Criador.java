package com.example.quizifmobile.modelDominio;

import java.io.Serializable;

public class Criador extends Usuario implements Serializable{
    private static final long serialVersionUID = 123456789L;

    public Criador(int codUsuario, String nomeUsuario, String apelido, String email, String senha, String sal) {
        super(codUsuario, nomeUsuario, apelido, email, senha, sal);
    }

    /**
     * Só para cadastro
     * 
     * @param nomeUsuario
     * @param apelido
     * @param email
     * @param senha
     * @param sal 
     */
    public Criador(String nomeUsuario, String apelido, String email, String senha, String sal) {
        super(nomeUsuario, apelido, email, senha, sal);
    }

    public Criador(int codUsuario) {
        super(codUsuario);
    }

    public Criador(String email, String senha) {
      super(email, senha);
    }

    @Override
    public String toString() {
        return super.toString() + "Criador{" + '}';
    }

    public Criador(int codUsuario, String nomeUsuario, String email) {
        super(codUsuario, nomeUsuario, email);
    }
    
}
