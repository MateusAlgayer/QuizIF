
//Mateus Roberto Algayer - 10/01/2022

package Model;

import java.util.ArrayList;
import ModelDominio.Area;
import factory.Conector;
import java.sql.*;
import util.Metodos;

public class AreaDAO {
  
  private final Connection con;

  public AreaDAO() {
    this.con = Conector.getConnection();
  }
  
  public ArrayList<Area> getListaArea(int id){
    try {
      Metodos.GravaLog("SQL", id, "ListaAreas - INI");
      
      PreparedStatement stmt;
      
      String sql = "SELECT * FROM TABARE";
                 
      stmt = con.prepareStatement(sql);
      
      ResultSet result = stmt.executeQuery();
      
      ArrayList<Area> listaProvas = new ArrayList<>();
      
      while(result.next()){
        listaProvas.add(new Area(result.getInt("CODIGO"), result.getString("NOME")));
      }

      stmt.close();
      con.close();
      
      Metodos.GravaLog("SQL", id, "ListaAreas - FIM");
      return listaProvas;
      
    } catch (SQLException e) {
      Metodos.GravaLogErro("ERR",id, e.toString());
    }
    return null;
  }
}
