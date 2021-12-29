
//Mateus Roberto Algayer - 28/12/2021 :: Criação

package controller;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConexaoController {
    
  private final Socket wSocket;
  private final ObjectOutputStream wOut;
  private final ObjectInputStream wIn;

  public ConexaoController(Socket pSocket, ObjectOutputStream pOut, ObjectInputStream pIn) {
    this.wSocket = pSocket;
    this.wOut = pOut;
    this.wIn = pIn;
  }
  
  
}
