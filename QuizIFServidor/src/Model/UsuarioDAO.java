
//Mateus Roberto Algayer - 29/12/2021 :: Criação

package Model;

import ModelDominio.Administrador;
import ModelDominio.Comum;
import ModelDominio.Criador;
import ModelDominio.Usuario;
import factory.Conector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static util.Metodos.GravaLog;
import static util.Metodos.GravaLogErro;

public class UsuarioDAO {
  
  private final Connection con;

  public UsuarioDAO() {
    con = Conector.getConnection();
  }
  
  
  public Usuario efetuarLogin(Usuario pUsu, int Id){
    PreparedStatement stmt;
    Usuario wUsu = null;
    
    try {
      String sql = "SELECT * FROM TABUSU "+
                   "WHERE EMAIL = ? AND SENHA = ?"; 
      stmt = con.prepareStatement(sql);
      stmt.setString(1, pUsu.getEmail());
      stmt.setString(2, pUsu.getSenha());
      
      ResultSet result = stmt.executeQuery();
      
      while(result.next()){
        switch(result.getString("TIPO")){
          case "C" -> wUsu = new Comum(result.getInt("CODIGO"), 
                                     result.getString("NOME"),
                                     result.getString("APELIDO"), 
                                     result.getString("EMAIL"), 
                                     result.getString("SENHA"));
          case "J" -> wUsu = new Criador(result.getInt("CODIGO"), 
                                       result.getString("NOME"),
                                       result.getString("APELIDO"), 
                                       result.getString("EMAIL"), 
                                       result.getString("SENHA"));
          case "A" -> wUsu = new Administrador(result.getInt("CODIGO"), 
                                             result.getString("NOME"),
                                             result.getString("APELIDO"), 
                                             result.getString("EMAIL"), 
                                             result.getString("SENHA"));
        }

        GravaLog("SQL", Id, "Recuperou um objeto do banco");
      }
      
      stmt.close();
      con.close();
    } catch (SQLException e) {
      GravaLogErro("ERR",Id, e.toString());
    }
    return wUsu;
  }
  
  public boolean ExisteEmail(String pEmail, int id){
    GravaLog("CAD", id, "Testa email único - INI");
    
    try {
      String sql = "SELECT * FROM TABUSU "+
                   "  WHERE EMAIL = ?";
      try (PreparedStatement stmt = con.prepareStatement(sql)) {
        
        stmt.setString(1, pEmail);

        ResultSet result = stmt.executeQuery();

        if(result.next()){
          return true;
        }

        GravaLog("CAD", id, "Testa email único - FIM");
        return false;
      } finally {
        con.close();
      }
    } catch (SQLException e) {
      GravaLogErro("ERR",id, "Erro ao testar email único\n"+e.toString());
      return true;
    }
  }
  
  public int CadastroUsu(Usuario usu,int id){
    PreparedStatement stmt = null;
    
    try {
      try {
        con.setAutoCommit(false);

        String sql = "INSERT INTO TABUSU (NOME, APELIDO, EMAIL, SENHA, SAL) VALUES (?,?,?,?,?)";

        stmt = con.prepareStatement(sql);

        stmt.setString(1, usu.getNomeUsuario());
        stmt.setString(2, usu.getApelido());
        stmt.setString(3, usu.getEmail());
        stmt.setString(4, usu.getSenha());
        stmt.setString(5, usu.getSal());

        stmt.execute();

        con.commit();

        } catch (SQLException e) {
          try {
            con.rollback();
            GravaLogErro("ERR", id, "Erro ao cadastrar usuário\n"+e.toString());
          } catch (SQLException ex) {
            GravaLogErro("ERR", id, "Erro ao cadastrar usuário\n"+ex.toString());
          }
          return e.getErrorCode();
        }

        return -1;
      } finally {
        try {
          if(stmt != null){
            stmt.close();
          }
          con.setAutoCommit(true);
          con.close();
        } catch (SQLException ex) {
          GravaLogErro("ERR", id, "Erro ao cadastrar usuário\n"+ex.toString());
        }
      }
  }  
  
  public String getUsuSal(String email, int id){
    GravaLog("SAL", id, "Pega sal usuário - INI");
    
    try {
      String sql = "SELECT SAL FROM TABUSU "+
                   "  WHERE EMAIL = ?";
      try (PreparedStatement stmt = con.prepareStatement(sql)) {
        
        stmt.setString(1, email);

        ResultSet result = stmt.executeQuery();
        
        GravaLog("SAL", id, "Pega sal usuário - FIM");
        
        if(result.next()){
          return result.getString("SAL");
        }

        return "";
      } finally {
        con.close();
      }
    } catch (SQLException e) {
      GravaLogErro("ERR",id, "Erro ao recuperar o sal do usuário\n"+e.toString());
      return "";
    }
  }
}
