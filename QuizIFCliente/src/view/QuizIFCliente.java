
//Mateus Roberto Algayer - 28/12/2021

package view;

import controller.ConexaoController;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import static util.Metodos.GravaLog;
import static util.Metodos.GravaLogErro;

public class QuizIFCliente {

  public static ConexaoController ccont;
  
  public static void main(String[] args) {
    
    Socket wSocket;
    ObjectOutputStream wOut;
    ObjectInputStream wIn;
    
    
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Windows".equals(info.getName()) || "Linux".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
      GravaLogErro("ERR", 0, "Erro ao definir Look and Feel");
    }
   
    try {
      GravaLog("CON", 0, "Tentando se conectar ao servidor");
      
      wSocket = new Socket("127.0.0.1", 12345);
      wOut = new ObjectOutputStream(wSocket.getOutputStream());
      wIn = new ObjectInputStream(wSocket.getInputStream());
      
      ccont = new ConexaoController(wSocket, wOut, wIn);
      
    } catch (IOException e) {
      GravaLogErro("CON", 0, e.toString());
    }
  }
  
}
