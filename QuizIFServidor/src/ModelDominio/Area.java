/*
    //João Jorge Stahl Gomes - 29/12/2021 ::Criação
 */
package ModelDominio;

/**
 *
 * @author User
 */
public class Area {
    private static final long seralVersionUID = 123456789L;
    
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
