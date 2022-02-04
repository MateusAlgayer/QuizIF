package com.example.quizifmobile.modelDominio;

import java.io.Serializable;

public class Prova implements Serializable{
    private static final long serialVersionUID = 123456789L;
    
    private int codigoProva;
    private String nomeProva;
    private Area areaGeral;
    private int dificuldade;
    private char situacao;
    private int pontuacao;

    public int getCodigoProva() {
        return codigoProva;
    }

    public void setCodigoProva(int codigoProva) {
        this.codigoProva = codigoProva;
    }

    public String getNomeProva() {
        return nomeProva;
    }

    public void setNomeProva(String nomeProva) {
        this.nomeProva = nomeProva;
    }

    public Area getAreaGeral() {
        return areaGeral;
    }

    public void setAreaGeral(Area areaGeral) {
        this.areaGeral = areaGeral;
    }

    public int getDificuldade() {
        return dificuldade;
    }

    public void setDificuludade(int dificuldade) {
        this.dificuldade = dificuldade;
    }

    public char getSituacao() {
        return situacao;
    }

    public void setSituacao(char situacao) {
        this.situacao = situacao;
    }

  public int getPontuacao() {
    return pontuacao;
  }

  public void setPontuacao(int pontuacao) {
    this.pontuacao = pontuacao;
  }

    public Prova(int codigoProva, String nomeProva, Area areaGeral, int dificuldade, char situacao, int pontuacao) {
        this.codigoProva = codigoProva;
        this.nomeProva = nomeProva;
        this.areaGeral = areaGeral;
        this.dificuldade = dificuldade;
        this.situacao = situacao;
        this.pontuacao = pontuacao; //Mateus Roberto Algayer - 29/12/2021
    }

    public Prova(String nomeProva, Area areaGeral, int dificuldade, char situacao) {
        this.nomeProva = nomeProva;
        this.areaGeral = areaGeral;
        this.dificuldade = dificuldade;
        this.situacao = situacao;
    }

    public Prova(int codigoProva) {
        this.codigoProva = codigoProva;
    }
    
    public Prova(int codigoProva, String nomeProva, Area areaGeral, int dificuldade, int pontuacao) {
      this.codigoProva = codigoProva;
      this.nomeProva = nomeProva;
      this.areaGeral = areaGeral;
      this.dificuldade = dificuldade;
      this.pontuacao = pontuacao;
    }
    
    public String getDificuldadeLiteral(){
      switch(this.dificuldade) {
          case 1:
              return "Fácil";
          case 2:
              return "Médio";
          case 3:
              return "Difícil";
          default:
              return "";
      }
    }
    
    @Override
    public String toString() {
        return "Prova{" + "codigoProva=" + codigoProva + ", nomeProva=" + nomeProva + ", areaGeral=" + areaGeral + ", dificuludade=" + dificuldade + ", situacao=" + situacao + '}';
    }
    
    
}
