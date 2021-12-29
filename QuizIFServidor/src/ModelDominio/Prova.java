/*
    //João Jorge Stahl Gomes - 29/12/2021 ::Criação
 */
package ModelDominio;

/**
 *
 * @author User
 */
public class Prova {
    private static final long seralVersionUID = 123456789L;
    
    private int codigoProva;
    private int nomeProva;
    private Area areaGeral;
    private String dificuludade;
    private char situacao;

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

    public Prova(int codigoProva, int nomeProva, Area areaGeral, String dificuludade, char situacao) {
        this.codigoProva = codigoProva;
        this.nomeProva = nomeProva;
        this.areaGeral = areaGeral;
        this.dificuludade = dificuludade;
        this.situacao = situacao;
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
