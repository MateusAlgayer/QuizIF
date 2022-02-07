//Mateus Roberto Algayer - 16/12/2021

package factory;

import java.sql.*;
import util.Metodos;

public class Conector {
    private static Connection con;
    
    public static Connection getConnection(){
        try {
            String url = "jdbc:mysql://localhost:3306/";
            String banco = "quizif";
            String usuario = "root";
            String senha = "";
            
            con = DriverManager.getConnection(url+banco, usuario, senha);
            Metodos.gravaLog("CON", 0, "Iniciada a conexão com o banco de dados");
            return con;
                    
        } catch (SQLException e) {
            Metodos.gravaLogErro("ERR", 0, e.toString());
            return null;
        }
    }
}
