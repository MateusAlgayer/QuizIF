
//Mateus Roberto Algayer - 28/12/2021

package view;

import com.formdev.flatlaf.FlatLightLaf;
import controller.ConexaoController;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import util.Metodos;
import static util.Metodos.GravaLog;
import static util.Metodos.GravaLogErro;
import static util.Metodos.Erro;

public class QuizIFCliente {

  public static ConexaoController ccont;
  
  public static void main(String[] args) {
         
    Socket wSocket;
    ObjectOutputStream wOut;
    ObjectInputStream wIn;
    
    try {  
      javax.swing.UIManager.setLookAndFeel(new FlatLightLaf());
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      GravaLogErro("ERR", 0, "Erro ao definir Look and Feel");
    }
   
    try {
      GravaLog("CON", 0, "Tentando se conectar ao servidor");
      
      wSocket = new Socket(LeConex(), 12345);
      wOut = new ObjectOutputStream(wSocket.getOutputStream());
      wIn = new ObjectInputStream(wSocket.getInputStream());
      
      ccont = new ConexaoController(wSocket, wOut, wIn);
      
    } catch (IOException e) {
      Erro("Erro", "Erro na conexão com o servidor:\n"+e.toString());
      GravaLogErro("CON", 0, e.toString());
      Metodos.CriaConf("ConexCliente", LeConex());
      LeConex();
      Metodos.Aviso("Aviso!!", "IP do servidor gravado!!\nReinicie a aplicação!");
      System.exit(0);
    }
    
    GravaLog("CON", 0, "Conexão efetuado com sucesso!");
    FormLogin fl = new FormLogin();
    fl.setVisible(true);
  }

  private static String LeConex() {
    String ip = Metodos.LeConf("ConexCliente");
      
    if(ip.isEmpty()){
      JTextField tfConex = new JTextField();
      Object[] campos = {"Informe o IP do servidor:",tfConex};
      Object[] opcoes = {"Salvar","Sair"};

      int teste;
      teste = JOptionPane.showOptionDialog(null, 
                                      campos, 
                                      "Servidor não encontrado!", 
                                      JOptionPane.OK_CANCEL_OPTION, 
                                      JOptionPane.WARNING_MESSAGE, 
                                      null, 
                                      opcoes, 
                                      opcoes[0]);
      if(teste == JOptionPane.OK_OPTION){
        Metodos.CriaConf("ConexCliente", tfConex.getText());
        ip = tfConex.getText();
      } else {
        System.exit(0);
      }
    }
    return ip;
  }
}
