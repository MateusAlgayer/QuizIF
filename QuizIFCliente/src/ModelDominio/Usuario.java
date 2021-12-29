/*
    //João Jorge Stahl Gomes - 29/12/2021 ::Criação
 */
package ModelDominio;

import java.io.Serializable;

public class Usuario implements Serializable{
    private static final long serialVersionUID = 123456789L;
    
    protected int codUsuario;
    protected String nomeUsuario;
    protected String apelido;
    protected String email;
    protected String senha;
    
    //Getter e Setter

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    //Construtores

    public Usuario(int codUsuario, String nomeUsuario, String apelido, String email, String senha) {
        this.codUsuario = codUsuario;
        this.nomeUsuario = nomeUsuario;
        this.apelido = apelido;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(String nomeUsuario, String apelido, String email, String senha) {
        this.nomeUsuario = nomeUsuario;
        this.apelido = apelido;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public Usuario(String email, String senha) {
      this.email = email;
      this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario{" + "codUsuario=" + codUsuario + ", nomeUsuario=" + nomeUsuario + ", apelido=" + apelido + ", email=" + email + ", senha=" + senha + '}';
    }
}
