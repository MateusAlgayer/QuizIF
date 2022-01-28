
package Model;

import factory.Conector;
import java.util.HashMap;
import java.sql.*;
import static util.Metodos.GravaLog;
import static util.Metodos.GravaLogErro;

public class InfoBaseDAO {
  
  private final Connection con;
  
  public InfoBaseDAO(){
    con = Conector.getConnection();
  }
  
  /**
   * Pega o tamanho dos campos do SQL, é uma informação utilizada para verificar o tamanho máximo que as variaveis podem ter
   * @return HashMap com pares String/Inteiro, sendo String a tabela + campo e Inteiro o tamanho do campo
   */
  public HashMap<String, Integer> getTamanhoCampos(){
    PreparedStatement stmt = null;
    try{
      try {
        String sql = "SELECT UPPER(CONCAT(TABLE_NAME,'.',COLUMN_NAME)) AS CAMPO, "+
                     "CHARACTER_MAXIMUM_LENGTH FROM information_schema.COLUMNS WHERE TABLE_SCHEMA = 'quizif' AND DATA_TYPE = 'varchar';";

        stmt = con.prepareStatement(sql);

        ResultSet result = stmt.executeQuery();

        HashMap<String, Integer> map = new HashMap<>();

        while(result.next())
          map.put(result.getString("CAMPO"), result.getInt("CHARACTER_MAXIMUM_LENGTH"));

        GravaLog("SQL", 0, "Carregou info: Tamanho campos");
        return map;

      } catch (SQLException e) {
        GravaLogErro("ERR",0,"Erro ao carregar tamanho de campos"+e.toString());
      }
      return null;
    }finally{
      try {
        if(stmt != null)
          stmt.close();
        con.close(); 
      } catch (SQLException e) {
        GravaLogErro("ERR",0,e.toString());
      }
      
    }
  }
  
}
