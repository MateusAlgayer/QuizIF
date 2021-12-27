//Mateus Roberto Algayer - 16/12/2021 :: Criação

package view;

import factory.Conector;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import util.Metodos;
import java.sql.*;

public class ProjetoFinalServidor {

    public static void main(String[] args) {
        
        Connection con;
        con = Conector.getConnection();

        try {
          ServerSocket servidor = new ServerSocket(12345);
          Metodos.GravaLog("CON",0,"Usuário conectado");

          ConexaoUsu conex = new ConexaoUsu(servidor, con);

        } catch(IOException e){
            Metodos.GravaLogErro("ERR", 0, e.toString());
        }
        
    }
    
}

class ConexaoUsu extends Thread{
        private ServerSocket serv;
        private int idConex = 0;
        private Connection con;

        public ConexaoUsu(ServerSocket serv, Connection con) {
          this.serv = serv;
          this.con = con;
          idConex = 0;
          this.start();
        }

        @Override
        public void run() {
            try{
                while(true){
                  Socket cliente = serv.accept();
                  ObjectInputStream in = new ObjectInputStream(cliente.getInputStream());
                  ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());
                  idConex++;
                  
                  Metodos.GravaLog("CON", idConex, "Conexão cliente");

                  //a implementar
                  //TrataClienteController tratacliente = new TrataClienteController(out, in, cliente, idUnico);
                }
              } catch(IOException e){
                Metodos.GravaLogErro("ERR", idConex, e.toString());
              }
        }
               
    }
