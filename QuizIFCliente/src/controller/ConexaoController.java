
//Mateus Roberto Algayer - 28/12/2021 :: Criação
//João Felipe Staub - 18/01/2022 :: getRanking

package controller;

import ModelDominio.Comum;
import ModelDominio.Prova;
import ModelDominio.Usuario;
import ModelDominio.Area;
import ModelDominio.Jogo;
import ModelDominio.Pergunta;
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
    
  private final Socket socket;
  private final ObjectOutputStream out;
  private final ObjectInputStream in;

  public ConexaoController(Socket pSocket, ObjectOutputStream pOut, ObjectInputStream pIn) {
    this.socket = pSocket;
    this.out = pOut;
    this.in = pIn;
  }
  
  public Usuario Login(Usuario pUsu){
    Usuario wUsu = null;
    
    String msg = "";
    try {
      GravaLog("LOG", 0, "Efetuar login - INI");
      
      out.writeObject("EFETUARLOGIN");
      
      msg = (String) in.readObject();
      out.writeObject(pUsu.getEmail());
      
      String sal = (String)in.readObject();
      
      if(sal.isEmpty()){
        return null;
      }
      
      pUsu.setSenha(CriptoHash.Cripto(pUsu.getSenha(), sal, 0));
      
      out.writeObject(pUsu);
      
      wUsu = (Usuario) in.readObject();
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
      out.writeObject("GETLISTAPROVAS");
      
      msg = (String) in.readObject();
      out.writeObject(InfoApp.getGUsuLogado());
      out.writeObject(usuEspec);
      
      ArrayList<Prova> listaProvas = (ArrayList<Prova>) in.readObject();
      
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
      out.writeObject("VALIDACODIGOEMAIL");
      
      if (!((String)in.readObject()).equals("ok")){
        Metodos.GravaLog("REQ", 0, "Ocorreu um erro ao requisitar codigo via email");
        return false;
      }
      
      out.writeObject(pEmail);
      
      String sal = (String) in.readObject();
      
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
              out.writeObject("Cancelar");
            } else {
              out.writeObject("");
            }
          }
          default -> out.writeObject(CriptoHash.Cripto(InfoApp.getGCodConfirmacao(), sal, 0));
        }
        
        msg = (String) in.readObject();
        
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
      out.writeObject("CADASTROUSU");
      
      msg = (String) in.readObject();
      
      out.writeObject(usu);
      
      GravaLog("INS", 0, "Usuário - FIM");

      msg = (String)in.readObject();
      
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
      
      out.writeObject("REDEFINESENHA");
      
      msg = (String) in.readObject();
      out.writeObject(email);
      
      String sal = (String)in.readObject();
      
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
              out.writeObject("Cancelar");
            } else {
              out.writeObject("");
            }
          }
          default -> out.writeObject(CriptoHash.Cripto(InfoApp.getGCodConfirmacao(), sal, 0));
        }
        
        msg = (String) in.readObject();
        
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
        
        FormConfirmaSenha frm = new FormConfirmaSenha(sal, false);
        frm.setModal(true);
        frm.setVisible(true);

        if(InfoApp.getGSenhaCripto().equals("Fechou")) {
          if(Metodos.msgConfirma("Deseja interromper o processo de redefinição de senha?")){
            out.writeObject(null);
            return false;
          } 
        } else if(!InfoApp.getGSenhaCripto().equals("")){
          //altera
          Comum novoUsu = new Comum(email, InfoApp.getGSenhaCripto());
          
          out.writeObject(novoUsu);
          
          GravaLog("CAD", 0, "Cadastro - FIM");
          return ((String)in.readObject()).equals("ok");
        }
      }
      
    }catch(IOException | ClassNotFoundException e){
      GravaLogErro("ERR", 0, "Erro ao redefinir a senha\n"+e.toString());
      return false;
    }
    return false;
  }

  public ArrayList<Area> getListaArea() {
    ArrayList<Area> lista = null;
    
    GravaLog("GET", 0, "Areas - INI");
    String msg = "";
    try {
      out.writeObject("GETLISTAAREA");
      
      msg = (String) in.readObject();
      
      lista = (ArrayList<Area>) in.readObject();
      
      GravaLog("GET", 0, "Areas - FIM");
      return lista;
      
    } catch (IOException | ClassNotFoundException e) {
      GravaLogErro("ERR", 0, "Erro na requisição de areas\n"+e.toString());
    }
    
    return lista; 
  }

  public void getPerguntasProva(ArrayList<Pergunta> listaSel, ArrayList<Pergunta> listaDis, int numProva) {
    GravaLog("GET", 0,"Perguntas de uma prova - INI");
    String msg = "";
    try{
      out.writeObject("GETPERGUNTASPROVA");
      
      msg = (String) in.readObject();
      
      out.writeObject(numProva);
      
      listaDis.addAll((ArrayList<Pergunta>) in.readObject());
      
      if(numProva != 0){
        listaSel.addAll((ArrayList<Pergunta>) in.readObject());
      }
      
    } catch(IOException | ClassNotFoundException e){
      GravaLogErro("ERR", 0, "Erro na requisição de perguntas de uma prova\n"+e.toString());
    }
    
    GravaLog("GET", 0,"Perguntas de uma prova - FIM");
  }

  public int ExcluirProva(int codigoProva) {
    String msg;
    try {
      out.writeObject("DELETAPROVA");
      msg = (String) in.readObject();
      out.writeObject(codigoProva);
      msg = (String) in.readObject();

      return switch (msg) {
        case "ok" -> 0;
        case "jautiliz" -> 1;
        default -> 2;
      };
    } catch (IOException | ClassNotFoundException e) {
        GravaLog("ERRO", 0, "Erro ao deletar prova \n" + e.toString());
        return 2;
    }
  }
  
  public boolean EnviaDelUsu(String email){
    String msg;
    try {
      GravaLog("DEL", 0, "Deletar Usuario - INI");
      
      out.writeObject("VALIDACODIGOEMAILDELUSU");
      
      msg = (String) in.readObject();
      out.writeObject(email);
      
      String sal = (String)in.readObject();
      
      if(sal.isEmpty()){
        return false;
      }
      
      boolean continua = true; 
      int cont = 0;
      boolean rep = false;
      
      while(continua){
        GravaLog("DEL", 0, "Codigo email rep:"+(cont++));
        
        FormConfirmaCodigoEmail frm = new FormConfirmaCodigoEmail(rep);
        frm.setModal(true);
        frm.setVisible(true);

        switch (InfoApp.getGCodConfirmacao()) {
          case "Fechou" -> {
            if(Metodos.msgConfirma("Deseja interromper o processo de verificação de código via email? ")){
              out.writeObject("Cancelar");
            } else {
              out.writeObject("");
            }
          }
          default -> out.writeObject(CriptoHash.Cripto(InfoApp.getGCodConfirmacao(), sal, 0));
        }
        
        msg = (String) in.readObject();
        
        switch (msg) {
            case "ok" -> {
              GravaLog("DEL", 0, "Excluir Usuario - email - FIM");
              continua = false;
            }
            case "Cancelei" -> {
              Metodos.Aviso("Deletar Usuario", "Deletar Usuario cancelado");
              GravaLog("DEL", 0, "Deletar Usuario - email - FIM");
              return false;
            }
            default -> {
              rep = true;
            }
          }
      }
      
      return true;
      
    }catch(IOException | ClassNotFoundException e){
      GravaLogErro("ERR", 0, "Erro ao deletar o usuário\n"+e.toString());
      return false;
    }
  }
  
  public boolean DeletaUsu (Usuario usu){
      String msg;
        try {
            out.writeObject("Deletar Usuário");
            msg = (String) in.readObject();
            out.writeObject(usu);
            msg = (String) in.readObject();
                     
            return msg.equals("ok");
        } catch (IOException | ClassNotFoundException e) {
            GravaLog("ERRO", 0, "Erro ao deletar Usuário \n" + e.toString());
            return false;
        }
  }
  
  public boolean AlteraSenhaUsu (){
      String msg;
      try {
          
          String sal = InfoApp.getGUsuLogado().getSal();
          
          FormConfirmaSenha frm = new FormConfirmaSenha(sal, true);
          frm.setModal(true);
          frm.setVisible(true);
          
          if(InfoApp.getGSenhaCripto().equals("Fechou")){
              return false;
          }
          
          out.writeObject("Alterar Senha Usuário");
          msg = (String) in.readObject();
          Usuario usu = new Usuario(InfoApp.getGUsuLogado().getEmail(), InfoApp.getGSenhaCripto());
          out.writeObject(usu);
          msg = (String) in.readObject();
          
          return msg.equals("ok");
      } catch (IOException | ClassNotFoundException e) {
        GravaLog("ERRO", 0, "Erro ao alterar usuário \n" + e.toString());
        return false;
      }
  }

  public boolean InserirProva(Prova p,ArrayList<Pergunta> perSel) {
    GravaLog("CAD", 0,"Cadastro de prova - INI");
    String msg = "";
    try{
      out.writeObject("INSERIRPROVA");
      
      msg = (String) in.readObject();
      
      out.writeObject(p);
      out.writeObject(perSel);
          
      GravaLog("CAD", 0,"Cadastro de prova - FIM");
      return ((String)in.readObject()).equals("ok");
      
    } catch(IOException | ClassNotFoundException e){
      GravaLogErro("ERR", 0, "Erro no cadastro de provas\n"+e.toString());
      return false;
    }
  }

  public boolean ModificarProva(Prova p, ArrayList<Pergunta> cadPerSel) {
    GravaLog("UPD", 0,"Alteração de prova - INI");
    String msg = "";
    try{
      out.writeObject("ALTERARPROVA");
      
      msg = (String) in.readObject();
      
      out.writeObject(p);
      out.writeObject(cadPerSel);
          
      GravaLog("CAD", 0,"Alteração de prova - FIM");
      return ((String)in.readObject()).equals("ok");
      
    } catch(IOException | ClassNotFoundException e){
      GravaLogErro("ERR", 0, "Erro ao editar prova\n"+e.toString());
      return false;
    }
  }
  
  public ArrayList<Jogo> getRanking(int filtro){
    GravaLog("GET", 0, "Ranking - INI");
    String msg = "";
    
    try {
      out.writeObject("GETRANKING");
      
      msg = (String) in.readObject();
      out.writeObject(filtro);
      
      ArrayList<Jogo> listaJogos = (ArrayList<Jogo>) in.readObject();
      
      GravaLog("GET", 0, "Ranking - FIM");
      return listaJogos;
      
    } catch (IOException | ClassNotFoundException e) {
      GravaLogErro("ERR", 0, "Erro ao enviar Ranking\n"+e.toString());
    }
    
    return null;
  }
  
  public void getPerguntasJogo(ArrayList<Pergunta> listaPergunta, int codigoProva) {
    GravaLog("GET", 0,"perguntas jogo - INI");
    String msg = "";
    try{
      out.writeObject("GETPERGUNTASJOGO");
      
      msg = (String) in.readObject();
      
      out.writeObject(codigoProva);

      listaPergunta.addAll((ArrayList<Pergunta>) in.readObject());
      
    } catch(IOException | ClassNotFoundException e){
      GravaLogErro("ERR", 0, "Erro na requisição de perguntas jogo\n"+e.toString());
    }
    
    GravaLog("GET", 0,"perguntas jogo - FIM");
  }
}
