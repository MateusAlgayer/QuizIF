
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
                                     result.getString("SENHA"),
                                     result.getString("SAL"));
          case "J" -> wUsu = new Criador(result.getInt("CODIGO"), 
                                       result.getString("NOME"),
                                       result.getString("APELIDO"), 
                                       result.getString("EMAIL"), 
                                       result.getString("SENHA"),
                                       result.getString("SAL"));
          case "A" -> wUsu = new Administrador(result.getInt("CODIGO"), 
                                             result.getString("NOME"),
                                             result.getString("APELIDO"), 
                                             result.getString("EMAIL"), 
                                             result.getString("SENHA"),
                                             result.getString("SAL"));
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
  
  public int RedefSenha(Usuario usu, int id){
    PreparedStatement stmt = null;
    
    try {
      try {
        con.setAutoCommit(false);

        String sql = "UPDATE TABUSU SET SENHA = ? WHERE EMAIL = ?";

        stmt = con.prepareStatement(sql);
        
        stmt.setString(1, usu.getSenha());
        stmt.setString(2, usu.getEmail());

        stmt.execute();

        con.commit();

        } catch (SQLException e) {
          try {
            con.rollback();
            GravaLogErro("ERR", id, "Erro redefinir senha\n"+e.toString());
          } catch (SQLException ex) {
            GravaLogErro("ERR", id, "Erro redefinir senha\n"+ex.toString());
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
          GravaLogErro("ERR", id, "Erro redefinir senha\n"+ex.toString());
        }
      }
  }
  
    //método que faz o DELETE na tabela de marcas
    //quando o int for -1 é porque deu tudo certo
    //quando o int fo != de -1 é porque deu ERRO ao deletar
    public int deletausu(Usuario usu, int id) {
        PreparedStatement stmt = null;

        try {

            try {
                //eu vou controlar as transações com o banco
                //por isso o AutoCommit é FALSE
                con.setAutoCommit(false);

                String sql = "delete from tabusu"
                        + " where CODIGO = ?";
                stmt = con.prepareStatement(sql);

                //trocar os parâmetros
                stmt.setInt(1, usu.getCodUsuario());
                //executar o script
                stmt.execute();
                //efetivar a transação
                con.commit(); // <- IMPORTANTE: efetiva a transação
                return -1; //Deu tudo certo!
            } catch (SQLException e) {
                try {
                    GravaLogErro("ERRO", 0, "Erro ao deletar Usuário \n" + e.toString());
                    con.rollback(); // <- cancela a transação se deu erro
                    return e.getErrorCode();
                } catch (SQLException ex) {
                    GravaLogErro("ERRO", 0, "Erro ao deletar Usuário \n" + ex.toString());
                    return ex.getErrorCode();
                }
            }
        } finally {
            try {
                //entra aqui independente se deu erro ou não
                stmt.close();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException e) {
                e.printStackTrace(); 
                return e.getErrorCode();
            }
        }
    }
    
    public int AlteraSenha(Usuario usu, int id){
    PreparedStatement stmt = null;
    
    try {
      try {
        con.setAutoCommit(false);

        String sql = "UPDATE TABUSU SET SENHA = ? WHERE EMAIL = ?";

        stmt = con.prepareStatement(sql);
        
        stmt.setString(1, usu.getSenha());
        stmt.setString(2, usu.getEmail());

        stmt.execute();

        con.commit();

        } catch (SQLException e) {
          try {
            con.rollback();
            GravaLogErro("ERR", id, "Erro ao alterar senha\n"+e.toString());
          } catch (SQLException ex) {
            GravaLogErro("ERR", id, "Erro ao alterar senha\n"+ex.toString());
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
          GravaLogErro("ERR", id, "Erro ao alterar senha\n"+ex.toString());
        }
      }
  }
}
