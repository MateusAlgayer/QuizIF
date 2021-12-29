
//Mateus Roberto Algayer - 29/12/2021 :: se tiver arquivo debug vai printar no console os logs
//Mateus Roberto Algayer - 23/11/2021 :: Consistencia e Pedaco 

package util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Window;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.text.JTextComponent;

public class Metodos{
  
  //Mateus Roberto Algayer - 23/11/2021
  public static Boolean Consistencia(Boolean pGeraAviso,JComponent ...wComps){
    
    for(JComponent wComp : wComps){
      
      if((wComp instanceof JTextComponent wCompTexto)) {
        
        if(wCompTexto.getText().trim().equals("")){
          
          if(pGeraAviso) Aviso(wComp.getTopLevelAncestor().getName(), "campo '"+wComp.getName()+"' inválido");
          
          wComp.setBackground(Color.YELLOW);
          wComp.requestFocus();
          return false; 
        }

        wComp.setBackground(Color.WHITE);
      } 
      
    }
    
    return true; 
  }
  
  //Mateus Roberto Algayer - 23/11/2021
  public static String Pedaco(String pLinha, String pDelimita, int pNumString){
    
    Boolean wContinua = true;
    String wParte = "";
    int wCiclo = 0;
    int wIdxIni = 0, wIdx = 0;
    
    if(!pLinha.contains(pDelimita)) return "";
    if(pNumString == 0) return "";
    
    wIdx = pLinha.indexOf(pDelimita, wIdx);
    
    //mateus;roberto;algayer
    
    while(wContinua){
      
      if(wIdx == -1){
        wContinua = false;
        wIdx = pLinha.length();
      }
      
      wCiclo++;
      
      if(wCiclo == pNumString){
        
        wParte = pLinha.substring(wIdxIni, wIdx);
        break;
      }
      
      wIdxIni = wIdx+pDelimita.length();
      wIdx = pLinha.indexOf(pDelimita, wIdx + pDelimita.length());
      
    }
    return wParte;
  }
  
  //Mateus Roberto Algayer - 23/11/2021
  public static void GeraConsistenciaCampos(JRootPane pComps){
    
    ArrayList<Component> wListaComps = new ArrayList<>();
    
    wListaComps.addAll(PegaTodosComponentes(pComps));
    
    //Pega todos os componentes na lista e abre uma stream
    //então ele filtra pelos componentes que são instancias de JTextComponent 
    //ordenando cada e criando um evento KeyListener com o método "Consistencia"
    wListaComps.stream().filter(wComp -> (wComp instanceof JTextComponent)).forEachOrdered(wComp -> {
      
      ((JTextComponent)wComp).addKeyListener(new java.awt.event.KeyAdapter() {
        
        @Override
        public void keyReleased(java.awt.event.KeyEvent evt) {
          
          Consistencia(Boolean.FALSE, ((JTextComponent)wComp));
        }
        
      }); 
    });
  }
  
  //Mateus Roberto Algayer - 23/11/2021
  public static ArrayList<Component> PegaTodosComponentes(final Container pComp){
    ArrayList<Component> wListaComps = new ArrayList<>();
    
    Component[] wComps = pComp.getComponents();
    
    for(Component wComp: wComps){
      wListaComps.add(wComp);
      
      //se wComp é um container, chama PegaTodosComponentes recursivamente
      if(wComp instanceof Container container) wListaComps.addAll(PegaTodosComponentes(container));
    }
    
    return wListaComps;
  }
  
  //Mateus Roberto Algayer - 26/11/2021
  public static void Erro(String pTitulo, String pMsg){
    JOptionPane.showMessageDialog(null, pMsg, pTitulo, JOptionPane.ERROR_MESSAGE);
  }
  
  //Mateus Roberto Algayer - 26/11/2021
  public static void Aviso(String pTitulo, String pMsg){
    JOptionPane.showMessageDialog(null, pMsg, pTitulo, JOptionPane.WARNING_MESSAGE);
  }
  
  //Mateus Roberto Algayer - 26/11/2021
  public static void CriaTela(Class pTela){
    try {
      Window wTela = (Window)pTela.getDeclaredConstructor().newInstance();

      wTela.setVisible(true);

    } catch (Exception e) {
      Erro("Erro!", e.getMessage());
     
    }
  }
  
  //Mateus Roberto Algayer - 29/11/2021
  public static void CriaTelaModal(Class pTela){      
    try {
      Window wTela = (Window)pTela.getDeclaredConstructor().newInstance();

      ((JDialog)wTela).setModal(true);

      wTela.setVisible(true);
    } catch (Exception e) {
      Erro("Erro!", e.getMessage());
    }
  }
  
  //Mateus Roberto Algayer - 03/12/2021
  //overload da CriaTelaModal pra pegar parâmetros
  public static void CriaTelaModal(Class pTela,Object ...pObj){  
    try{
      Window wTela = (Window)pTela.getDeclaredConstructor().newInstance();
      
      ((JDialog)wTela).setModal(true);

      wTela.setVisible(true);
    } catch (Exception e) {
      Erro("Erro!", e.getMessage());
    }
  }
  
  //Mateus Roberto Algayer - 11/12/2021
  public static void Sucesso(String pTitulo, String pMsg){
    JOptionPane.showMessageDialog(null, pMsg, pTitulo, JOptionPane.INFORMATION_MESSAGE);
  }
  
  //Mateus Roberto Algayer - 16/12/2021
  public static void GravaLog(String acao, int id, String operacao){
      
    try {

        String path = "Logs/";

        File dir = new File(path);
        
        if(!dir.exists()){
            dir.mkdirs();
        }
        
        File arquivo = new File(path+"QuizIFLog - "+id+" - "+LocalDate.now()+".log");
        
        if(!arquivo.exists()){
            arquivo.createNewFile();
        }

        try (BufferedWriter grava = new BufferedWriter(new FileWriter(arquivo, true))) {
            grava.append("\n"+acao+" - "+id+" - "+Instant.now()+" - "+operacao);
            
            if((new File("QuizIFDebug.debug")).exists()){
              System.out.println(acao+" - "+id+" - "+Instant.now()+" - "+operacao);
            }
        }
    } catch (IOException e) {
        GravaLogErro("ERR", id, e.toString());
    }
  }
  
  //Mateus Roberto Algayer - 16/12/2021
  public static void GravaLogErro(String acao, int id, String operacao){
      
    try {

        String path = "Logs/Erros/";

        File dir = new File(path);
        
        if(!dir.exists()){
            dir.mkdirs();
        }
        
        File arquivo = new File(path+"QuizIFLogErro - "+id+" - "+LocalDate.now()+".log");
        
        if(!arquivo.exists()){
            arquivo.createNewFile();
        }

        try (BufferedWriter grava = new BufferedWriter(new FileWriter(arquivo, true))) {
            grava.append("\n"+acao+" - "+id+" - "+Instant.now()+" - "+operacao);
            if((new File("QuizIFDebug.debug")).exists()){ //Mateus Roberto Algayer - 29/12/2021
              System.out.println(acao+" - "+id+" - "+Instant.now()+" - "+operacao);
            }
        }
    } catch (IOException e) {
    }
  }
}
