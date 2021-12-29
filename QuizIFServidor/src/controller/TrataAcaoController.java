
//Mateus Roberto Algayer - 27/12/2021 :: Criação

package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import static util.Metodos.GravaLog;
import static util.Metodos.GravaLogErro;

public class TrataAcaoController extends Thread{
  
  private final ObjectOutputStream out;
  private final ObjectInputStream in;
  private final Socket cliente;
  private final int idUnico;

  public TrataAcaoController(ObjectOutputStream pOut, ObjectInputStream pIn, Socket pCliente, int pIdUnico) {
    this.out = pOut;
    this.in = pIn;
    this.cliente = pCliente;
    this.idUnico = pIdUnico;
  }

  @Override
  public void run() {
    String wCom;
    
    GravaLog("CLI", idUnico, "Esperando comando");
    
    try {
      wCom = (String) in.readObject();
      
      while(!wCom.equalsIgnoreCase("FIM")){
        
        GravaLog("CLI", idUnico, "Enviou o comando: '"+wCom+"'");
        
      }
      
    } catch (IOException | ClassNotFoundException e) {
      GravaLogErro("ERR", idUnico, e.toString());
    }
    
    GravaLog("CLI", idUnico, "Fechando Conexão");
    
    try {
      in.close();
      out.close();
    } catch (IOException e) {
      GravaLogErro("ERR", idUnico, e.toString());
    }
  }
  
  
}
