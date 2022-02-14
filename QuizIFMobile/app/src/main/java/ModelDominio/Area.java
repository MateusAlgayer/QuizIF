package ModelDominio;

import java.io.Serializable;

public class Area implements Serializable{
    private static final long serialVersionUID = 123456789L;
    
    private int codArea;
    private String nome;

    public int getCodArea() {
        return codArea;
    }

    public void setCodArea(int codArea) {
        this.codArea = codArea;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
    
    public Area(int codArea, String nome) {
        this.codArea = codArea;
        this.nome = nome;
    }

    public Area(int codArea) {
        this.codArea = codArea;
    }

    public Area(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Area{" + "codArea=" + codArea + ", nome=" + nome + '}';
    }
}
