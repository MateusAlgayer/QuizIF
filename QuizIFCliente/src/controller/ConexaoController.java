
//Mateus Roberto Algayer - 28/12/2021 :: Criação
//João Felipe Staub - 18/01/2022 :: getRanking
//João Felipe Staub - 19/01/2022 :: getUsuarios
//João Felipe Staub - 19/01/2022 :: AlteraTipoUsu

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
import view.FormConfirmaCodigoEmail;
import view.FormConfirmaSenha;
import static util.Metodos.gravaLog;
import static util.Metodos.gravaLogErro;

public class ConexaoController {
    
  private final Socket socket;
  private final ObjectOutputStream out;
  private final ObjectInputStream in;

  public ConexaoController(Socket pSocket, ObjectOutputStream pOut, ObjectInputStream pIn) {
    this.socket = pSocket;
    this.out = pOut;
    this.in = pIn;
  }
  
  public Usuario login(Usuario pUsu){
    Usuario wUsu = null;
    
    String msg;
    try {
      gravaLog("LOG", 0, "Efetuar login - INI");
      
      out.writeObject("EFETUARLOGIN");
      
      msg = (String) in.readObject();
      
      if(!msg.equals("ok"))
        throw new IOException("Servidor não pode processar a requisição");
      
      out.writeObject(pUsu.getEmail());
      
      String sal = (String)in.readObject();
      
      if(sal.isEmpty()){
        return null;
      }
      
      pUsu.setSenha(CriptoHash.Cripto(pUsu.getSenha(), sal, 0));
      
      out.writeObject(pUsu);
      
      wUsu = (Usuario) in.readObject();
      gravaLog("LOG", 0, "Efetuar login - FIM");
      
    } catch (IOException | ClassNotFoundException e) {
      gravaLogErro("ERR", 0, "Erro ao enviar o form de login\n"+e.toString());
    }
    
    return wUsu;
  }
  
  public ArrayList<Prova> getProvas(int usuEspec){
    
    gravaLog("GET", 0, "Provas - INI");
    String msg;
    try {
      out.writeObject("GETLISTAPROVAS");
      
      msg = (String) in.readObject();
      
      if(!msg.equals("ok"))
        throw new IOException("Servidor não pode processar a requisição");
      
      out.writeObject(InfoApp.getGUsuLogado());
      
      msg = (String) in.readObject();      
      out.writeObject(usuEspec);
      
      ArrayList<Prova> listaProvas = new ArrayList<>();
              
      listaProvas.addAll((ArrayList<Prova>) in.readObject());
      
      gravaLog("GET", 0, "Provas - FIM");
      return listaProvas;
      
    } catch (IOException | ClassNotFoundException e) {
      gravaLogErro("ERR", 0, "Erro ao enviar listaProvas\n"+e.toString());
    }
    
    return null; 
  }
  
  public String enviaCodigoEmail(String pEmail){
    String msg;
    gravaLog("VAL", 0, "Codigo email - INI");
    try {
      out.writeObject("VALIDACODIGOEMAIL");
      
      if (!((String)in.readObject()).equals("ok")){
        Metodos.gravaLog("REQ", 0, "Ocorreu um erro ao requisitar codigo via email");
        return "E^Erro ao requesitar email!";
      }
      
      out.writeObject(pEmail);
      
      String sal = (String) in.readObject();
      
      if(sal.equals("")){
        Metodos.gravaLog("REQ", 0, "Ocorreu um problema na criptografia");
        return "E^Ocorreu um problema na criptografia!";
      } else if(sal.equals("EmailExiste")){
        return "A^Email informado ja está em uso!";
      }
      
      //boolean continua = true;
      //boolean rep = false;
      int cont = 0;
      
      InfoApp.setGCodConfirmacao("");
      //João Jorge - 04/01/2022 
      
        gravaLog("VAL", 0, "Codigo email rep:"+(cont++));
        
        FormConfirmaCodigoEmail frm = new FormConfirmaCodigoEmail(false);
        frm.setModal(true);
        frm.setVisible(true);

        switch (InfoApp.getGCodConfirmacao()) {
          case "Fechou" -> {
            if(Metodos.msgConfirma("Deseja interromper o processo de verificação de código via email? \n O Cadastro será perdido.")){
              out.writeObject("Cancelar");
            }
          }
          default -> out.writeObject(CriptoHash.Cripto(InfoApp.getGCodConfirmacao(), sal, 0));
        }
        
        msg = (String) in.readObject();
        
        switch (msg) {
            case "S^ok" -> {
              gravaLog("VAL", 0, "Codigo email - FIM");
              return "S^Ok";
            }
            case "Cancelei" -> {
              gravaLog("VAL", 0, "Codigo email - FIM");
              return "A^Cadastro cancelado!";
            }
            default -> {
              return "E^Código inválido! Cadastro cancelado!";
            }
          }
      
    } catch (IOException | ClassNotFoundException e) {
       gravaLogErro("ERR", 0, "Erro ao enviar código via Email\n"+e.toString());
    }
    return "E^Erro ao enviar o código via Email!";
  }
  
  public String cadastraUsu(Usuario usu){
    gravaLog("INS", 0, "Usuário - INI");
    
    String msg;
    try {
      out.writeObject("CADASTROUSU");
      
      msg = (String) in.readObject();
      
      if(!msg.equals("ok"))
        throw new IOException("Servidor não pode processar a requisição");
      
      out.writeObject(usu);
      
      gravaLog("INS", 0, "Usuário - FIM");

      msg = (String)in.readObject();
      
      return msg;
    } catch (IOException | ClassNotFoundException e) {
      gravaLogErro("ERR", 0, "Erro ao cadastrar usuário\n"+e.toString());
      return "Erro na comunicação com o servidor:\n"+e.toString(); 
    }
  }
  
  public String enviaRedefSenha(String email){
    String msg;
    try {
      gravaLog("UPD", 0, "Redefinir senha - INI");
      
      out.writeObject("REDEFINESENHA");
      
      msg = (String) in.readObject();
      
      if(!msg.equals("ok"))
        throw new IOException("Servidor não pode processar a requisição");
      
      out.writeObject(email);
      
      String sal = (String)in.readObject();
      
      if(sal.isEmpty()){
        return "E^O E-mail especificado não pode ser encontrado!";
      }
      
      //boolean continua = true; 
      int cont = 0;
      //boolean rep = false;
      
      //while(continua){
        gravaLog("VAL", 0, "Codigo email rep:"+(cont++));
        
        FormConfirmaCodigoEmail frm = new FormConfirmaCodigoEmail(false);
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
            case "S^ok" -> {
              gravaLog("UPD", 0, "Redefinir senha - email - FIM");
              //continua = false;
            }
            case "Cancelei" -> {
              gravaLog("UPD", 0, "Redefinir senha - email - FIM");
              return "A^Redefinição de senha cancelada!";
            }
            default -> {
              //rep = true;
              return "E^O processo sera cancelado";
            }
          }
      //}
      
      //continua = true;
      cont = 0;
      
      InfoApp.setGSenhaCripto("");
      gravaLog("UPD", 0, "Redefinir senha - senha - INI");
      
      //while(continua){
        gravaLog("UPD", 0, "Codigo email rep:"+(cont++));
        
        FormConfirmaSenha form = new FormConfirmaSenha(sal, false);
        form.setModal(true);
        form.setVisible(true);

        if(InfoApp.getGSenhaCripto().equals("Fechou")) {
          if(Metodos.msgConfirma("Deseja interromper o processo de redefinição de senha?")){
            out.writeObject(null);
            return "A^Redefinição de senha cancelada!";
          } 
        } else if(!InfoApp.getGSenhaCripto().equals("")){
          //altera
          Comum novoUsu = new Comum(email, InfoApp.getGSenhaCripto());
          
          out.writeObject(novoUsu);
          
          gravaLog("CAD", 0, "Cadastro - FIM");
          return (String)in.readObject();
        }
      //}
      
    }catch(IOException | ClassNotFoundException e){
      gravaLogErro("ERR", 0, "Erro ao redefinir a senha\n"+e.toString());
      return "E^Erro na comunicação entre cliente/servidor\n"+e.toString();
    }
    return "E^Erro na comunicação entre cliente/servidor";
  }

  public ArrayList<Area> getListaArea() {
    ArrayList<Area> lista = null;
    
    gravaLog("GET", 0, "Areas - INI");
    try {
      out.writeObject("GETLISTAAREA");     
      
      lista = (ArrayList<Area>) in.readObject();
      
      gravaLog("GET", 0, "Areas - FIM");
      return lista;
      
    } catch (IOException | ClassNotFoundException e) {
      gravaLogErro("ERR", 0, "Erro na requisição de areas\n"+e.toString());
    }
    
    return lista; 
  }

  public void getPerguntasProva(ArrayList<Pergunta> listaSel, ArrayList<Pergunta> listaDis, int numProva) {
    gravaLog("GET", 0,"Perguntas de uma prova - INI");
    String msg;
    try{
      out.writeObject("GETPERGUNTASPROVA");
      
      msg = (String) in.readObject();
      
      if(!msg.equals("ok"))
        throw new IOException("Servidor não pode processar a requisição");
      
      out.writeObject(numProva);
      
      listaDis.addAll((ArrayList<Pergunta>) in.readObject());
      
      if(numProva != 0){
        out.writeObject("ok");
        listaSel.addAll((ArrayList<Pergunta>) in.readObject());
      }
      
    } catch(IOException | ClassNotFoundException e){
      gravaLogErro("ERR", 0, "Erro na requisição de perguntas de uma prova\n"+e.toString());
    }
    
    gravaLog("GET", 0,"Perguntas de uma prova - FIM");
  }

  public String excluirProva(int codigoProva) {
    String msg;
    try {
      out.writeObject("DELETAPROVA");
      msg = (String) in.readObject();
      
      if(!msg.equals("ok"))
        throw new IOException("Servidor não pode processar a requisição");
      
      out.writeObject(codigoProva);
      msg = (String) in.readObject();

//      return switch (msg) {
//        case "ok" -> 0;
//        case "jautiliz" -> 1;
//        default -> 2;
//      };
      return msg;
    } catch (IOException | ClassNotFoundException e) {
        gravaLogErro("ERRO", 0, "Erro ao deletar prova \n" + e.toString());
        return "Erro ao deletar a prova:\n"+e.toString();
    }
  }
  
  public String enviaDelUsu(String email){
    String msg;
    try {
      gravaLog("DEL", 0, "Deletar Usuario - INI");
      
      out.writeObject("VALIDACODIGOEMAILDELUSU");
      
      msg = (String) in.readObject();
      
      if(!msg.equals("ok"))
        throw new IOException("Servidor não pode processar a requisição");
      
      out.writeObject(email);
      
      String sal = (String)in.readObject();
      
      if(sal.isEmpty()){
        return "E^Erro na comunicação entre cliente/servidor";
      }
      
      boolean continua = true; 
      int cont = 0;
      boolean rep = false;
      
      
        gravaLog("DEL", 0, "Codigo email rep:"+(cont++));
        
        FormConfirmaCodigoEmail frm = new FormConfirmaCodigoEmail(rep);
        frm.setModal(true);
        frm.setVisible(true);

        switch (InfoApp.getGCodConfirmacao()) {
          case "Fechou" -> {
            if(Metodos.msgConfirma("Deseja interromper o processo de verificação de código via email? ")){
              out.writeObject("Cancelar");
            }
          }
          default -> out.writeObject(CriptoHash.Cripto(InfoApp.getGCodConfirmacao(), sal, 0));
        }
        
        msg = (String) in.readObject();
        
        switch (msg) {
            case "S^ok" -> {
              gravaLog("DEL", 0, "Excluir Usuario - email - FIM");
              //continua = false;
              return "S^ok";
            }
            case "Cancelei" -> {
              gravaLog("DEL", 0, "Deletar Usuario - email - FIM");
              return "A^Processo de exclusão de usuário cancelado!";
            }
            default -> {
              return "E^Código Inválido, Processo cancelado";
            }
          }
               
    }catch(IOException | ClassNotFoundException e){
      gravaLogErro("ERR", 0, "Erro ao deletar o usuário\n"+e.toString());
      return "E^Erro na comunicação cliente/servidor\n"+e.toString();
    }
  }
  
  public String deletaUsu (Usuario usu){
      String msg;
        try {
            out.writeObject("Deletar Usuário");
            msg = (String) in.readObject();
            
            if(!msg.equals("ok"))
              throw new IOException("Servidor não pode processar a requisição");
            
            out.writeObject(usu);
            msg = (String) in.readObject();
                     
            return msg;
        } catch (IOException | ClassNotFoundException e) {
            gravaLogErro("ERRO", 0, "Erro ao deletar Usuário \n" + e.toString());
            return "E^Erro na comunicação entre cliente/servidor\n"+e.toString();
        }
  }
  
  public String alteraTipoUsu(Usuario usu, String tipo){
      String msg;
      
      try {
          
          out.writeObject("AlteraTipoUsu");
          msg = (String) in.readObject();
          
          if(!msg.equals("ok"))
            throw new IOException("Servidor não pode processar a requisição");
          
          out.writeObject(usu);
          out.writeObject(tipo);
          msg = (String) in.readObject();
          
          return msg;
      } catch (IOException | ClassNotFoundException e) {
        gravaLogErro("ERR", 0, "Erro ao alterar usuário \n" + e.toString());
        return "Erro interno do servidor:\n"+e.toString();
      }
  }
  
  public String alteraSenhaUsu (){
      String msg;
      try {
          
          String sal = InfoApp.getGUsuLogado().getSal();
          
          FormConfirmaSenha frm = new FormConfirmaSenha(sal, true);
          frm.setModal(true);
          frm.setVisible(true);
          
          if(InfoApp.getGSenhaCripto().equals("Fechou")){
              return "A^Processo de alteração de senha cancelado!";
          }
          
          out.writeObject("Alterar Senha Usuário");
          msg = (String) in.readObject();
          
          if(!msg.equals("ok"))
            throw new IOException("Servidor não pode processar a requisição");

          Usuario usu = new Usuario(InfoApp.getGUsuLogado().getEmail(), InfoApp.getGSenhaCripto());
          out.writeObject(usu);
          
          msg = (String) in.readObject();
          
          return msg;
      } catch (IOException | ClassNotFoundException e) {
        gravaLogErro("ERRO", 0, "Erro ao alterar senha \n" + e.toString());
        return "E^Erro na comunicação entre cliente/servidor\n"+e.toString();
      }
  }

  public String inserirProva(Prova p,ArrayList<Pergunta> perSel) {
    gravaLog("CAD", 0,"Cadastro de prova - INI");
    String msg;
    try{
      out.writeObject("INSERIRPROVA");
      
      msg = (String) in.readObject();
      
      if(!msg.equals("ok"))
        throw new IOException("Servidor não pode processar a requisição");
      
      out.writeObject(p);
      out.writeObject(perSel);
          
      gravaLog("CAD", 0,"Cadastro de prova - FIM");
      return (String)in.readObject();
      
    } catch(IOException | ClassNotFoundException e){
      gravaLogErro("ERR", 0, "Erro no cadastro de provas\n"+e.toString());
      return "Erro na comunicação entre cliente/servidor\n"+e.toString();
    }
  }

  public String modificarProva(Prova p, ArrayList<Pergunta> cadPerSel) {
    gravaLog("UPD", 0,"Alteração de prova - INI");
    String msg;
    try{
      out.writeObject("ALTERARPROVA");
      
      msg = (String) in.readObject();
      
      if(!msg.equals("ok"))
        throw new IOException("Servidor não pode processar a requisição");
      
      out.writeObject(p);
      out.writeObject(cadPerSel);
          
      gravaLog("CAD", 0,"Alteração de prova - FIM");
      return (String)in.readObject();
      
    } catch(IOException | ClassNotFoundException e){
      gravaLogErro("ERR", 0, "Erro ao editar prova\n"+e.toString());
      return "Erro na comunicação entre cliente/servidor\n"+e.toString();
    }
  }
  
  public ArrayList<Jogo> getRanking(int filtro){
    gravaLog("GET", 0, "Ranking - INI");
    String msg;
    
    try {
      out.writeObject("GETRANKING");
      
      msg = (String) in.readObject();
      
      if(!msg.equals("ok"))
        throw new IOException("Servidor não pode processar a requisição");
      
      out.writeObject(filtro);
      
      ArrayList<Jogo> listaJogos = (ArrayList<Jogo>) in.readObject();
      
      gravaLog("GET", 0, "Ranking - FIM");
      return listaJogos;
      
    } catch (IOException | ClassNotFoundException e) {
      gravaLogErro("ERR", 0, "Erro ao enviar Ranking\n"+e.toString());
    }
    
    return null;
  }
  
  public ArrayList<Usuario> getUsuarios(){
      gravaLog("GET", 0, "Usuarios - INI");
      String msg;
      
      try {
          out.writeObject("GETUSU");
          
          msg = (String) in.readObject();
          
          if(!msg.equals("ok"))
            throw new IOException("Servidor não pode processar a requisição");
          
          out.writeObject("ok");
          
          ArrayList<Usuario> listaUsuarios = (ArrayList<Usuario>) in.readObject();
          
          gravaLog("GET", 0, "Usuarios - FIM");
          return listaUsuarios;
          
      } catch (IOException | ClassNotFoundException e) {
          gravaLogErro("ERR", 0, "Erro ao enviar Usuarios\n"+e.toString());
      }
      return null;
  }

  public void getPerguntasJogo(ArrayList<Pergunta> listaPergunta, int codigoProva) {
    gravaLog("GET", 0,"perguntas jogo - INI");
    String msg;
    try{
      out.writeObject("GETPERGUNTASJOGO");
      
      msg = (String) in.readObject();
      
      if(!msg.equals("ok"))
        throw new IOException("Servidor não pode processar a requisição");
      
      out.writeObject(codigoProva);

      listaPergunta.addAll((ArrayList<Pergunta>) in.readObject());
      
    } catch(IOException | ClassNotFoundException e){
      gravaLogErro("ERR", 0, "Erro na requisição de perguntas jogo\n"+e.toString());
    }
    
    gravaLog("GET", 0,"perguntas jogo - FIM");
  }

  public ArrayList<Pergunta> getPerguntas() {
    gravaLog("GET", 0,"perguntas - INI");
    String msg;
    try{
      out.writeObject("GETPERGUNTAS");
      
      msg = (String) in.readObject();
      
      if(!msg.equals("ok"))
        throw new IOException("Servidor não pode processar a requisição");
      
      gravaLog("GET", 0,"perguntas - FIM");
      return (ArrayList<Pergunta>) in.readObject();
      
    } catch(IOException | ClassNotFoundException e){
      gravaLogErro("ERR", 0, "Erro na requisição de perguntas\n"+e.toString());
    }
    
    return null;
  }
  
  public ArrayList<Prova> getProvasHist(){
    
    gravaLog("GET", 0, "ProvasHist - INI");
    String msg;
    try {
      out.writeObject("GETLISTAPROVASHIST");
      
      msg = (String) in.readObject();
      
      if(!msg.equals("ok"))
        throw new IOException("Servidor não pode processar a requisição");
      
      ArrayList<Prova> listaProvas = (ArrayList<Prova>) in.readObject();
      
      gravaLog("GET", 0, "ProvasHist - FIM");
      return listaProvas;
      
    } catch (IOException | ClassNotFoundException e) {
      gravaLogErro("ERR", 0, "Erro ao enviar ProvasHist\n"+e.toString());
    }
    
    return null; 
  }

  public String excluirPergunta(int codigo) {
    String msg;
    gravaLog("DEL", 0, "Deletar pergunta - INI");
    
    try {
      
      out.writeObject("DELETAPERGUNTA");
      msg = (String) in.readObject();
      
      if(!msg.equals("ok"))
        throw new IOException("Servidor não pode processar a requisição");
      
      out.writeObject(codigo);
      msg = (String) in.readObject();

      gravaLog("DEL", 0, "Deletar pergunta - FIM");
      return msg;//switch (msg) {
        //case "ok" -> 0;
        //case "jautiliz" -> 1;
        //default -> 2;
      //};
    } catch (IOException | ClassNotFoundException e) {
        gravaLogErro("ERRO", 0, "Erro ao deletar pergunta \n" + e.toString());
        return "Erro na comunicação entre cliente/servidor\n"+e.toString();
    }
  }

  public String inserePergunta(Pergunta p) {
    gravaLog("CAD", 0,"Cadastro de pergunta - INI");
    String msg;
    try{
      out.writeObject("INSERIRPERGUNTA");
      
      msg = (String) in.readObject();
      
      if(!msg.equals("ok"))
        throw new IOException("Servidor não pode processar a requisição");
      
      out.writeObject(p);
          
      gravaLog("CAD", 0,"Cadastro de pergunta - FIM");
      return (String)in.readObject();
      
    } catch(IOException | ClassNotFoundException e){
      gravaLogErro("ERR", 0, "Erro no cadastro de pergunta\n"+e.toString());
      return "Erro na comunicação entre cliente/servidor\n"+e.toString();
    }
  }

  public String alteraPergunta(Pergunta p) {
    gravaLog("UPD", 0,"Alteração de pergunta - INI");
    String msg;
    try{
      out.writeObject("ALTERARPERGUNTA");
      
      msg = (String) in.readObject();
      
      if(!msg.equals("ok"))
        throw new IOException("Servidor não pode processar a requisição");
      
      out.writeObject(p);
          
      gravaLog("CAD", 0,"Alteração de pergunta - FIM");
      return (String)in.readObject();
      
    } catch(IOException | ClassNotFoundException e){
      gravaLogErro("ERR", 0, "Erro ao alterar pergunta\n"+e.toString());
      return "Erro na comunicação entre cliente/servidor\n"+e.toString();
    }
  }

  public String gravaResultJogo(Jogo resultJogo) {
    gravaLog("INS", 0,"Grava jogo - INI");
    String msg;
    try{
      out.writeObject("GRAVARJOGO");
      
      msg = (String) in.readObject();
      
      if(!msg.equals("ok"))
        throw new IOException("Servidor não pode processar a requisição");
      
      out.writeObject(resultJogo);
          
      gravaLog("INS", 0,"Grava jogo - FIM");
      return (String)in.readObject();
      
    } catch(IOException | ClassNotFoundException e){
      gravaLogErro("ERR", 0, "Erro ao gravar jogo\n"+e.toString());
      return "Erro na comunicação entre cliente/servidor\n"+e.toString();
    }
  }
}
