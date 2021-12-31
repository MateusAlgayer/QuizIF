
//Mateus Roberto Algayer - 28/12/2021 :: Criação

package controller;

import ModelDominio.Prova;
import ModelDominio.Usuario;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import static util.Metodos.GravaLogErro;

public class ConexaoController {
    
  private final Socket wSocket;
  private final ObjectOutputStream wOut;
  private final ObjectInputStream wIn;

  public ConexaoController(Socket pSocket, ObjectOutputStream pOut, ObjectInputStream pIn) {
    this.wSocket = pSocket;
    this.wOut = pOut;
    this.wIn = pIn;
  }
  
  public Usuario Login(Usuario pUsu){
    Usuario wUsu = null;
    
    String msg = "";
    try {
      wOut.writeObject("EFETUARLOGIN");
      
      msg = (String) wIn.readObject();
      wOut.writeObject(pUsu);
      
      wUsu = (Usuario) wIn.readObject();
      
    } catch (IOException | ClassNotFoundException e) {
      GravaLogErro("ERR", 0, "Erro ao enviar o form de login");
    }
    
    return wUsu;
  }
  
  public ArrayList<Prova> getProvas(){
    
    String msg = "";
    try {
      wOut.writeObject("GETLISTAPROVAS");
      
      msg = (String) wIn.readObject();
      wOut.writeObject(InfoApp.GUsuLogado);
      
      ArrayList<Prova> listaProvas = (ArrayList<Prova>) wIn.readObject();
      
      return listaProvas;
      
    } catch (IOException | ClassNotFoundException e) {
      GravaLogErro("ERR", 0, "Erro ao enviar listaProvas");
    }
    
    return null; 
  }
}
