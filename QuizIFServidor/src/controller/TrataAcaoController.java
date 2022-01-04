
//Mateus Roberto Algayer - 27/12/2021 :: Criação

package controller;

import Model.ProvaDAO;
import Model.UsuarioDAO;
import ModelDominio.Prova;
import ModelDominio.Usuario;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import util.CriptoHash;
import util.Metodos;
import static util.Metodos.GravaLog;
import static util.Metodos.GravaLogErro;
import util.QuizIFMail;

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
        
        if(wCom.equalsIgnoreCase("EFETUARLOGIN")){
          GravaLog("REQ", idUnico, "Requisição de login - INI");
           
          out.writeObject("ok");
          Usuario user = (Usuario) in.readObject();
          
          GravaLog("REQ", idUnico, "Requisição de login - FIM");
          out.writeObject((new UsuarioDAO()).efetuarLogin(user, idUnico));
        } else if(wCom.equalsIgnoreCase("GETLISTAPROVAS")){
          
          GravaLog("REQ", idUnico, "Lista de provas - INI");
          out.writeObject("ok");
          
          Usuario usu = (Usuario)in.readObject();
          
          ArrayList<Prova> listaProvas = ((new ProvaDAO()).getListaProva(usu.getCodUsuario(), idUnico));
          
          GravaLog("REQ", idUnico, "Lista de provas - FIM");
          out.writeObject(listaProvas);
        } else if(wCom.equalsIgnoreCase("VALIDACODIGOEMAIL")){
          
          GravaLog("REQ", idUnico, "Validação de email por código - INI");
          out.writeObject("ok");
          
          String email = (String)in.readObject();
          
          String sal = CriptoHash.getSalt();
          
          out.writeObject(sal);
          
          String codigo = Metodos.GerarCodigo();
          
          QuizIFMail.EnviaEmail(email, codigo, idUnico);
          
          String criptocodigo = CriptoHash.Cripto(codigo, sal, idUnico);
          
          String cod = (String)in.readObject();
          
          if(cod.equals("")){
            //não continua
            out.writeObject(false);
          } else {
            //retorna um booleano comparando o codigo gerado com o codigo recebido no email
            out.writeObject(criptocodigo.equals(cod));
          }
          
          GravaLog("REQ", idUnico, "Validação de email por código - FIM");
        }
        
        GravaLog("CLI", idUnico, "Esperando comando");
        wCom = (String) in.readObject();
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
