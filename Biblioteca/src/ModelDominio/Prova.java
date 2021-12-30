/*
  //Mateus Roberto Algayer - 29/12/2021 :: Campo pontuacao 
  //João Jorge Stahl Gomes - 29/12/2021 :: Criação
 */
package ModelDominio;

import java.io.Serializable;

public class Prova implements Serializable{
    private static final long serialVersionUID = 123456789L;
    
    private int codigoProva;
    private int nomeProva;
    private Area areaGeral;
    private String dificuludade;
    private char situacao;
    private int pontuacao;

    public int getCodigoProva() {
        return codigoProva;
    }

    public void setCodigoProva(int codigoProva) {
        this.codigoProva = codigoProva;
    }

    public int getNomeProva() {
        return nomeProva;
    }

    public void setNomeProva(int nomeProva) {
        this.nomeProva = nomeProva;
    }

    public Area getAreaGeral() {
        return areaGeral;
    }

    public void setAreaGeral(Area areaGeral) {
        this.areaGeral = areaGeral;
    }

    public String getDificuludade() {
        return dificuludade;
    }

    public void setDificuludade(String dificuludade) {
        this.dificuludade = dificuludade;
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

    public Prova(int codigoProva, int nomeProva, Area areaGeral, String dificuludade, char situacao, int pontuacao) {
        this.codigoProva = codigoProva;
        this.nomeProva = nomeProva;
        this.areaGeral = areaGeral;
        this.dificuludade = dificuludade;
        this.situacao = situacao;
        this.pontuacao = pontuacao; //Mateus Roberto Algayer - 29/12/2021
    }

    public Prova(int nomeProva, Area areaGeral, String dificuludade, char situacao) {
        this.nomeProva = nomeProva;
        this.areaGeral = areaGeral;
        this.dificuludade = dificuludade;
        this.situacao = situacao;
    }

    public Prova(int codigoProva) {
        this.codigoProva = codigoProva;
    }
    

    @Override
    public String toString() {
        return "Prova{" + "codigoProva=" + codigoProva + ", nomeProva=" + nomeProva + ", areaGeral=" + areaGeral + ", dificuludade=" + dificuludade + ", situacao=" + situacao + '}';
    }
    
    
}
