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

    /**
     * Só para cadastro
     * 
     * @param nomeUsuario
     * @param apelido
     * @param email
     * @param senha
     * @param sal 
     */
    public Comum(String nomeUsuario, String apelido, String email, String senha, String sal) {
        super(nomeUsuario, apelido, email, senha, sal);
    }

    public Comum(int codUsuario) {
        super(codUsuario);
    }

    public Comum(String email, String senha) {
      super(email, senha);
    }

    @Override
    public String toString() {
        return super.toString() + "Comum{" + '}';
    }
    
    
}
