
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
  
}
