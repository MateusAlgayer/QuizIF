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

public class QuizIFServ {

    public static void main(String[] args) {
        
        Connection GCon;
        GCon = Conector.getConnection();

        try {
          ServerSocket GServidor = new ServerSocket(12345);
          Metodos.GravaLog("CON",0,"Usuário conectado");

          ConexaoUsu GConex = new ConexaoUsu(GServidor, GCon);
          
          GConex.start();
          
        } catch(IOException e){
            Metodos.GravaLogErro("ERR", 0, e.toString());
        }
        
    }
    
}

class ConexaoUsu extends Thread{
        private final ServerSocket wServ;
        private int wIdConex = 0;
        private final Connection wCon;

        public ConexaoUsu(ServerSocket pServ, Connection pCon) {
          this.wServ = pServ;
          this.wCon = pCon;
          wIdConex = 0;
        }

        @Override
        public void run() {
            try{
                while(true){
                  Socket cliente = wServ.accept();
                  ObjectInputStream in = new ObjectInputStream(cliente.getInputStream());
                  ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());
                  wIdConex++;
                  
                  Metodos.GravaLog("CON", wIdConex, "Conexão cliente");

                  //a implementar
                  //TrataClienteController tratacliente = new TrataClienteController(out, in, cliente, idUnico);
                }
              } catch(IOException e){
                Metodos.GravaLogErro("ERR", wIdConex, e.toString());
              }
        }
               
    }
