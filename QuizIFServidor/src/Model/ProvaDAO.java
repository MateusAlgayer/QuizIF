
//Mateus Roberto Algayer - 30/12/2021

package Model;

import ModelDominio.Area;
import ModelDominio.Pergunta;
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
                                  result.getInt("DIFICULDADE"),
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

  public void getPerguntasProva(int numProva, ArrayList<Pergunta> listaDis, ArrayList<Pergunta> listaSel, int Id) {
     try {
      PreparedStatement stmt;
      
      String wCondicao = "";
      
      if(numProva != 0){
        wCondicao = "AND TABPER.CODIGO NOT IN (SELECT TABPROPER.PERGUNTA FROM TABPROPER WHERE TABPROPER.PROVA = ?)";
      }
      
      String sql = "SELECT TABPER.CODIGO, TABPER.PERGUNTA,TABARE.CODIGO AS CODAREA, TABARE.NOME, TABPER.DIFICULDADE  FROM TABPER "+
                   "LEFT OUTER JOIN TABARE ON TABPER.CODAREA = TABARE.CODIGO "+
                   "WHERE TABPER.SITUACAO <> 'I' "+
                   wCondicao;
      
      stmt = con.prepareStatement(sql); 
      
      if(numProva != 0){
        stmt.setInt(1, numProva);
      }
      
      ResultSet result = stmt.executeQuery();
      
      while(result.next()){
        listaDis.add(new Pergunta(result.getInt("CODIGO"),
                                  new Area(result.getInt("CODAREA"),result.getString("NOME")),
                                  result.getString("PERGUNTA"),
                                  result.getInt("DIFICULDADE")));
      }
      
      if(numProva != 0){
      
        sql = "SELECT TABPER.CODIGO, TABPER.PERGUNTA,TABARE.CODIGO AS CODAREA, TABARE.NOME, TABPER.DIFICULDADE "+
              "FROM TABPROPER "+
              " LEFT OUTER JOIN TABPER ON TABPROPER.PERGUNTA = TABPER.CODIGO "+
              " LEFT OUTER JOIN TABARE ON TABPER.CODAREA = TABARE.CODIGO "+
              "WHERE TABPROPER.PROVA = ? "; 
      
        stmt = con.prepareStatement(sql); 

        stmt.setInt(1, numProva);

        result = stmt.executeQuery();

        while(result.next()){
          listaSel.add( new Pergunta(result.getInt("CODIGO"),
                                     new Area(result.getInt("CODAREA"),result.getString("NOME")),
                                     result.getString("PERGUNTA"),
                                     result.getInt("DIFICULDADE")));
        }
      
      }

      stmt.close();
      con.close();
      
      GravaLog("SQL", Id, "Recuperou um objeto do banco: PerguntasProva");
    } catch (SQLException e) {
      GravaLogErro("ERR",Id, e.toString());
    }
  }

  public int InserirProva(Prova p, ArrayList<Pergunta> cadPerSel, int idUnico, int usuLogado) {
    PreparedStatement stmt = null;
    PreparedStatement stmt2 = null;
    PreparedStatement stmt3 = null;
    
    try {
      try {
        con.setAutoCommit(false);
        
        int cont = 0;
        String sqlAdicional = "";
        
        for (int x = 0; x < cadPerSel.size();x++)
          sqlAdicional += (sqlAdicional.equals("") ? "" : ",")+"(@ULT_ID_PRO, ?)";

        String sql = "INSERT INTO TABPRO (DONO, NOME, AREAGERAL, DIFICULDADE, SITUACAO) VALUES (?,?,?,?,?);";
                    
        String sql2 = "SET @ULT_ID_PRO = LAST_INSERT_ID(); ";
                     
        String sql3 = "INSERT INTO TABPROPER (PROVA, PERGUNTA) VALUES "+sqlAdicional;
                     
        stmt = con.prepareStatement(sql);

        stmt.setInt(1, usuLogado);
        stmt.setString(2, p.getNomeProva());
        stmt.setInt(3, p.getAreaGeral().getCodArea());
        stmt.setInt(4, p.getDificuldade());
        stmt.setString(5, String.valueOf(p.getSituacao()));

        stmt.execute();
        stmt.close();
        
        stmt2 = con.prepareStatement(sql2);
        
        stmt2.execute();
        stmt2.close();
        
        stmt3 = con.prepareStatement(sql3);
        
        for (Pergunta cadPerSel1 : cadPerSel){
          cont++;
          stmt3.setInt(cont, cadPerSel1.getCodPergunta());
        }
        
        stmt3.execute();
        stmt3.close();
        
        con.commit();

        } catch (SQLException e) {
          try {
            con.rollback();
            GravaLogErro("ERR", idUnico, "Erro ao cadastrar prova\n"+e.toString());
          } catch (SQLException ex) {
            GravaLogErro("ERR", idUnico, "Erro ao cadastrar prova\n"+ex.toString());
          }
          return e.getErrorCode();
        }

        return -1;
      } finally {
        try {
          if(stmt != null){
            stmt.close();
          }
          if(stmt2 != null){
            stmt2.close();
          }
          if(stmt3 != null){
            stmt3.close();
          }
          con.setAutoCommit(true);
          con.close();
        } catch (SQLException ex) {
          GravaLogErro("ERR", idUnico, "Erro ao cadastrar prova\n"+ex.toString());
        }
      }
  }
}
