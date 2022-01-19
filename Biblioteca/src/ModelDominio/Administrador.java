/*
    //João Jorge Stahl Gomes - 29/12/2021 ::Criação
    //João Felipe Staub - 19/01/2022 :: Add construtor para listaUsuarios
*/
package ModelDominio;

import java.io.Serializable;

public class Administrador extends Criador implements Serializable{
    private static final long serialVersionUID = 123456789L;

    public Administrador(int codUsuario, String nomeUsuario, String apelido, String email, String senha, String sal) {
        super(codUsuario, nomeUsuario, apelido, email, senha, sal);
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
    
    public Administrador(String nomeUsuario, String apelido, String email, String senha,String sal) {
        super(nomeUsuario, apelido, email, senha, sal);
    }

    public Administrador(String email, String senha) {
      super(email, senha);
    }

    public Administrador(int codUsuario) {
        super(codUsuario);
    }

    @Override
    public String toString() {
        return super.toString() + "Administrador{" + '}';
    }

    public Administrador(int codUsuario, String nomeUsuario, String apelido) {
        super(codUsuario, nomeUsuario, apelido);
    }
    
}
