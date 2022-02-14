package ModelDominio;

import java.io.Serializable;

public class Usuario implements Serializable{
    private static final long serialVersionUID = 123456789L;
    
    private int codUsuario;
    private String nomeUsuario;
    private String apelido;
    private String email;
    private String senha;
    private String sal;
    
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

    public String getSal() {
        return sal;
    }

    public void setSal(String sal) {
        this.sal = sal;
    }
    
    //Construtores

    public Usuario(int codUsuario, String nomeUsuario, String apelido, String email, String senha, String sal) {
        this.codUsuario = codUsuario;
        this.nomeUsuario = nomeUsuario;
        this.apelido = apelido;
        this.email = email;
        this.senha = senha;
        this.sal = sal;
    }
    
    /**só para cadastro
     * 
     * @param nomeUsuario
     * @param apelido
     * @param email
     * @param senha
     * @param sal 
     */
    public Usuario(String nomeUsuario, String apelido, String email, String senha, String sal) {
        this.nomeUsuario = nomeUsuario;
        this.apelido = apelido;
        this.email = email;
        this.senha = senha;
        this.sal = sal;
    }

    public Usuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario(int codUsuario, String nomeUsuario, String email) {
        this.codUsuario = codUsuario;
        this.nomeUsuario = nomeUsuario;
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "Usuario{" + "codUsuario=" + codUsuario + ", nomeUsuario=" + nomeUsuario + ", apelido=" + apelido + ", email=" + email + ", senha=" + senha + '}';
    }

    
}
