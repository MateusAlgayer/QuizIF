package ModelDominio;

import java.io.Serializable;

public class Jogo implements Serializable{
    private static final long serialVersionUID = 123456789L;
    
    private Prova prova;
    private Usuario jogador;
    private int numPerguntas;
    private int numAcertos;
    private int pontuacao;
    
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
    
    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Jogo(Prova prova, Usuario jogador, int numPerguntas, int numAcertos, int pontuacao) {
        this.prova = prova;
        this.jogador = jogador;
        this.numPerguntas = numPerguntas;
        this.numAcertos = numAcertos;
        this.pontuacao = pontuacao;
    }

    public Jogo(Prova prova, Usuario jogador, int numPerguntas, int numAcertos) {
        this.prova = prova;
        this.jogador = jogador;
        this.numPerguntas = numPerguntas;
        this.numAcertos = numAcertos;
    }

    @Override
    public String toString() {
        return "Jogo{" + ", prova=" + prova + ", jogador=" + jogador + ", numPerguntas=" + numPerguntas + ", numAcertos=" + numAcertos + '}';
    }
}
