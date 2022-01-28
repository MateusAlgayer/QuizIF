
//Mateus Roberto Algayer - 27/12/2021 :: Criação
//João Felipe Staub - 18/01/2022 :: GETRANKING
//João Felipe Staub - 18/01/2022 :: GETRANKING TÉRMINO
//João Felipe Staub - 19/01/2022 :: AlteraTipoUsu

package controller;

import Model.AreaDAO;
import Model.JogoDAO;
import Model.PerguntaDAO;
import Model.ProvaDAO;
import Model.UsuarioDAO;
import ModelDominio.Jogo;
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
  private String emailLogado;
  private int usuLogado;

  public TrataAcaoController(ObjectOutputStream pOut, ObjectInputStream pIn, Socket pCliente, int pIdUnico) {
    this.out = pOut;
    this.in = pIn;
    this.cliente = pCliente;
    this.idUnico = pIdUnico;
    this.usuLogado = 0;
    this.emailLogado = "";
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
              this.emailLogado = usu.getEmail();
              GravaLog("USU", idUnico, "Usuário conectado: "+usuLogado + "\nEmail: " +emailLogado);
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
            
            String sal = CriptoHash.getSalt(idUnico);
          
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
            
            String sal = CriptoHash.getSalt(idUnico);
          
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
          
          String status = (new UsuarioDAO()).CadastroUsu(cadUsu, idUnico);
          
          out.writeObject("ok");
          
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
        } else if(wCom.equalsIgnoreCase("Alterar Senha Usuário")){
          GravaLog("UPD", idUnico, "Alterar senha INI");
          
          out.writeObject("ok");
          
          GravaLog("UPD", idUnico, "Alterar senha - FIM");
          
          GravaLog("UPD", idUnico, "Alterar senha - INI");

          Usuario usu = (Usuario)in.readObject();

          if (usu != null){
            if((new UsuarioDAO()).AlteraSenha(usu, idUnico) == -1){
                out.writeObject("ok");
            } else {
                out.writeObject("nok");
            }
          }
          GravaLog("UPD", idUnico, "Alterar senha - FIM");
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
        } else if(wCom.equalsIgnoreCase("DELETAPROVA")){
          GravaLog("DEL", idUnico, "Deletar prova - INI");
          
          out.writeObject("ok");
          
          int prova = (int)in.readObject();
          
          int status = (new ProvaDAO()).DeletaProva(prova, idUnico);
          
          switch (status) {
            case -1 -> out.writeObject("ok");
            case 1451 -> //código de erro pra quando o que está sendo deletado já foi referenciado como estrangeira em uma tabela filha
              out.writeObject("jautiliz");
            default -> out.writeObject("nok");
          }
          
          GravaLog("DEL", idUnico, "Deletar prova - FIM");
        } else if(wCom.equalsIgnoreCase("ALTERARPROVA")){
          GravaLog("UPD", idUnico, "Alteração de prova - INI");
          
          out.writeObject("ok");
          
          Prova p = (Prova)in.readObject();
          
          ArrayList<Pergunta> cadPerSel = (ArrayList<Pergunta>) in.readObject();
          
          int status = (new ProvaDAO()).AlterarProva(p, cadPerSel, idUnico, usuLogado);
          
          if(status == -1){
            out.writeObject("ok");
          } else {
            out.writeObject("nok");
            GravaLogErro("ERR", idUnico, "Erro ao editar prova\nStatus erro:"+status);
          }
          
          GravaLog("UPD", idUnico, "Alteração de prova - FIM");
        }else if(wCom.equalsIgnoreCase("GETRANKING")){
            GravaLog("REQ", idUnico, "Get Ranking - INI");
            
            out.writeObject("ok");
            
            int filtro = (Integer) in.readObject();
            
            JogoDAO jgdao = new JogoDAO();
            ArrayList<Jogo> listaRanking = jgdao.getListaRanking(filtro, idUnico);
            out.writeObject(listaRanking);
            
            GravaLog("REQ", idUnico, "Get Ranking - FIM");
        } else if(wCom.equalsIgnoreCase("GETPERGUNTASJOGO")){
          GravaLog("REQ", idUnico, "perguntas jogo - INI");
          
          out.writeObject("ok");
          
          int numProva = (int)in.readObject();
          
          ArrayList<Pergunta> listaSel = (new JogoDAO()).getPerguntasJogo(numProva, idUnico);
          
          out.writeObject(listaSel);
          
          GravaLog("REQ", idUnico, "perguntas jogo - FIM");
        } else if(wCom.equalsIgnoreCase("AlteraTipoUsu")){
          GravaLog("DEL", idUnico, "Alterar Tipo Usuário - INI");
          
          out.writeObject("ok");
          
          Usuario usu = (Usuario) in.readObject();
          String tipo = (String) in.readObject();
          
            UsuarioDAO usudao = new UsuarioDAO();
            String status = usudao.AlteraTipoUsu(usu, tipo, idUnico);
            
            out.writeObject(status);
//            if(statusCode == -1){
//                out.writeObject("ok");
//            }else{
//                out.writeObject("nok");
//            }
          
          GravaLog("DEL", idUnico, "Deletar prova - FIM");
        } else if(wCom.equalsIgnoreCase("GETUSU")){
            GravaLog("REQ", idUnico, "Get Usuarios - INI");
            
            out.writeObject("ok");
            
            String msg = (String) in.readObject();
            
            UsuarioDAO usudao = new UsuarioDAO();
            ArrayList<Usuario> listaUsuarios = usudao.getListaUsuarios(idUnico);
            out.writeObject(listaUsuarios);
            
            GravaLog("REQ", idUnico, "Get Usuarios - FIM");
        } else if(wCom.equalsIgnoreCase("GETPERGUNTAS")){
          GravaLog("REQ", idUnico, "perguntas - INI");
          
          out.writeObject("ok");
          
          ArrayList<Pergunta> listaSel = (new PerguntaDAO()).getPerguntas(usuLogado, idUnico);
          
          out.writeObject(listaSel);
          
          GravaLog("REQ", idUnico, "perguntas - FIM");
        } else if(wCom.equalsIgnoreCase("GETLISTAPROVASHIST")){
          
          GravaLog("REQ", idUnico, "Lista de provas histórico - INI");
          out.writeObject("ok");
          
          ArrayList<Prova> listaProvas = ((new ProvaDAO()).getListaProvaHist(this.usuLogado, idUnico));
          
          GravaLog("REQ", idUnico, "Lista de provas histórico - FIM");
          out.writeObject(listaProvas);
          
        } else if(wCom.equalsIgnoreCase("DELETAPERGUNTA")){
          GravaLog("DEL", idUnico, "Deletar pergunta - INI");
          
          out.writeObject("ok");
          
          int codPergunta = (int)in.readObject();
          
          String status = (new PerguntaDAO()).DeletaPergunta(codPergunta, idUnico);
          
          out.writeObject(status);
//          switch (status) {
//            case -1 -> out.writeObject("ok");
//            case 1451 -> //código de erro pra quando o que está sendo deletado já foi referenciado como estrangeira em uma tabela filha
//              out.writeObject("jautiliz");
//            default -> out.writeObject("nok");
//          }
          
          GravaLog("DEL", idUnico, "Deletar pergunta - FIM");
        } else if(wCom.equalsIgnoreCase("INSERIRPERGUNTA")){
          GravaLog("CAD", idUnico, "Cadastro de pergunta - INI");
          
          out.writeObject("ok");
          
          Pergunta p = (Pergunta)in.readObject();
          
          int status = (new PerguntaDAO()).InserirPergunta(p, idUnico, usuLogado);
          
          if(status == -1){
            out.writeObject("ok");
          } else {
            out.writeObject("nok");
            GravaLogErro("ERR", idUnico, "Erro ao inserir a pergunta\nStatus erro:"+status);
          }
          
          GravaLog("CAD", idUnico, "Cadastro de pergunta - FIM");
        } else if(wCom.equalsIgnoreCase("ALTERARPERGUNTA")){
          GravaLog("UPD", idUnico, "Alteração de pergunta - INI");
          
          out.writeObject("ok");
          
          Pergunta p = (Pergunta)in.readObject();
          
          int status = (new PerguntaDAO()).AlterarPergunta(p, idUnico, usuLogado);
          
          if(status == -1){
            out.writeObject("ok");
          } else {
            out.writeObject("nok");
            GravaLogErro("ERR", idUnico, "Erro ao alterar a pergunta\nStatus erro:"+status);
          }
          
          GravaLog("UPD", idUnico, "Alteração de pergunta - FIM");
        } else if(wCom.equalsIgnoreCase("GRAVARJOGO")){
          GravaLog("INS", idUnico, "Grava jogo - INI");
          
          out.writeObject("ok");
          
          Jogo j = (Jogo)in.readObject();
          
          int status = (new JogoDAO()).GravaResultJogo(j, idUnico);
          
          if(status == -1){
            out.writeObject("ok");
          } else {
            out.writeObject("nok");
            GravaLogErro("ERR", idUnico, "Erro ao gravar jogo\nStatus erro:"+status);
          }
          
          GravaLog("INS", idUnico, "Grava jogo - FIM");
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