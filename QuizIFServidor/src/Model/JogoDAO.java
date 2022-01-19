
//João Felipe Staub - 18/01/2022 :: Criação
//João Felipe Staub - 18/01/2022 :: Término

package Model;

import ModelDominio.Jogo;
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
                    wCondicao;
      
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
    
}
