package com.example.quizifmobile.util;


import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Metodos {

    //Par de nomes e tamanhos dos campos,
    //usado no Consistencia para validar se um campo não ultrapassou o limite dos campos da base.
    private static HashMap<String, Integer> GTamanhoCampos;

    /**
     * setter do hashmap de tamanhos dos campos. Não é necessário setar novamente após a primeira vez
     * @param listaCampos lista de hashMap que vem do servidor quando a aplicação inicializa
     */
    public static void setTamanhoCampos(HashMap<String, Integer> listaCampos){
        GTamanhoCampos = listaCampos;
    }

    /**
     * metodo para adicionar tamanhos personalizados de campo no hashmap
     * CUIDADO: colocar uma string que já existe sobrescreve o conteudo daquele par
     * @param campo String que representa a chave a ser utilizada para identificar o campo
     * @param tamanho inteiro que representa o tamanho personalizado do campo
     */
    public static void putTamanhoCampoPerso(String campo, int tamanho){
        GTamanhoCampos.put(campo, tamanho);
    }

    /**
     * getter do hashmap de tamanhos dos campos.
     * @param nomeCampo nomedatabela.nomedocampo
     * @return retorna o tamanho do campo passado como parâmetro na base
     */
    public static int getTamanhoCampo(String nomeCampo){
        return GTamanhoCampos.get(nomeCampo);
    }

    /**
     * Esse metodo serve para pegar uma parte da string que está entre delimitadores
     * @param pLinha - a String total a ser separada
     * @param pDelimita - o delimitador que deve ser utilizado
     * @param pNumString - o numero da string que se quer Ex: Ciclano-Fulano, 1 seria Ciclano, 2 - seria Fulano
     * @return A string dentro dos delimitadores ou vazio caso não encontre
     */
    public static String Pedaco(String pLinha, String pDelimita, int pNumString) {

        Boolean wContinua = true;
        String wParte = "";
        int wCiclo = 0;
        int wIdxIni = 0, wIdx = 0;

        if (!pLinha.contains(pDelimita)) return "";
        if (pNumString == 0) return "";

        wIdx = pLinha.indexOf(pDelimita, wIdx);

        while (wContinua) {

            if (wIdx == -1) {
                wContinua = false;
                wIdx = pLinha.length();
            }

            wCiclo++;

            if (wCiclo == pNumString) {

                wParte = pLinha.substring(wIdxIni, wIdx);
                break;
            }

            wIdxIni = wIdx + pDelimita.length();
            wIdx = pLinha.indexOf(pDelimita, wIdx + pDelimita.length());

        }
        return wParte;
    }

    /**
     * Esse metodo cria um arquivo de log, usado para marcar processos da aplicação
     * @param acao descrição simples do que está sendo feito Ex: "GET", "DEL", "UPD", "REQ"
     * @param id Usado para criar um arquivo com essa id, é usado principalmente no servidor para diferenciar logs de diferentes threads
     * @param operacao uma string com informações adicionais para aquele log
     */
    public static void GravaLog(String acao, int id, String operacao){
        Log.d("DEBUG", "\n"+acao+" - "+id+" - "+Calendar.getInstance().getTime()+" - "+operacao);
    }

    /**
     * Esse metodo cria um arquivo de log específico para Erros, usado para marcar erros da aplicação
     * @param acao descrição simples do que está sendo feito Ex: "GET", "DEL", "UPD", "REQ"
     * @param id Usado para criar um arquivo com essa id, é usado principalmente no servidor para diferenciar logs de diferentes threads
     * @param operacao uma string com informações adicionais para aquele log e informações do erro
     */
    public static void GravaLogErro(String acao, int id, String operacao){
        Log.d("DEBUG-ERR", "\n"+acao+" - "+id+" - "+Calendar.getInstance().getTime()+" - "+operacao);
    }
}