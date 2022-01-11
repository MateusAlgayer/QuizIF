
//Mateus Roberto Algayer - 27/12/2021 :: Criação

package controller;

import Model.AreaDAO;
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
          
          String email = (String)in.readObject();
          
          String sal = (new UsuarioDAO()).getUsuSal(email, idUnico);
          
          out.writeObject(sal);
          
          if (!sal.isEmpty()){
            Usuario user = (Usuario) in.readObject();
            out.writeObject((new UsuarioDAO()).efetuarLogin(user, idUnico));
          }
          
          GravaLog("REQ", idUnico, "Requisição de login - FIM");
          
        } else if(wCom.equalsIgnoreCase("GETLISTAPROVAS")){
          
          GravaLog("REQ", idUnico, "Lista de provas - INI");
          out.writeObject("ok");
          
          Usuario usu = (Usuario)in.readObject();
          
          int usuEspec = (int) in.readObject();
          
          ArrayList<Prova> listaProvas = ((new ProvaDAO()).getListaProva(usu.getCodUsuario(), idUnico, usuEspec));
          
          GravaLog("REQ", idUnico, "Lista de provas - FIM");
          out.writeObject(listaProvas);
        } else if(wCom.equalsIgnoreCase("VALIDACODIGOEMAIL")){
          
          GravaLog("REQ", idUnico, "Validação de email por código - INI");
          out.writeObject("ok");
          
          String email = (String)in.readObject();
          
          boolean existe = (new UsuarioDAO()).ExisteEmail(email, idUnico);
          
          if(!existe){
            
            String sal = CriptoHash.getSalt();
          
            out.writeObject(sal);

            String codigo = Metodos.GerarCodigo();

            QuizIFMail.EnviaEmail(email, codigo, idUnico);

            String criptocodigo = CriptoHash.Cripto(codigo, sal, idUnico);

            boolean continua = true;

            while(continua){

              String cod = (String)in.readObject();

              if(cod.equals("Cancelar")){
                continua = false;
                out.writeObject("Cancelei");
              } else if (criptocodigo.equals(cod)){
                continua = false;
                out.writeObject("ok");
              } else {
                out.writeObject("");
              }

            }

            GravaLog("REQ", idUnico, "Validação de email por código - FIM");
          } else {
            //cai aqui caso o email esteja em uso
            GravaLog("REQ", idUnico, "Validação de email por código - FIM");
            out.writeObject("EmailExiste");
          }
        } else if(wCom.equalsIgnoreCase("CADASTROUSU")){
          GravaLog("INS", idUnico, "Cadastro de usuário - INI");
          
          out.writeObject("ok");
          
          Usuario cadUsu = (Usuario)in.readObject();
          
          if ((new UsuarioDAO()).CadastroUsu(cadUsu, idUnico) == -1){
            out.writeObject("ok");
          } else {
            out.writeObject("nok");
          }
          
          GravaLog("INS", idUnico, "Cadastro de usuário - FIM");
        } else if(wCom.equalsIgnoreCase("REDEFINESENHA")){
          GravaLog("UPD", idUnico, "Redefinir senha - email - INI");
          
          out.writeObject("ok");
          
          String email = (String)in.readObject();
          
          String sal = (new UsuarioDAO()).getUsuSal(email, idUnico);
          
          out.writeObject(sal);
          
          if (!sal.isEmpty()){
            String codigo = Metodos.GerarCodigo();

            QuizIFMail.EnviaEmail(email, codigo, idUnico);

            String criptocodigo = CriptoHash.Cripto(codigo, sal, idUnico);

            boolean continua = true;
            boolean cancelado = false;
            
            while(continua){

              String cod = (String)in.readObject();

              if(cod.equals("Cancelar")){
                out.writeObject("Cancelei");
                cancelado = true;
                continua = false;
              } else if (criptocodigo.equals(cod)){
                continua = false;
                out.writeObject("ok");
              } else {
                out.writeObject("");
              }

            }
            
            if(!cancelado){
              GravaLog("UPD", idUnico, "Redefinir senha - email - FIM");
          
              GravaLog("UPD", idUnico, "Redefinir senha - senha - INI");

              Usuario usu = (Usuario)in.readObject();

              if (usu != null){
                if((new UsuarioDAO()).RedefSenha(usu, idUnico) == -1){
                  out.writeObject("ok");
                } else {
                  out.writeObject("nok");
                }
                
              }

              GravaLog("UPD", idUnico, "Redefinir senha - senha - FIM");
            }
          }
        } else if(wCom.equalsIgnoreCase("GETLISTAAREA")){
          GravaLog("REQ", idUnico, "Lista areas - INI");
          
          out.writeObject("ok");
          
          out.writeObject((new AreaDAO()).getListaArea(idUnico));
          
          GravaLog("REQ", idUnico, "Lista areas - FIM");
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
