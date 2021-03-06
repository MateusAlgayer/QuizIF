
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
import util.QuizIFMail;
import ModelDominio.Pergunta;
import static util.Metodos.gravaLog;
import static util.Metodos.gravaLogErro;

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
    
    gravaLog("CLI", idUnico, "Esperando comando");
    
    try {
      wCom = (String) in.readObject();
      
      while(!wCom.equalsIgnoreCase("FIM")){
        gravaLog("CLI", idUnico, "Enviou o comando: '"+wCom+"'");
        
        if(wCom.equalsIgnoreCase("EFETUARLOGIN")){
          gravaLog("REQ", idUnico, "Requisição de login - INI");
          
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
              gravaLog("USU", idUnico, "Usuário conectado: "+usuLogado + "\nEmail: " +emailLogado);
            }
          }
          
          gravaLog("REQ", idUnico, "Requisição de login - FIM");
          
        } else if(wCom.equalsIgnoreCase("GETLISTAPROVAS")){
          
          gravaLog("REQ", idUnico, "Lista de provas - INI");
          out.writeObject("ok");
          
          Usuario usu = (Usuario)in.readObject();
          
          out.writeObject("ok");
          
          int usuEspec = (int) in.readObject();
          
          ArrayList<Prova> listaProvas = ((new ProvaDAO()).getListaProva(usu.getCodUsuario(), idUnico, usuEspec));
          
          gravaLog("REQ", idUnico, "Lista de provas - FIM");
          out.writeObject(listaProvas);
        } else if(wCom.equalsIgnoreCase("VALIDACODIGOEMAIL")){
          
          gravaLog("REQ", idUnico, "Validação de email por código - INI");
          out.writeObject("ok");
          
          String email = (String)in.readObject();
          
          boolean existe = (new UsuarioDAO()).ExisteEmail(email, idUnico);
          
          if(!existe){
            
            String sal = CriptoHash.getSalt(idUnico);
          
            out.writeObject(sal);
            
            if(!sal.isEmpty()){
              String codigo = Metodos.gerarCodigo();
              
                System.out.println(codigo);

              QuizIFMail.enviaEmail(email, codigo, idUnico);

              String criptocodigo = CriptoHash.Cripto(codigo, sal, idUnico);

              //boolean continua = true;

              //while(continua){

                String cod = (String)in.readObject();

                if(cod.equals("Cancelar")){
                  //continua = false;
                  out.writeObject("Cancelei");
                } else if (criptocodigo.equals(cod)){
                  //continua = false;
                  out.writeObject("S^ok");
                } else {
                  out.writeObject("nok");
                }

              //}
            }

            gravaLog("REQ", idUnico, "Validação de email por código - FIM");
          } else {
            //cai aqui caso o email esteja em uso
            gravaLog("REQ", idUnico, "Validação de email por código - FIM");
            out.writeObject("EmailExiste");
          }
        } else if(wCom.equalsIgnoreCase("VALIDACODIGOEMAILDELUSU")){
          
          gravaLog("REQ", idUnico, "Validação de email por código - INI");
          out.writeObject("ok");
          
          String email = (String)in.readObject();
            
          String sal = CriptoHash.getSalt(idUnico);

          out.writeObject(sal);

          String codigo = Metodos.gerarCodigo();
          
          System.out.println(codigo);

          QuizIFMail.enviaEmail(email, codigo, idUnico);

          String criptocodigo = CriptoHash.Cripto(codigo, sal, idUnico);

          //boolean continua = true;

          //while(continua){

          String cod = (String)in.readObject();

          if(cod.equals("Cancelar")){
            //continua = false;
            out.writeObject("Cancelei");
          } else if (criptocodigo.equals(cod)){
            //continua = false;
            out.writeObject("S^ok");
          } else {
            out.writeObject("A^Código Inválido!");
          }

          //}

          gravaLog("REQ", idUnico, "Validação de email por código - FIM");
        } else if(wCom.equalsIgnoreCase("Deletar Usuário")){
            gravaLog("DEL", idUnico, "Deletar usuário - INI");
            
            out.writeObject("ok");
            Usuario usu = (Usuario) in.readObject();
            //chamar o método de deletar do UsuarioDao
            UsuarioDAO usudao = new UsuarioDAO();
            
            String status = usudao.deletausu(usu, idUnico);
            
            out.writeObject(status);
            
            gravaLog("DEL", idUnico, "Deletar usuário - FIM");
//            if (result == -1){
//                out.writeObject("ok");
//            }else {
//                out.writeObject("nok");
//            }
        } else if(wCom.equalsIgnoreCase("CADASTROUSU")){
          gravaLog("INS", idUnico, "Cadastro de usuário - INI");
          
          out.writeObject("ok");
          
          Usuario cadUsu = (Usuario)in.readObject();
          
          String status = (new UsuarioDAO()).CadastroUsu(cadUsu, idUnico);
          
          out.writeObject(status);
          
          gravaLog("INS", idUnico, "Cadastro de usuário - FIM");
        } else if(wCom.equalsIgnoreCase("REDEFINESENHA")){
          gravaLog("UPD", idUnico, "Redefinir senha - email - INI");
          
          out.writeObject("ok");
          
          String email = (String)in.readObject();
          
          String sal = (new UsuarioDAO()).getUsuSal(email, idUnico);
          
          out.writeObject(sal);
          
          if (!sal.isEmpty()){
            String codigo = Metodos.gerarCodigo();
            
            System.out.println(codigo);

            QuizIFMail.enviaEmail(email, codigo, idUnico);

            String criptocodigo = CriptoHash.Cripto(codigo, sal, idUnico);

            //boolean continua = true;
            boolean cancelado = false;
            
            //while(continua){

              String cod = (String)in.readObject();

              if(cod.equals("Cancelar")){
                out.writeObject("Cancelei");
                cancelado = true;
                //continua = false;
              } else if (criptocodigo.equals(cod)){
                //continua = false;
                out.writeObject("S^ok");
              } else {
                out.writeObject("");
                cancelado = true;
              }

            //}
            
//            if(!cancelado){
//              gravaLog("UPD", idUnico, "Redefinir senha - email - FIM");
//          
//              gravaLog("UPD", idUnico, "Redefinir senha - senha - INI");
//
//              Usuario usu = (Usuario)in.readObject();
//
//              if (usu != null){
//                String status = (new UsuarioDAO()).RedefSenha(usu, idUnico);
//                                
//                out.writeObject(status);
////                if((new UsuarioDAO()).RedefSenha(usu, idUnico) == -1){
////                  out.writeObject("ok");
////                } else {
////                  out.writeObject("nok");
////                }
//                
//              }
//
//              gravaLog("UPD", idUnico, "Redefinir senha - senha - FIM");
//            }
          }
        } else if(wCom.equalsIgnoreCase("Alterar Senha Usuário")){
          gravaLog("UPD", idUnico, "Alterar senha INI");
          
          out.writeObject("ok");

          Usuario usu = (Usuario)in.readObject();

          if (usu != null){
            String status = (new UsuarioDAO()).AlteraSenha(usu, idUnico);
            
            out.writeObject(status);
            
//            if((new UsuarioDAO()).AlteraSenha(usu, idUnico) == -1){
//                out.writeObject("ok");
//            } else {
//                out.writeObject("nok");
//            }
          }
          gravaLog("UPD", idUnico, "Alterar senha - FIM");
        } else if(wCom.equalsIgnoreCase("GETLISTAAREA")){
          gravaLog("REQ", idUnico, "Lista areas - INI");
          
          //out.writeObject("ok");
          
          out.writeObject((new AreaDAO()).getListaArea(idUnico));
          
          gravaLog("REQ", idUnico, "Lista areas - FIM");
        } else if(wCom.equalsIgnoreCase("GETPERGUNTASPROVA")){
          gravaLog("REQ", idUnico, "Lista perguntas prova - INI");
          
          out.writeObject("ok");
          
          int numProva = (int)in.readObject();
          
          ArrayList<Pergunta> listaDis = new ArrayList<>();
          ArrayList<Pergunta> listaSel = new ArrayList<>();
          
          (new ProvaDAO()).getPerguntasProva(numProva, listaDis, listaSel, idUnico);
          
          out.writeObject(listaDis);
          
          if (numProva != 0){
            String msg = (String)in.readObject();
            
            out.writeObject(listaSel);
          }
          
          gravaLog("REQ", idUnico, "Lista perguntas prova - FIM");
        } else if(wCom.equalsIgnoreCase("INSERIRPROVA")){
          gravaLog("CAD", idUnico, "Cadastro de prova - INI");
          
          out.writeObject("ok");
          
          Prova p = (Prova)in.readObject();
          
          ArrayList<Pergunta> cadPerSel = (ArrayList<Pergunta>) in.readObject();
          
          String status = (new ProvaDAO()).InserirProva(p, cadPerSel, idUnico, usuLogado);
          
          out.writeObject(status);
//          if(status == -1){
//            out.writeObject("ok");
//          } else {
//            out.writeObject("nok");
//            GravaLogErro("ERR", idUnico, "Erro ao inserir a prova\nStatus erro:"+status);
//          }
          gravaLog("CAD", idUnico, "Cadastro de prova - FIM");
        } else if(wCom.equalsIgnoreCase("DELETAPROVA")){
          gravaLog("DEL", idUnico, "Deletar prova - INI");
          
          out.writeObject("ok");
          
          int prova = (int)in.readObject();
          
          String status = (new ProvaDAO()).DeletaProva(prova, idUnico);
          
//          switch (status) {
//            case -1 -> out.writeObject("ok");
//            case 1451 -> //código de erro pra quando o que está sendo deletado já foi referenciado como estrangeira em uma tabela filha
//              out.writeObject("jautiliz");
//            default -> out.writeObject("nok");
//          }
          
          out.writeObject(status);

          gravaLog("DEL", idUnico, "Deletar prova - FIM");
        } else if(wCom.equalsIgnoreCase("ALTERARPROVA")){
          gravaLog("UPD", idUnico, "Alteração de prova - INI");
          
          out.writeObject("ok");
          
          Prova p = (Prova)in.readObject();
          
          ArrayList<Pergunta> cadPerSel = (ArrayList<Pergunta>) in.readObject();
          
          String status = (new ProvaDAO()).AlterarProva(p, cadPerSel, idUnico, usuLogado);
          
          out.writeObject(status);
//          if(status == -1){
//            out.writeObject("ok");
//          } else {
//            out.writeObject("nok");
//            GravaLogErro("ERR", idUnico, "Erro ao editar prova\nStatus erro:"+status);
//          }
          gravaLog("UPD", idUnico, "Alteração de prova - FIM");
        }else if(wCom.equalsIgnoreCase("GETRANKING")){
            gravaLog("REQ", idUnico, "Get Ranking - INI");
            
            out.writeObject("ok");
            
            int filtro = (Integer) in.readObject();
            
            JogoDAO jgdao = new JogoDAO();
            ArrayList<Jogo> listaRanking = jgdao.getListaRanking(filtro, idUnico);
            out.writeObject(listaRanking);
            
            gravaLog("REQ", idUnico, "Get Ranking - FIM");
        } else if(wCom.equalsIgnoreCase("GETPERGUNTASJOGO")){
          gravaLog("REQ", idUnico, "perguntas jogo - INI");
          
          out.writeObject("ok");
          
          int numProva = (int)in.readObject();
          
          ArrayList<Pergunta> listaSel = (new JogoDAO()).getPerguntasJogo(numProva, idUnico);
          
          out.writeObject(listaSel);
          
          gravaLog("REQ", idUnico, "perguntas jogo - FIM");
        } else if(wCom.equalsIgnoreCase("AlteraTipoUsu")){
          gravaLog("DEL", idUnico, "Alterar Tipo Usuário - INI");
          
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
          
          gravaLog("DEL", idUnico, "Deletar prova - FIM");
        } else if(wCom.equalsIgnoreCase("GETUSU")){
            gravaLog("REQ", idUnico, "Get Usuarios - INI");
            
            out.writeObject("ok");
            
            String msg = (String) in.readObject();
            
            UsuarioDAO usudao = new UsuarioDAO();
            ArrayList<Usuario> listaUsuarios = usudao.getListaUsuarios(idUnico);
            out.writeObject(listaUsuarios);
            
            gravaLog("REQ", idUnico, "Get Usuarios - FIM");
        } else if(wCom.equalsIgnoreCase("GETPERGUNTAS")){
          gravaLog("REQ", idUnico, "perguntas - INI");
          
          out.writeObject("ok");
          
          ArrayList<Pergunta> listaSel = (new PerguntaDAO()).getPerguntas(usuLogado, idUnico);
          
          out.writeObject(listaSel);
          
          gravaLog("REQ", idUnico, "perguntas - FIM");
        } else if(wCom.equalsIgnoreCase("GETLISTAPROVASHIST")){
          
          gravaLog("REQ", idUnico, "Lista de provas histórico - INI");
          out.writeObject("ok");
          
          ArrayList<Prova> listaProvas = ((new ProvaDAO()).getListaProvaHist(this.usuLogado, idUnico));
          
          gravaLog("REQ", idUnico, "Lista de provas histórico - FIM");
          out.writeObject(listaProvas);
          
        } else if(wCom.equalsIgnoreCase("DELETAPERGUNTA")){
          gravaLog("DEL", idUnico, "Deletar pergunta - INI");
          
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
          
          gravaLog("DEL", idUnico, "Deletar pergunta - FIM");
        } else if(wCom.equalsIgnoreCase("INSERIRPERGUNTA")){
          gravaLog("CAD", idUnico, "Cadastro de pergunta - INI");
          
          out.writeObject("ok");
          
          Pergunta p = (Pergunta)in.readObject();
          
          String status = (new PerguntaDAO()).InserirPergunta(p, idUnico, usuLogado);
          
          out.writeObject(status);
          
//          if(status == -1){
//            out.writeObject("ok");
//          } else {
//            out.writeObject("nok");
//          }
          gravaLog("CAD", idUnico, "Cadastro de pergunta - FIM");
        } else if(wCom.equalsIgnoreCase("ALTERARPERGUNTA")){
          gravaLog("UPD", idUnico, "Alteração de pergunta - INI");
          
          out.writeObject("ok");
          
          Pergunta p = (Pergunta)in.readObject();
          
          String status = (new PerguntaDAO()).AlterarPergunta(p, idUnico, usuLogado);
          
          out.writeObject(status);
//          if(status == -1){
//            out.writeObject("ok");
//          } else {
//            out.writeObject("nok");
//          }
          
          gravaLog("UPD", idUnico, "Alteração de pergunta - FIM");
        } else if(wCom.equalsIgnoreCase("GRAVARJOGO")){
          gravaLog("INS", idUnico, "Grava jogo - INI");
          
          out.writeObject("ok");
          
          Jogo j = (Jogo)in.readObject();
          
          String status = (new JogoDAO()).GravaResultJogo(j, idUnico);
          
          out.writeObject(status);
//          if(status == -1){
//            out.writeObject("ok");
//          } else {
//            out.writeObject("nok");
//            GravaLogErro("ERR", idUnico, "Erro ao gravar jogo\nStatus erro:"+status);
//          }
          gravaLog("INS", idUnico, "Grava jogo - FIM");
        }
        
        gravaLog("CLI", idUnico, "Esperando comando");
        wCom = (String) in.readObject();
      }
      
    } catch (IOException | ClassNotFoundException e) {
      gravaLogErro("ERR", idUnico, e.toString());
    }
    
    gravaLog("CLI", idUnico, "Fechando Conexão");
    
    try {
      in.close();
      out.close();
    } catch (IOException e) {
      gravaLogErro("ERR", idUnico, e.toString());
    }
  }
  
}