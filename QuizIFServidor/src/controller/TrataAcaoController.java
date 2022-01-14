
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
import ModelDominio.Pergunta;

public class TrataAcaoController extends Thread{
  
  private final ObjectOutputStream out;
  private final ObjectInputStream in;
  private final Socket cliente;
  private final int idUnico;
  private int usuLogado;

  public TrataAcaoController(ObjectOutputStream pOut, ObjectInputStream pIn, Socket pCliente, int pIdUnico) {
    this.out = pOut;
    this.in = pIn;
    this.cliente = pCliente;
    this.idUnico = pIdUnico;
    this.usuLogado = 0;
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
            
            Usuario usu = (new UsuarioDAO()).efetuarLogin(user, idUnico);
            out.writeObject(usu);
            
            if (usu != null){
              this.usuLogado = usu.getCodUsuario();
              GravaLog("USU", idUnico, "Usuário conectado: "+usuLogado);
            }
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
        } else if(wCom.equalsIgnoreCase("VALIDACODIGOEMAILDELUSU")){
          
          GravaLog("REQ", idUnico, "Validação de email por código - INI");
          out.writeObject("ok");
          
          String email = (String)in.readObject();
          
          boolean existe = (new UsuarioDAO()).ExisteEmail(email, idUnico);
          
          if(existe){
            
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
          }
        } else if(wCom.equalsIgnoreCase("Deletar Usuário")){
            out.writeObject("ok");
            Usuario usu = (Usuario) in.readObject();
            //chamar o método de deletar do UsuarioDao
            UsuarioDAO usudao = new UsuarioDAO();
            int result = usudao.deletausu(usu, idUnico);
            if (result == -1){
                out.writeObject("ok");
            }else {
                out.writeObject("nok");
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
        } else if(wCom.equalsIgnoreCase("GETPERGUNTASPROVA")){
          GravaLog("REQ", idUnico, "Lista perguntas prova - INI");
          
          out.writeObject("ok");
          
          int numProva = (int)in.readObject();
          
          ArrayList<Pergunta> listaDis = new ArrayList<>();
          ArrayList<Pergunta> listaSel = new ArrayList<>();
          
          (new ProvaDAO()).getPerguntasProva(numProva, listaDis, listaSel, idUnico);
          
          out.writeObject(listaDis);
          
          if (numProva != 0){
          out.writeObject(listaSel);
          }
          
          GravaLog("REQ", idUnico, "Lista perguntas prova - FIM");
        } else if(wCom.equalsIgnoreCase("INSERIRPROVA")){
          GravaLog("CAD", idUnico, "Cadastro de prova - INI");
          
          out.writeObject("ok");
          
          Prova p = (Prova)in.readObject();
          
          ArrayList<Pergunta> cadPerSel = (ArrayList<Pergunta>) in.readObject();
          
          int status = (new ProvaDAO()).InserirProva(p, cadPerSel, idUnico, usuLogado);
          
          if(status == -1){
            out.writeObject("ok");
          } else {
            out.writeObject("nok");
            GravaLogErro("ERR", idUnico, "Erro ao inserir a prova\nStatus erro:"+status);
          }
          
          GravaLog("CAD", idUnico, "Cadastro de prova - FIM");
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
