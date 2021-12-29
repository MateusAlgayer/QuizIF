/*
    //João Jorge Stahl Gomes - 29/12/2021 ::Criação
*/
package ModelDominio;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class Administrador extends Criador implements Serializable{
    private static final long seralVersionUID = 123456789L;

    public Administrador(int codUsuario, String nomeUsuario, String apelido, String email, char senha) {
        super(codUsuario, nomeUsuario, apelido, email, senha);
    }

    public Administrador(String nomeUsuario, String apelido, String email, char senha) {
        super(nomeUsuario, apelido, email, senha);
    }

    public Administrador(int codUsuario) {
        super(codUsuario);
    }

    @Override
    public String toString() {
        return super.toString() + "Administrador{" + '}';
    }
    
    
}
