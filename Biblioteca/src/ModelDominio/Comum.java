/*
    //João Jorge Stahl Gomes - 29/12/2021 ::Criação
//Staub - 30/12/2021 :: Edit Comum/add sal
 */
package ModelDominio;

import java.io.Serializable;

public class Comum extends Usuario implements Serializable{
    private static final long serialVersionUID = 123456789L;

    public Comum(int codUsuario, String nomeUsuario, String apelido, String email, String senha) {
        super(codUsuario, nomeUsuario, apelido, email, senha);
    }

    public Comum(String nomeUsuario, String apelido, String email, String senha) {
        super(nomeUsuario, apelido, email, senha);
    }

    public Comum(int codUsuario) {
        super(codUsuario);
    }

    public Comum(String email, String senha, String sal) {
      super(email, senha, sal);
    }

    @Override
    public String toString() {
        return super.toString() + "Comum{" + '}';
    }
    
    
}
