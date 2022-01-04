
//Mateus Roberto Algayer - 28/12/2021 :: Criação

package controller;

import ModelDominio.Prova;
import ModelDominio.Usuario;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import util.CriptoHash;
import util.Metodos;
import static util.Metodos.GravaLogErro;
import view.FormConfirmaCodigoEmail;

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
      GravaLogErro("ERR", 0, "Erro ao enviar o form de login\n"+e.toString());
    }
    
    return wUsu;
  }
  
  public ArrayList<Prova> getProvas(){
    
    String msg = "";
    try {
      wOut.writeObject("GETLISTAPROVAS");
      
      msg = (String) wIn.readObject();
      wOut.writeObject(InfoApp.getGUsuLogado());
      
      ArrayList<Prova> listaProvas = (ArrayList<Prova>) wIn.readObject();
      
      return listaProvas;
      
    } catch (IOException | ClassNotFoundException e) {
      GravaLogErro("ERR", 0, "Erro ao enviar listaProvas\n"+e.toString());
    }
    
    return null; 
  }
  
  public boolean EnviaCodigoEmail(String pEmail){
    String msg = "";
    
    try {
      wOut.writeObject("VALIDACODIGOEMAIL");
      
      if (!((String)wIn.readObject()).equals("ok")){
        Metodos.GravaLog("REQ", 0, "Ocorreu um erro ao requisitar codigo via email");
        return false;
      }
      
      wOut.writeObject(pEmail);
      
      String sal = (String) wIn.readObject();
      
      if(sal.equals("")){
        Metodos.GravaLog("REQ", 0, "Ocorreu um problema na criptografia");
        return false;
      }
      
      boolean continua = true;
      
      InfoApp.setGCodConfirmacao("");
      //João Jorge - 04/01/2022 :: Criação
      while(continua){
        FormConfirmaCodigoEmail frm = new FormConfirmaCodigoEmail();
        frm.setModal(true);
        frm.setVisible(true);

          switch (InfoApp.getGCodConfirmacao()) {
              case "" -> wOut.writeObject("");
              case "Fechou" -> {
                  if(Metodos.msgConfirma("Deseja interromper o processo de verificação de código via email? \n O Cadastro será perdido.")){
                      wOut.writeObject("Cancelar");
                  }
              }
              default -> wOut.writeObject(CriptoHash.Cripto(InfoApp.getGCodConfirmacao(), sal, 0));
          }
        
        msg = (String) wIn.readObject();
        
        switch (msg) {
            case "ok" -> {
                continua = false;
                return true;
              }
              case "Cancelei" -> {
                continua = false;
                return false;
              }
              default -> {
                //Continua no próximo episodio
              }
          }
        }
    } catch (IOException | ClassNotFoundException e) {
       GravaLogErro("ERR", 0, "Erro ao enviar código via Email\n"+e.toString());
    }
    return false;
  }
}
