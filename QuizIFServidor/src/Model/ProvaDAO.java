
//Mateus Roberto Algayer - 30/12/2021

package Model;

import ModelDominio.Area;
import ModelDominio.Pergunta;
import ModelDominio.Prova;
import factory.Conector;
import java.sql.*;
import java.util.ArrayList;
import static util.Metodos.gravaLog;
import static util.Metodos.gravaLogErro;

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
      
      String sql = "SELECT TABPRO.CODIGO, TABPRO.NOME, TABPRO.AREAGERAL, TABARE.NOME AS NOMEAREA, TABPRO.DIFICULDADE, TABPRO.SITUACAO, "+ 
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
                                  result.getString("SITUACAO").charAt(0),                 
                                  result.getInt("PONTUACAO")
                                  ));
      }

      stmt.close();
      con.close();
      
      gravaLog("SQL", Id, "Recuperou um objeto do banco: ListaProvas");
      return listaProvas;
      
    } catch (SQLException e) {
      gravaLogErro("ERR",Id, e.toString());
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
      
      String sql = "SELECT TABPER.CODIGO, TABPER.PERGUNTA,TABARE.CODIGO AS CODAREA, TABARE.NOME, TABPER.DIFICULDADE FROM TABPER "+
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
      
      gravaLog("SQL", Id, "Recuperou um objeto do banco: PerguntasProva");
    } catch (SQLException e) {
      gravaLogErro("ERR",Id, e.toString());
    }
  }

  public String InserirProva(Prova p, ArrayList<Pergunta> cadPerSel, int idUnico, int usuLogado) {
    //precisou executar 3 querys, como o JDBC barra o uso de mais de uma query por statement ent??o 
    //usei 1 statement pra cada query;
    PreparedStatement stmt = null;
    PreparedStatement stmt2 = null;
    PreparedStatement stmt3 = null;
    
    try {
      try {
        con.setAutoCommit(false);
        
        int cont = 0;
        // isso explode se n??o tiver pergunta, por isso as telas de pergunta se baseam na ideia de que h?? ao menos uma pergunta
        String sqlAdicional = "";
        
        for (int x = 0; x < cadPerSel.size();x++)
          sqlAdicional += (sqlAdicional.equals("") ? "" : ",")+"(@ULT_ID_PRO_"+ idUnico + ", ?)";

        String sql = "INSERT INTO TABPRO (DONO, NOME, AREAGERAL, DIFICULDADE, SITUACAO) VALUES (?,?,?,?,?);";
                    
        String sql2 = "SET @ULT_ID_PRO_"+ idUnico + " = LAST_INSERT_ID(); ";
                     
        String sql3 = "INSERT INTO TABPROPER (PROVA, PERGUNTA) VALUES "+sqlAdicional;
        
        //Insere a prova
        stmt = con.prepareStatement(sql);

        stmt.setInt(1, usuLogado);
        stmt.setString(2, p.getNomeProva());
        stmt.setInt(3, p.getAreaGeral().getCodArea());
        stmt.setInt(4, p.getDificuldade());
        stmt.setString(5, String.valueOf(p.getSituacao()));

        stmt.execute();
        stmt.close();
        
        //Seta variavel SQL com o id da ??ltima prova
        stmt2 = con.prepareStatement(sql2);
        
        stmt2.execute();
        stmt2.close();
        
        //insere as perguntas
        stmt3 = con.prepareStatement(sql3);
        
        for (Pergunta cadPerSel1 : cadPerSel){
          cont++;
          stmt3.setInt(cont, cadPerSel1.getCodPergunta());
        }
        
        stmt3.execute();
        stmt3.close();
        
        //da commit em tudo
        con.commit();

        } catch (SQLException e) {
          try {
            con.rollback();
            gravaLogErro("ERR", idUnico, "Erro ao cadastrar prova\n"+e.toString());
          } catch (SQLException ex) {
            gravaLogErro("ERR", idUnico, "Erro ao cadastrar prova\n"+ex.toString());
          }
          return "E^"+e.getErrorCode()+" - "+e.toString();
        }

        return "S^ok";
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
          gravaLogErro("ERR", idUnico, "Erro ao cadastrar prova\n"+ex.toString());
        }
      }
  }

  public String DeletaProva(int prova, int idUnico) {
    PreparedStatement stmt = null;

    try {
        try {

            con.setAutoCommit(false);

            String sql = "DELETE FROM TABPRO "+
                         " WHERE CODIGO = ?";
            stmt = con.prepareStatement(sql);

            stmt.setInt(1, prova);

            stmt.execute();

            con.commit();
            return "S^ok";
        } catch (SQLException e) {
            try {
                gravaLogErro("ERRO", 0, "Erro ao deletar prova \n" + e.toString());
                con.rollback();
                
                if(e.getErrorCode() == 1451)//codigo de erro caso a chave estrangeira j?? esteja em uso e n??o possa ser deletada 
                  return "A^Prova n??o pode ser deletada pois j?? foi realizada por algum usu??rio\n utilize o campo 'Situa????o' para inativar a prova";
                
                return "E^"+e.getErrorCode()+" - "+e.toString(); 
            } catch (SQLException ex) {
                gravaLogErro("ERRO", 0, "Erro ao deletar prova \n" + ex.toString());
                return "E^"+ex.getErrorCode()+" - "+ex.toString();
            }
        }
    } finally {
        try {
            if(stmt != null)
              stmt.close();
            con.setAutoCommit(true);
            con.close();
        } catch (SQLException e) {
            gravaLogErro("ERRO", 0, "Erro ao deletar prova \n" + e.toString());
            return "E^"+e.getErrorCode()+" - "+e.toString();
        }
    }
  }

  public String AlterarProva(Prova p, ArrayList<Pergunta> cadPerSel, int idUnico, int usuLogado) {
    gravaLog("UPD", idUnico, "Editar prova - INI");

    PreparedStatement stmt = null;
    PreparedStatement stmt2 = null;
    PreparedStatement stmt3 = null;
    
    try {
      try {
        con.setAutoCommit(false);
        
        int cont = 1;
        String sqlAdicional = "";
        
        for (int x = 0; x < cadPerSel.size();x++)
          sqlAdicional += (sqlAdicional.equals("") ? "" : ",")+"(?, ?)";

        String sql = "UPDATE TABPRO SET DONO = ?, NOME = ?, AREAGERAL = ?, DIFICULDADE = ?, SITUACAO = ? WHERE CODIGO = ?;";
                     
        String sql2 = "DELETE FROM TABPROPER WHERE PROVA = ?";
        
        String sql3 = "INSERT INTO TABPROPER (PROVA, PERGUNTA) VALUES "+sqlAdicional;
        
        //Altera prova
        stmt = con.prepareStatement(sql);

        stmt.setInt(1, usuLogado);
        stmt.setString(2, p.getNomeProva());
        stmt.setInt(3, p.getAreaGeral().getCodArea());
        stmt.setInt(4, p.getDificuldade());
        stmt.setString(5, String.valueOf(p.getSituacao()));
        stmt.setInt(6, p.getCodigoProva());

        stmt.execute();
        stmt.close();
        
        //deleta as perguntas daquela prova
        stmt2 = con.prepareStatement(sql2);
        
        stmt2.setInt(1, p.getCodigoProva());
        
        stmt2.execute();
        stmt2.close();

        //reinserir perguntas
        stmt3 = con.prepareStatement(sql3);
        
        for (Pergunta cadPerSel1 : cadPerSel){
          stmt3.setInt(cont, p.getCodigoProva());
          stmt3.setInt(cont+1, cadPerSel1.getCodPergunta());
          cont = cont + 2;
        }
        
        stmt3.execute();
        stmt3.close();
        
        con.commit();

        } catch (SQLException e) {
          try {
            con.rollback();
            gravaLogErro("ERR", idUnico, "Erro ao editar prova\n"+e.toString());
          } catch (SQLException ex) {
            gravaLogErro("ERR", idUnico, "Erro ao editar prova\n"+ex.toString());
          }
          return "E^"+e.getErrorCode()+" - "+e.toString();
        }
        
        gravaLog("UPD", idUnico, "Editar prova - FIM");
        return "S^ok";
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
        gravaLogErro("ERR", idUnico, "Erro ao editar prova\n"+ex.toString());
      }
    }
  }

  public ArrayList<Prova> getListaProvaHist(int usu, int idUnico) {
    try {
      PreparedStatement stmt;
      
      String sql = "SELECT TABPRO.CODIGO, TABPRO.NOME, TABPRO.AREAGERAL, TABARE.NOME AS NOMEAREA, "+
                   "TABPRO.DIFICULDADE, TABPRO.SITUACAO, "+
                   "ROUND((TABUSUPRO.NUMACERTOS*100) DIV TABUSUPRO.NUMPERGUNTAS) AS PONTUACAO "+
                   "FROM TABUSUPRO "+
                     "LEFT OUTER JOIN TABPRO ON TABUSUPRO.PROVA = TABPRO.CODIGO "+
                     "LEFT OUTER JOIN TABARE ON TABPRO.AREAGERAL = TABARE.CODIGO "+
                   "WHERE USUARIO = ?";
      
      stmt = con.prepareStatement(sql);
      
      stmt.setInt(1, usu);
      
      ResultSet result = stmt.executeQuery();
      
      ArrayList<Prova> listaProvas = new ArrayList<>();
      
      while(result.next()){
        listaProvas.add(new Prova(result.getInt("CODIGO"),
                                  result.getString("NOME"),
                                  new Area(result.getInt("AREAGERAL"),result.getString("NOMEAREA")),
                                  result.getInt("DIFICULDADE"),
                                  result.getString("SITUACAO").charAt(0),                 
                                  result.getInt("PONTUACAO")
                                  ));
      }

      stmt.close();
      con.close();
      
      gravaLog("SQL", idUnico, "Recuperou um objeto do banco: ListaProvasHist");
      return listaProvas;
      
    } catch (SQLException e) {
      gravaLogErro("ERR",idUnico, e.toString());
    }
    return null;
  }

}
