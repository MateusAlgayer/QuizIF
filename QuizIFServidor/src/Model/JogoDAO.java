
//João Felipe Staub - 18/01/2022 :: Criação

package Model;

import ModelDominio.Area;
import ModelDominio.Jogo;
import ModelDominio.Pergunta;
import ModelDominio.Usuario;
import factory.Conector;
import java.sql.*;
import java.util.ArrayList;
import static util.Metodos.GravaLog;
import static util.Metodos.GravaLogErro;

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
      
      String sql = "SELECT TABUSU.NOME, TABUSU.EMAIL, SUM(TABUSUPRO.NUMACERTOS) AS TOTNUMACERTO, " +
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
                                  new Usuario(0,result.getString("NOME"), result.getString("EMAIL")),
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
  
  public ArrayList<Pergunta> getPerguntasJogo(int numProva, int idUnico){
    PreparedStatement stmt = null;
    try {
      try {
        ArrayList<Pergunta> listaSel = new ArrayList<>();

        
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

        GravaLog("SQL", idUnico, "Recuperou um objeto do banco: PerguntasJogo");
        return listaSel;
      } catch (SQLException e) {
        GravaLogErro("ERR",idUnico, e.toString());
        return null;
      }
    } finally {
      try {
        if(stmt != null)
          stmt.close();
        con.close(); 
      } catch (SQLException e) {
        GravaLogErro("ERR",idUnico, e.toString());
        return null;
      }
      
    }
    
  }

  public String GravaResultJogo(Jogo j, int idUnico) {
    //uma query pra testar se o registro já existe e outra para inserir/atualizar
    PreparedStatement stmt = null;
    PreparedStatement stmt2 = null;
    
    try {
      try {
        con.setAutoCommit(false);
        
        int cont = 0;

        String sql = "SELECT EXISTS( SELECT * FROM TABUSUPRO WHERE PROVA = ? AND USUARIO = ?) AS EXISTE;";
        
        //Testa se já existe um resultado gravado para aquele jogo
        stmt = con.prepareStatement(sql);

        stmt.setInt(1, j.getProva().getCodigoProva());
        stmt.setInt(2, j.getJogador().getCodUsuario());

        ResultSet result = stmt.executeQuery();
        
        boolean existe = false;
        
        while(result.next())
          existe = result.getBoolean("EXISTE");
        
        stmt.close();
        
        String sql2;
        
        //prepara uma query caso exista e uma caso não exista
        if(existe){
          sql2 = "UPDATE TABUSUPRO SET NUMPERGUNTAS = ?, NUMACERTOS = ? WHERE PROVA = ? AND USUARIO = ?;";
                 
          stmt2 = con.prepareStatement(sql2);
          
          stmt2.setInt(1, j.getNumPerguntas());
          stmt2.setInt(2, j.getNumAcertos());
          stmt2.setInt(3, j.getProva().getCodigoProva());
          stmt2.setInt(4, j.getJogador().getCodUsuario());
          
        } else {
          sql2 = "INSERT INTO TABUSUPRO (PROVA, USUARIO, NUMPERGUNTAS, NUMACERTOS) VALUES (?,?,?,?);";
                
          stmt2 = con.prepareStatement(sql2);
          
          stmt2.setInt(1, j.getProva().getCodigoProva());
          stmt2.setInt(2, j.getJogador().getCodUsuario());
          stmt2.setInt(3, j.getNumPerguntas());
          stmt2.setInt(4, j.getNumAcertos());
        }
        
        stmt2.execute();
        stmt2.close();
        
        //da commit em tudo
        con.commit();

        } catch (SQLException e) {
          try {
            con.rollback();
            GravaLogErro("ERR", idUnico, "Erro ao gravar jogo\n"+e.toString());
          } catch (SQLException ex) {
            GravaLogErro("ERR", idUnico, "Erro ao gravar jogo\n"+ex.toString());
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
          con.setAutoCommit(true);
          con.close();
        } catch (SQLException ex) {
          GravaLogErro("ERR", idUnico, "Erro ao gravar jogo\n"+ex.toString());
        }
      }
  }
}
