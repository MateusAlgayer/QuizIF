
//Mateus Roberto Algayer - 24/01/2022

package Model;

import ModelDominio.Area;
import ModelDominio.Pergunta;
import factory.Conector;
import java.sql.*;
import java.util.ArrayList;
import static util.Metodos.gravaLog;
import static util.Metodos.gravaLogErro;

public class PerguntaDAO {
  
  private Connection con;

  public PerguntaDAO() {
    this.con = Conector.getConnection();
  }
  
  public String DeletaPergunta(int codPergunta, int idUnico) {
    PreparedStatement stmt = null;

    try {
        try {

            con.setAutoCommit(false);

            String sql = "DELETE FROM TABPER "+
                         " WHERE CODIGO = ?";
            stmt = con.prepareStatement(sql);

            stmt.setInt(1, codPergunta);

            stmt.execute();

            con.commit();
            return "S^ok";
        } catch (SQLException e) {
            try {
                gravaLogErro("ERRO", 0, "Erro ao deletar pergunta \n" + e.toString());
                con.rollback();
                
                if(e.getErrorCode() == 1451){ //codigo de erro caso a chave estrangeira já esteja em uso e não possa ser deletada 
                  return "A^Pergunta não pode ser deletada pois já foi utilizada por alguma prova\n utilize o campo 'Situação' para inativar a pergunta";
                }
                
                return "E^"+e.getErrorCode()+" - "+e.toString();
            } catch (SQLException ex) {
                gravaLogErro("ERRO", 0, "Erro ao deletar pergunta \n" + ex.toString());
                return "E^"+e.getErrorCode()+" - "+e.toString();
            }
        }
    } finally {
        try {
            if(stmt != null) //testa se não é null ao invés de só fechar, pq pode dar erro no con.setAutoCommit e nesse caso stmt ainda não pode usar .close() e geraria mais erros
              stmt.close();
            con.setAutoCommit(true);
            con.close();
        } catch (SQLException e) {
            gravaLogErro("ERRO", 0, "Erro ao deletar pergunta \n" + e.toString());
        }
    }
  }
  
  public ArrayList<Pergunta> getPerguntas(int dono, int idUnico) {
    try {
      ArrayList<Pergunta> listaSel = new ArrayList<>();
      
      PreparedStatement stmt;
      String sql = "SELECT TABPER.CODIGO, TABARE.CODIGO AS CODAREA, TABARE.NOME, TABPER.PERGUNTA, TABPER.DIFICULDADE, "+
                   "TABPER.ALTERNATIVAS, TABPER.CORRETA, TABPER.SITUACAO AS SIT "+ 
                   "FROM TABPER "+
                   " LEFT OUTER JOIN TABARE ON TABPER.CODAREA = TABARE.CODIGO "+
                   "WHERE DONO = ? OR NOT FIND_IN_SET(DONO,(SELECT GROUP_CONCAT(CODIGO) FROM TABUSU))";

      stmt = con.prepareStatement(sql); 

      stmt.setInt(1, dono);
      
      ResultSet result = stmt.executeQuery();

      while(result.next()){
        listaSel.add( new Pergunta(result.getInt("CODIGO"),
                                   new Area(result.getInt("CODAREA"),result.getString("NOME")),
                                   result.getString("PERGUNTA"),
                                   result.getInt("DIFICULDADE"),
                                   result.getString("ALTERNATIVAS"),
                                   result.getInt("CORRETA"),
                                   result.getString("SIT").charAt(0)));
      }
      
      stmt.close();
      con.close();
      gravaLog("SQL", idUnico, "Recuperou um objeto do banco: PerguntasJogo");
      return listaSel;
    } catch (SQLException e) {
      gravaLogErro("ERR",idUnico, e.toString());
      return null;
    }
  }

  public String InserirPergunta(Pergunta p, int idUnico, int usuLogado) {
    PreparedStatement stmt = null;
    
    try {
      try {
        con.setAutoCommit(false);

        String sql = "INSERT INTO TABPER(CODAREA, DONO, PERGUNTA, DIFICULDADE, ALTERNATIVAS, CORRETA, SITUACAO) VALUES (?, ?, ?, ?, ?, ?, ?);";
        
        //Insere a prova
        stmt = con.prepareStatement(sql);

        stmt.setInt(1, p.getArea().getCodArea());
        stmt.setInt(2, usuLogado);
        stmt.setString(3, p.getPergunta());
        stmt.setInt(4, p.getDificuldade());
        stmt.setString(5, p.getAlternativas());
        stmt.setInt(6, p.getCorreta());
        stmt.setString(7, String.valueOf(p.getSituacao()));

        stmt.execute();
        stmt.close();
        
        //da commit em tudo
        con.commit();

        } catch (SQLException e) {
          try {
            con.rollback();
            gravaLogErro("ERR", idUnico, "Erro ao inserir pergunta\n"+e.toString());
          } catch (SQLException ex) {
            gravaLogErro("ERR", idUnico, "Erro ao inserir pergunta\n"+ex.toString());
          }
          return "E^"+e.getErrorCode()+" - "+e.toString();
        }

        return "S^ok";
      } finally {
        try {
          //testa se não é null pq pode falhar no con.setAutoCommit e dai stmt ainda não pode usar close()
          if(stmt != null){
            stmt.close();
          }
          con.setAutoCommit(true);
          con.close();
        } catch (SQLException ex) {
          gravaLogErro("ERR", idUnico, "Erro ao inserir pergunta\n"+ex.toString());
        }
      }
  }

  public String AlterarPergunta(Pergunta p, int idUnico, int usuLogado) {
    PreparedStatement stmt = null;
    
    try {
      try {
        con.setAutoCommit(false);

        String sql = "UPDATE TABPER SET CODAREA = ?, DONO = ?, PERGUNTA = ?, DIFICULDADE = ?, ALTERNATIVAS = ?, CORRETA = ?, SITUACAO = ? WHERE CODIGO = ?";
        
        //Insere a prova
        stmt = con.prepareStatement(sql);

        stmt.setInt(1, p.getArea().getCodArea());
        stmt.setInt(2, usuLogado);
        stmt.setString(3, p.getPergunta());
        stmt.setInt(4, p.getDificuldade());
        stmt.setString(5, p.getAlternativas());
        stmt.setInt(6, p.getCorreta());
        stmt.setString(7, String.valueOf(p.getSituacao()));
        stmt.setInt(8, p.getCodPergunta());

        stmt.execute();
        stmt.close();
        
        //da commit em tudo
        con.commit();

        } catch (SQLException e) {
          try {
            con.rollback();
            gravaLogErro("ERR", idUnico, "Erro ao alterar pergunta\n"+e.toString());
          } catch (SQLException ex) {
            gravaLogErro("ERR", idUnico, "Erro ao alterar pergunta\n"+ex.toString());
          }
          return "E^"+e.getErrorCode()+" - "+e.toString();
        }

        return "S^ok";
      } finally {
        try {
          //testa se não é null pq pode falhar no con.setAutoCommit e dai stmt ainda não pode usar close()
          if(stmt != null){
            stmt.close();
          }
          con.setAutoCommit(true);
          con.close();
        } catch (SQLException ex) {
          gravaLogErro("ERR", idUnico, "Erro ao alterar pergunta\n"+ex.toString());
        }
      }
  }
}
