package ModelDominio;

import java.io.Serializable;

public class Pergunta implements Serializable{
    private static final long serialVersionUID = 123456789L;
    
    private int codPergunta;
    private Area area;
    private String pergunta;
    private int dificuldade;
    private String alternativas;
    private int correta;
    private char situacao;

    public Pergunta(int codPergunta, Area area, String pergunta, int dificuldade) {
      this.codPergunta = codPergunta;
      this.area = area;
      this.pergunta = pergunta;
      this.dificuldade = dificuldade;
    }

    public int getCodPergunta() {
        return codPergunta;
    }

    public void setCodPergunta(int codPergunta) {
        this.codPergunta = codPergunta;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public int getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }

    public String getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(String alternativas) {
        this.alternativas = alternativas;
    }

    public int getCorreta() {
        return correta;
    }

    public void setCorreta(int correta) {
        this.correta = correta;
    }

    public char getSituacao() {
        return situacao;
    }

    public void setSituacao(char situacao) {
        this.situacao = situacao;
    }
    
    

    public Pergunta(int codPergunta, Area area, String pergunta, int dificuldade, String alternativas, int correta, char situacao) {
        this.codPergunta = codPergunta;
        this.area = area;
        this.pergunta = pergunta;
        this.dificuldade = dificuldade;
        this.alternativas = alternativas;
        this.correta = correta;
        this.situacao = situacao;
    }

    public Pergunta(Area area, String pergunta, int dificuldade, String alternativas, int correta, char situacao) {
        this.area = area;
        this.pergunta = pergunta;
        this.dificuldade = dificuldade;
        this.alternativas = alternativas;
        this.correta = correta;
        this.situacao = situacao;
    }

    public Pergunta(int codPergunta) {
        this.codPergunta = codPergunta;
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
        return "Pergunta{" + "codPergunta=" + codPergunta + ", area=" + area + ", pergunta=" + pergunta + ", dificuldade=" + dificuldade + ", alternativas=" + alternativas + ", correta=" + correta + ", situacao=" + situacao + '}';
    } 
}
