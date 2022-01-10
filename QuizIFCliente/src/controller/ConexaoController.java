
//Mateus Roberto Algayer - 28/12/2021 :: Criação

package controller;

import ModelDominio.Comum;
import ModelDominio.Prova;
import ModelDominio.Usuario;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import util.CriptoHash;
import util.Metodos;
import static util.Metodos.GravaLogErro;
import static util.Metodos.GravaLog;
import view.FormConfirmaCodigoEmail;
import view.FormConfirmaSenha;

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
      GravaLog("LOG", 0, "Efetuar login - INI");
      
      wOut.writeObject("EFETUARLOGIN");
      
      msg = (String) wIn.readObject();
      wOut.writeObject(pUsu.getEmail());
      
      String sal = (String)wIn.readObject();
      
      if(sal.isEmpty()){
        return null;
      }
      
      pUsu.setSenha(CriptoHash.Cripto(pUsu.getSenha(), sal, 0));
      
      wOut.writeObject(pUsu);
      
      wUsu = (Usuario) wIn.readObject();
      GravaLog("LOG", 0, "Efetuar login - FIM");
      
    } catch (IOException | ClassNotFoundException e) {
      GravaLogErro("ERR", 0, "Erro ao enviar o form de login\n"+e.toString());
    }
    
    return wUsu;
  }
  
  public ArrayList<Prova> getProvas(int usuEspec){
    
    GravaLog("GET", 0, "Provas - INI");
    String msg = "";
    try {
      wOut.writeObject("GETLISTAPROVAS");
      
      msg = (String) wIn.readObject();
      wOut.writeObject(InfoApp.getGUsuLogado());
      wOut.writeObject(usuEspec);
      
      ArrayList<Prova> listaProvas = (ArrayList<Prova>) wIn.readObject();
      
      GravaLog("GET", 0, "Provas - FIM");
      return listaProvas;
      
    } catch (IOException | ClassNotFoundException e) {
      GravaLogErro("ERR", 0, "Erro ao enviar listaProvas\n"+e.toString());
    }
    
    return null; 
  }
  
  public boolean EnviaCodigoEmail(String pEmail){
    String msg = "";
    GravaLog("VAL", 0, "Codigo email - INI");
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
      } else if(sal.equals("EmailExiste")){
        Metodos.Erro("Cadastro", "O e-mail informado já está em uso");
        return false;
      }
      
      boolean continua = true;
      boolean rep = false;
      int cont = 0;
      
      InfoApp.setGCodConfirmacao("");
      //João Jorge - 04/01/2022 :: Criação
      while(continua){
        GravaLog("VAL", 0, "Codigo email rep:"+(cont++));
        
        FormConfirmaCodigoEmail frm = new FormConfirmaCodigoEmail(rep);
        frm.setModal(true);
        frm.setVisible(true);

        switch (InfoApp.getGCodConfirmacao()) {
          case "Fechou" -> {
            if(Metodos.msgConfirma("Deseja interromper o processo de verificação de código via email? \n O Cadastro será perdido.")){
              wOut.writeObject("Cancelar");
            } else {
              wOut.writeObject("");
            }
          }
          default -> wOut.writeObject(CriptoHash.Cripto(InfoApp.getGCodConfirmacao(), sal, 0));
        }
        
        msg = (String) wIn.readObject();
        
        switch (msg) {
            case "ok" -> {
              GravaLog("VAL", 0, "Codigo email - FIM");
              return true;
            }
            case "Cancelei" -> {
              Metodos.Aviso("Cadastro", "Cadastro cancelado com sucesso");
              GravaLog("VAL", 0, "Codigo email - FIM");
              return false;
            }
            default -> {
              //Continua no próximo episodio
              rep = true;
            }
          }
      }
    } catch (IOException | ClassNotFoundException e) {
       GravaLogErro("ERR", 0, "Erro ao enviar código via Email\n"+e.toString());
    }
    return false;
  }
  
  public boolean CadastraUsu(Usuario usu){
    GravaLog("INS", 0, "Usuário - INI");
    
    String msg = "";
    try {
      wOut.writeObject("CADASTROUSU");
      
      msg = (String) wIn.readObject();
      
      wOut.writeObject(usu);
      
      GravaLog("INS", 0, "Usuário - FIM");

      msg = (String)wIn.readObject();
      
      return msg.equals("ok");
    } catch (IOException | ClassNotFoundException e) {
      GravaLogErro("ERR", 0, "Erro ao cadastrar usuário\n"+e.toString());
    }
    
    return false; 
  }
  
  public boolean EnviaRedefSenha(String email){
    String msg;
    try {
      GravaLog("UPD", 0, "Redefinir senha - INI");
      
      wOut.writeObject("REDEFINESENHA");
      
      msg = (String) wIn.readObject();
      wOut.writeObject(email);
      
      String sal = (String)wIn.readObject();
      
      if(sal.isEmpty()){
        return false;
      }
      
      boolean continua = true; 
      int cont = 0;
      boolean rep = false;
      
      while(continua){
        GravaLog("VAL", 0, "Codigo email rep:"+(cont++));
        
        FormConfirmaCodigoEmail frm = new FormConfirmaCodigoEmail(rep);
        frm.setModal(true);
        frm.setVisible(true);

        switch (InfoApp.getGCodConfirmacao()) {
          case "Fechou" -> {
            if(Metodos.msgConfirma("Deseja interromper o processo de verificação de código via email? \n A redefinição de senha será perdida.")){
              wOut.writeObject("Cancelar");
            } else {
              wOut.writeObject("");
            }
          }
          default -> wOut.writeObject(CriptoHash.Cripto(InfoApp.getGCodConfirmacao(), sal, 0));
        }
        
        msg = (String) wIn.readObject();
        
        switch (msg) {
            case "ok" -> {
              GravaLog("UPD", 0, "Redefinir senha - email - FIM");
              continua = false;
            }
            case "Cancelei" -> {
              Metodos.Aviso("Redefinir senha", "Redefinir senha cancelado");
              GravaLog("UPD", 0, "Redefinir senha - email - FIM");
              return false;
            }
            default -> {
              rep = true;
            }
          }
      }
      
      continua = true;
      cont = 0;
      
      InfoApp.setGSenhaCripto("");
      GravaLog("UPD", 0, "Redefinir senha - senha - INI");
      
      while(continua){
        GravaLog("UPD", 0, "Codigo email rep:"+(cont++));
        
        FormConfirmaSenha frm = new FormConfirmaSenha(sal);
        frm.setModal(true);
        frm.setVisible(true);

        if(InfoApp.getGSenhaCripto().equals("Fechou")) {
          if(Metodos.msgConfirma("Deseja interromper o processo de redefinição de senha?")){
            wOut.writeObject(null);
            return false;
          } 
        } else if(!InfoApp.getGSenhaCripto().equals("")){
          //altera
          Comum novoUsu = new Comum(email, InfoApp.getGSenhaCripto());
          
          wOut.writeObject(novoUsu);
          
          GravaLog("CAD", 0, "Cadastro - FIM");
          return ((String)wIn.readObject()).equals("ok");
        }
      }
      
    }catch(IOException | ClassNotFoundException e){
      GravaLogErro("ERR", 0, "Erro ao redefinir a senha\n"+e.toString());
      return false;
    }
    return false;
  }
}
