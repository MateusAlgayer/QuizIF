
//Mateus Roberto Algayer - 30/12/2021

package Model;

import ModelDominio.Area;
import ModelDominio.Prova;
import factory.Conector;
import java.sql.*;
import java.util.ArrayList;
import static util.Metodos.GravaLog;
import static util.Metodos.GravaLogErro;

public class ProvaDAO {
  
  private final Connection con;

  public ProvaDAO() {
    this.con = Conector.getConnection();
  }
  
  public ArrayList<Prova> getListaProva(int usu,int Id, int usuEspec){
    try {
      PreparedStatement stmt;
      
      String wCondicao;
      
      if(usuEspec != 0){
        wCondicao = "WHERE DONO = ? OR NOT FIND_IN_SET(DONO,(SELECT GROUP_CONCAT(CODIGO) FROM TABUSU))";
      } else {
        wCondicao = "WHERE TABPRO.SITUACAO <> 'I'";
      }
      
      String sql = "SELECT TABPRO.CODIGO, TABPRO.NOME, TABPRO.AREAGERAL, TABARE.NOME AS NOMEAREA, TABPRO.DIFICULDADE,"+ 
                   "(SELECT ROUND((NUMACERTOS*100) DIV NUMPERGUNTAS) FROM TABUSUPRO "+
                   "WHERE PROVA = TABPRO.CODIGO AND USUARIO = ?) AS PONTUACAO "+
                   "FROM TABPRO "+
                   "LEFT OUTER JOIN TABARE ON TABARE.CODIGO = TABPRO.AREAGERAL "+
                   wCondicao;
      
      stmt = con.prepareStatement(sql);
      
      stmt.setInt(1, usu);
      
      if(usuEspec != 0){
        stmt.setInt(2, usuEspec);
      }
      
      ResultSet result = stmt.executeQuery();
      
      ArrayList<Prova> listaProvas = new ArrayList<>();
      
      while(result.next()){
        listaProvas.add(new Prova(result.getInt("CODIGO"),
                                  result.getString("NOME"),
                                  new Area(result.getInt("AREAGERAL"),result.getString("NOMEAREA")),
                                  result.getString("DIFICULDADE"),
                                  result.getInt("PONTUACAO")));
      }

      stmt.close();
      con.close();
      
      GravaLog("SQL", Id, "Recuperou um objeto do banco: ListaProvas");
      return listaProvas;
      
    } catch (SQLException e) {
      GravaLogErro("ERR",Id, e.toString());
    }
    return null;
  }
}
