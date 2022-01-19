/*
    //João Jorge Stahl Gomes - 29/12/2021 ::Criação
    //Staub - 30/12/2021 :: Edit Criador/add sal
    //João Felipe Staub - 19/01/2022 :: Add construtor para listaUsuarios   
 */
package ModelDominio;

import java.io.Serializable;

public class Criador extends Usuario implements Serializable{
    private static final long serialVersionUID = 123456789L;

    public Criador(int codUsuario, String nomeUsuario, String apelido, String email, String senha, String sal) {
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
    public Criador(String nomeUsuario, String apelido, String email, String senha, String sal) {
        super(nomeUsuario, apelido, email, senha, sal);
    }

    public Criador(int codUsuario) {
        super(codUsuario);
    }

    public Criador(String email, String senha) {
      super(email, senha);
    }

    @Override
    public String toString() {
        return super.toString() + "Criador{" + '}';
    }

    public Criador(int codUsuario, String nomeUsuario, String apelido) {
        super(codUsuario, nomeUsuario, apelido);
    }
    
}
