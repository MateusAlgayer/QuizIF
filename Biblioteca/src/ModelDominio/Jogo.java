/*
    //João Jorge Stahl Gomes - 29/12/2021 ::Criação
 */
package ModelDominio;

import java.io.Serializable;

public class Jogo implements Serializable{
    private static final long serialVersionUID = 123456789L;
    
    private int codJogo;
    private Prova prova;
    private Usuario jogador;
    private int numPerguntas;
    private int numAcertos;

    public int getCodJogo() {
        return codJogo;
    }

    public void setCodJogo(int codJogo) {
        this.codJogo = codJogo;
    }

    public Prova getProva() {
        return prova;
    }

    public void setProva(Prova prova) {
        this.prova = prova;
    }

    public Usuario getJogador() {
        return jogador;
    }

    public void setJogador(Usuario jogador) {
        this.jogador = jogador;
    }

    public int getNumPerguntas() {
        return numPerguntas;
    }

    public void setNumPerguntas(int numPerguntas) {
        this.numPerguntas = numPerguntas;
    }

    public int getNumAcertos() {
        return numAcertos;
    }

    public void setNumAcertos(int numAcertos) {
        this.numAcertos = numAcertos;
    }

    public Jogo(int codJogo, Prova prova, Usuario jogador, int numPerguntas, int numAcertos) {
        this.codJogo = codJogo;
        this.prova = prova;
        this.jogador = jogador;
        this.numPerguntas = numPerguntas;
        this.numAcertos = numAcertos;
    }

    public Jogo(Prova prova, Usuario jogador, int numPerguntas, int numAcertos) {
        this.prova = prova;
        this.jogador = jogador;
        this.numPerguntas = numPerguntas;
        this.numAcertos = numAcertos;
    }

    public Jogo(int codJogo) {
        this.codJogo = codJogo;
    }

    @Override
    public String toString() {
        return "Jogo{" + "codJogo=" + codJogo + ", prova=" + prova + ", jogador=" + jogador + ", numPerguntas=" + numPerguntas + ", numAcertos=" + numAcertos + '}';
    }
    
    
}
