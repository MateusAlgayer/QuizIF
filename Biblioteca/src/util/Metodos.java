
//Mateus Roberto Algayer - 04/01/2022 :: CriaConf e LeConf
//Mateus Roberto Algayer - 29/12/2021 :: se tiver arquivo debug vai printar no console os logs
//Mateus Roberto Algayer - 23/11/2021 :: Consistencia e Pedaco 

package util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.text.JTextComponent;

public class Metodos{
  
  //Mateus Roberto Algayer - 23/11/2021
  /**
   * O método consistencia serve demonstrar visualmente um campo inválido que não foi preenchido 
   * @param pGeraAviso - variável caso tenha que ter aviso ao usuário
   * @param wComps - variavel que representa um número n de componentes a serem verificados na consistencia
   * @return true se o campo é válido, false se o campo é inválido
   */
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
  /**
   * Esse metodo serve para pegar uma parte da string que está entre delimitadores
   * @param pLinha - a String total a ser separada
   * @param pDelimita - o delimitador que deve ser utilizado 
   * @param pNumString - o numero da string que se quer Ex: Ciclano-Fulano, 1 seria Ciclano, 2 - seria Fulano
   * @return A string dentro dos delimitadores ou vazio caso não encontre
   */
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
  /**
   * Este metodo pega e adiciona o método de consistencia para todos os componentes de texto de uma tela
   * @param pComps - representa o rootpane de uma tela, a raiz que possui todos os outros componentes
   */
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
  /**
   * Pega todos os componentes de uma tela, usando recursão 
   * @param pComp o componente pai, que será utilizado para pComp.getComponents()
   * @return um ArrayList com todos os componentes de uma tela
   */
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
  /**
   * Metodo para mostrar uma mensagem de erro
   * @param pTitulo titulo da mensagem
   * @param pMsg mensagem
   */
  public static void Erro(String pTitulo, String pMsg){
    JOptionPane.showMessageDialog(null, pMsg, pTitulo, JOptionPane.ERROR_MESSAGE);
  }
  
  //Mateus Roberto Algayer - 26/11/2021
   /**
   * Metodo para mostrar uma mensagem de aviso
   * @param pTitulo titulo da mensagem
   * @param pMsg mensagem
   */
  public static void Aviso(String pTitulo, String pMsg){
    JOptionPane.showMessageDialog(null, pMsg, pTitulo, JOptionPane.WARNING_MESSAGE);
  }
  
  //Mateus Roberto Algayer - 11/12/2021
   /**
   * Metodo para mostrar uma mensagem de sucesso
   * @param pTitulo titulo da mensagem
   * @param pMsg mensagem
   */
  public static void Sucesso(String pTitulo, String pMsg){
    JOptionPane.showMessageDialog(null, pMsg, pTitulo, JOptionPane.INFORMATION_MESSAGE);
  }
  
  //Mateus Roberto Algayer - 16/12/2021
  /**
   * Esse metodo cria um arquivo de log, usado para marcar processos da aplicação
   * @param acao descrição simples do que está sendo feito Ex: "GET", "DEL", "UPD", "REQ"
   * @param id Usado para criar um arquivo com essa id, é usado principalmente no servidor para diferenciar logs de diferentes threads
   * @param operacao uma string com informações adicionais para aquele log 
   */
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
  /**
   * Esse metodo cria um arquivo de log específico para Erros, usado para marcar erros da aplicação
   * @param acao descrição simples do que está sendo feito Ex: "GET", "DEL", "UPD", "REQ"
   * @param id Usado para criar um arquivo com essa id, é usado principalmente no servidor para diferenciar logs de diferentes threads
   * @param operacao uma string com informações adicionais para aquele log e informações do erro
   */
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
  
  //João Jorge Stahl Gomes - 04/01/2022
  /**
   * Gera um código aleatório para a verificação via email
   * @return um inteiro que será usado como código de verificação
   */
  public static String GerarCodigo(){
      Random random = new Random();
      int codEmail;
          codEmail = 100000+random.nextInt(999999);
      return String.valueOf(codEmail);
  }
  
  //João Jorge Stahl Gomes - 04/01/2022
  /**
   * Exibe uma mensagem de confirmação para o usuário com Sim e Não como opções
   * @param pMsg a mensagem a ser exibida
   * @return true se apertou "Sim" e false caso apertou "Não" ou fechou a tela
   */
  public static boolean msgConfirma(String pMsg){
    Object[] opcoes = {"   Sim   ", "   Não   "};
    return JOptionPane.showOptionDialog(null, 
                                        pMsg, 
                                        "Aviso!", 
                                        JOptionPane.YES_NO_OPTION, 
                                        JOptionPane.QUESTION_MESSAGE, 
                                        null, 
                                        opcoes, 
                                        opcoes[1]) == JOptionPane.YES_OPTION;
                                           
  }
  
  //Mateus Roberto Algayer - 04/01/2022
  /**
   * Esse metodo cria um arquivo para ser usado como arquivo de configuração local
   * @param pNomeConf o nome do arquivo
   * @param pConteudoConf o conteudo/configuração do arquivo
   */
  public static void CriaConf(String pNomeConf, String pConteudoConf){
    try {
      String path = "Cfg/";

      File dir = new File(path);

      if(!dir.exists()){
          dir.mkdirs();
      }
      
      File arquivo = new File(path+pNomeConf+".config");
        
        if(!arquivo.exists()){
            arquivo.createNewFile();
        }

        try (BufferedWriter grava = new BufferedWriter(new FileWriter(arquivo))) {
            grava.write(pConteudoConf);
            
            if((new File("QuizIFDebug.debug")).exists()){
              System.out.println("GRA"+" - Gravado arquivo de configuração: "+pNomeConf);
            }
        }
    } catch (IOException e) {
      GravaLogErro("CFG", 0, "Erro ao criar o arquivo de configuração\n"+e.toString());
    }
  }
  
  //Mateus Roberto Algayer - 04/01/2022
  /**
   * Esse metodo le o conteudo de um arquivo de configuração
   * @param pNomeConf nome da configuração a ser lida
   * @return o conteudo da configuração especificada ou vazio caso não exista configuração
   */
  public static String LeConf(String pNomeConf){
    try {
      String path = "Cfg/";
      
      File arquivo = new File(path+pNomeConf+".config");
      
      if(!arquivo.exists()){
        return "";
      }
      
      try (Scanner conf = new Scanner(arquivo)) {
        String conteudo = "";
        while(conf.hasNext()){
          conteudo = conf.next();
        }
        return conteudo;
      }
    } catch (Exception e) {
      GravaLogErro("CFG", 0, "Erro ao ler o arquivo de configuração\n"+e.toString());
      return "";
    }
  }
}
