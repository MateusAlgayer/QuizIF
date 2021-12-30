/*
    //João Jorge Stahl Gomes - 29/12/2021 ::Criação
 */
package ModelDominio;

import java.io.Serializable;

public class Criador extends Usuario implements Serializable{
    private static final long serialVersionUID = 123456789L;

    public Criador(int codUsuario, String nomeUsuario, String apelido, String email, String senha) {
        super(codUsuario, nomeUsuario, apelido, email, senha);
    }

    public Criador(String nomeUsuario, String apelido, String email, String senha) {
        super(nomeUsuario, apelido, email, senha);
    }

    public Criador(int codUsuario) {
        super(codUsuario);
    }

    public Criador(String apelido, String senha) {
      super(apelido, senha);
    }

    @Override
    public String toString() {
        return super.toString() + "Criador{" + '}';
    }
    
    
    
}
