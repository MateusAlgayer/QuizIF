
//João Felipe Staub - 18/01/2022 :: Criação

package Model;

import ModelDominio.Area;
import ModelDominio.Jogo;
import ModelDominio.Pergunta;
import factory.Conector;
import java.sql.*;
import java.util.ArrayList;
import static util.Metodos.GravaLog;
import static util.Metodos.GravaLogErro;

/**
 *
 * @author felip
 */
public class JogoDAO {
    
    private final Connection con;

    public JogoDAO() {
        this.con = Conector.getConnection();
    }
    
    public ArrayList<Jogo> getListaRanking(int filtro, int Id){
    try {
      PreparedStatement stmt;
      
      String wCondicao;
      
      wCondicao = switch(filtro){
          case 1 -> "TOTNUMACERTO";
          case 2 -> "TOTNUMPERGUNTA";
          default -> "PONTUACAO";
      };
      
      String sql = "SELECT TABUSU.NOME, TABUSU.APELIDO, SUM(TABUSUPRO.NUMACERTOS) AS TOTNUMACERTO, " +
                    "SUM(TABUSUPRO.NUMPERGUNTAS) AS TOTNUMPERGUNTA, " +
                    "(SELECT ROUND((SUM(NUMACERTOS)*100) DIV SUM(NUMPERGUNTAS)) FROM TABUSUPRO " +
                    "WHERE USUARIO = TABUSU.CODIGO) AS PONTUACAO " +
                    "FROM TABUSUPRO " +
                    "LEFT OUTER JOIN TABUSU ON TABUSUPRO.USUARIO = TABUSU.CODIGO " +
                    "GROUP BY TABUSU.CODIGO " +
                    "ORDER BY " + 
                    wCondicao+ " DESC";
      
      stmt = con.prepareStatement(sql);
      
      ResultSet result = stmt.executeQuery();
      
      ArrayList<Jogo> listaRanking = new ArrayList<>();
      
      while(result.next()){
        listaRanking.add(new Jogo(null,
                                  result.getString("APELIDO"),
                                  result.getInt("TOTNUMPERGUNTA"),
                                  result.getInt("TOTNUMACERTO"),                 
                                  result.getInt("PONTUACAO")
                                  ));
      }

      stmt.close();
      con.close();
      
      GravaLog("SQL", Id, "Recuperou um objeto do banco: ListaRanking");
      return listaRanking;
      
    } catch (SQLException e) {
      GravaLogErro("ERR",Id, e.toString());
    }
    return null;
  }  
  
  public ArrayList<Pergunta> getPerguntasJogo(int numProva, int idUnico) {
    try {
      ArrayList<Pergunta> listaSel = new ArrayList<>();
      
      PreparedStatement stmt;
      //Pergunta(int codPergunta, Area area, String pergunta, int dificuldade, String alternativas, int correta, char situacao)
      String sql = "SELECT TABPER.CODIGO, TABARE.CODIGO AS CODAREA, TABARE.NOME, TABPER.PERGUNTA, TABPER.DIFICULDADE, "+
                   "TABPER.ALTERNATIVAS, TABPER.CORRETA, TABPER.SITUACAO AS SIT "+ 
                   "FROM TABPROPER "+
                   " LEFT OUTER JOIN TABPER ON TABPROPER.PERGUNTA = TABPER.CODIGO "+
                   " LEFT OUTER JOIN TABARE ON TABPER.CODAREA = TABARE.CODIGO "+
                   "WHERE TABPROPER.PROVA = ? "; 

      stmt = con.prepareStatement(sql); 

      stmt.setInt(1, numProva);

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
      GravaLog("SQL", idUnico, "Recuperou um objeto do banco: PerguntasJogo");
      return listaSel;
    } catch (SQLException e) {
      GravaLogErro("ERR",idUnico, e.toString());
      return null;
    }
  }
}
