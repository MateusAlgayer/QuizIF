package com.example.quizifmobile.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

public class CriptoHash {

    /**
     * Metodo usado para criptografar uma string
     * @param pSenha String a ser criptografada
     * @param pSal Sal a ser utilizado
     * @param id id para gravação de log só é necessário em threads, em outros casos é 0
     * @return senha criptografada ou vazio caso haja erro
     */
    public static String Cripto(String pSenha, String pSal,int id){
        try {
            String wSenhaToHash = pSenha;
            String wSal = pSal;

            return getPassDigest(wSenhaToHash, wSal);

        } catch (Exception e) {
            Metodos.gravaLogErro("ERR", id, "Erro ao criptografar a senha");
            return "";
        }
    }

    /**
     * Metodo que gera um Sal para criptografia
     * @param id id para gravação de log só é necessário em threads, em outros casos é 0
     * @return um sal
     */
    public static String getSalt(int id){

        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
            byte[] salt = new byte[16];
            sr.nextBytes(salt);
            return toHexString(salt);

        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            Metodos.gravaLogErro("ERR", id, "Erro no Sal");
            return "";
        }

    }

    private static String toHexString(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        for(byte i : bytes){
            sb.append(String.format("%02x", i));
        }
        return sb.toString();
    }

    private static String getPassDigest(String pSenhaToHash, String pSal){
        try {

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(pSal.getBytes());

            byte[] bytes = md.digest(pSenhaToHash.getBytes());
            return toHexString(bytes);

        } catch (NoSuchAlgorithmException e) {
            Metodos.gravaLogErro("ERR", 0, e.toString());
            return "";
        }
    }
}
