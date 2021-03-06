
//Mateus Roberto Algayer - 04/01/2022 :: CriaConf e LeConf
//Mateus Roberto Algayer - 29/12/2021 :: se tiver arquivo debug vai printar no console os logs
//Mateus Roberto Algayer - 23/11/2021 :: Consistencia e Pedaco 

package util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;
import javax.swing.text.JTextComponent;

public class Metodos{
  
  //Par de nomes e tamanhos dos campos,
  //usado no Consistencia para validar se um campo não ultrapassou o limite dos campos da base.
  private static HashMap<String, Integer> GTamanhoCampos;
  
  /**
   * setter do hashmap de tamanhos dos campos. Não é necessário setar novamente após a primeira vez
   * @param listaCampos lista de hashMap que vem do servidor quando a aplicação inicializa
   */
  public static void setTamanhoCampos(HashMap<String, Integer> listaCampos){
    GTamanhoCampos = listaCampos;
  }
  
  /**
   * metodo para adicionar tamanhos personalizados de campo no hashmap 
   * CUIDADO: colocar uma string que já existe sobrescreve o conteudo daquele par
   * @param campo String que representa a chave a ser utilizada para identificar o campo
   * @param tamanho inteiro que representa o tamanho personalizado do campo
   */
  public static void putTamanhoCampoPerso(String campo, int tamanho){
    GTamanhoCampos.put(campo, tamanho);
  }
  
  /**
   * getter do hashmap de tamanhos dos campos. 
   * @param nomeCampo nomedatabela.nomedocampo
   * @return retorna o tamanho do campo passado como parâmetro na base
   */
  public static int getTamanhoCampo(String nomeCampo){
    return GTamanhoCampos.get(nomeCampo);
  }
  
  //Mateus Roberto Algayer - 23/11/2021
  /**
   * O método consistencia serve para demonstrar visualmente se um campo é inválido
   * (Vazio ou grande suficiente para não caber no banco)
   * @param pGeraAviso - variável caso tenha que ter aviso ao usuário
   * @param wComps - variavel que representa um número n de componentes a serem verificados na consistencia
   * @return true se o campo é válido, false se o campo é inválido
   */
  public static Boolean consistencia(Boolean pGeraAviso,JComponent ...wComps){
    
    for(JComponent wComp : wComps){
      
      //Consistências para campos de texto
      if((wComp instanceof JTextComponent wCompTexto)) {
        
        //testa se vazio
        if(wCompTexto.getText().trim().equals("")){
          
          if(pGeraAviso) aviso(wComp.getTopLevelAncestor().getName(), "campo '"+wComp.getToolTipText()+"' inválido\nO campo está vazio.");
          
          wComp.setBackground(Color.YELLOW);
          wComp.requestFocus();
          return false; 
        }
              
        wComp.setBackground(Color.WHITE);
        
        //não precisa continuar se não é um campo com verificação de tamanho.
        //campos sem verificação de tamanho precisam de implementações customizadas de tamanho.
        if(GTamanhoCampos == null || !GTamanhoCampos.containsKey(wComp.getName()))
          continue;
        
        //testa o tamanho do campo
        if(wCompTexto.getText().length() > GTamanhoCampos.get(wComp.getName())){
          
          if(pGeraAviso) aviso(wComp.getTopLevelAncestor().getName(), "campo '"+wComp.getToolTipText()+"' inválido\nmáximo de caracteres suportados:\n"+
                                                                      GTamanhoCampos.get(wComp.getName())+"\nNúmero de caracteres atual:\n"+wCompTexto.getText().length());
          
          wComp.setBackground(Color.YELLOW);
          wComp.requestFocus();
          return false; 
        }
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
  public static String pedaco(String pLinha, String pDelimita, int pNumString){
    
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
   * Este metodo configura o formulário com alguns tratamentos que devem ser utilizados
   * @param pForm - representa uma tela, a raiz que possui todos os outros componentes
   */
  public static void configuraForms(Window pForm){
    
    java.net.URL url = ClassLoader.getSystemResource("view/imagens/QuizIFLogo.png");
    Toolkit kit = Toolkit.getDefaultToolkit();
    Image img = kit.createImage(url);
    pForm.setIconImage(img);
    
    ArrayList<Component> wListaComps = new ArrayList<>();
    
    JRootPane jrp;
    
    if( pForm instanceof JFrame jFrame){
      jrp = jFrame.getRootPane();
    } else if(pForm instanceof JDialog jDialog){
      jrp = jDialog.getRootPane();
    } else {
      return;
    }
    
    wListaComps.addAll(pegaTodosComponentes(jrp));
    
    //Pega todos os componentes na lista e abre uma stream
    //então ele filtra pelos componentes que são instancias de JTextComponent 
    //ordenando cada e criando um evento KeyListener com o método "Consistencia"
    wListaComps.stream().filter(wComp -> (wComp instanceof JTextComponent)).forEachOrdered(wComp -> {
      
      ((JTextComponent)wComp).addKeyListener(new java.awt.event.KeyAdapter() {
        
        @Override
        public void keyReleased(java.awt.event.KeyEvent evt) {
          consistencia(Boolean.FALSE, ((JTextComponent)wComp));
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
  public static ArrayList<Component> pegaTodosComponentes(final Container pComp){
    ArrayList<Component> wListaComps = new ArrayList<>();
    
    Component[] wComps = pComp.getComponents();
    
    for(Component wComp: wComps){
      wListaComps.add(wComp);
      
      //se wComp é um container, chama PegaTodosComponentes recursivamente
      if(wComp instanceof Container container) wListaComps.addAll(pegaTodosComponentes(container));
    }
    
    return wListaComps;
  }
  
  //Mateus Roberto Algayer - 26/11/2021
  /**
   * Metodo para mostrar uma mensagem de erro
   * @param pTitulo titulo da mensagem
   * @param pMsg mensagem
   */
  public static void erro(String pTitulo, String pMsg){
    JOptionPane.showMessageDialog(null, pMsg, pTitulo, JOptionPane.ERROR_MESSAGE);
  }
  
  //Mateus Roberto Algayer - 26/11/2021
   /**
   * Metodo para mostrar uma mensagem de aviso
   * @param pTitulo titulo da mensagem
   * @param pMsg mensagem
   */
  public static void aviso(String pTitulo, String pMsg){
    JOptionPane.showMessageDialog(null, pMsg, pTitulo, JOptionPane.WARNING_MESSAGE);
  }
  
  //Mateus Roberto Algayer - 11/12/2021
   /**
   * Metodo para mostrar uma mensagem de sucesso
   * @param pTitulo titulo da mensagem
   * @param pMsg mensagem
   */
  public static void sucesso(String pTitulo, String pMsg){
    JOptionPane.showMessageDialog(null, pMsg, pTitulo, JOptionPane.INFORMATION_MESSAGE);
  }
  
  //Mateus Roberto Algayer - 16/12/2021
  /**
   * Esse metodo cria um arquivo de log, usado para marcar processos da aplicação
   * @param acao descrição simples do que está sendo feito Ex: "GET", "DEL", "UPD", "REQ"
   * @param id Usado para criar um arquivo com essa id, é usado principalmente no servidor para diferenciar logs de diferentes threads
   * @param operacao uma string com informações adicionais para aquele log 
   */
  public static void gravaLog(String acao, int id, String operacao){
      
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
            grava.close();
            
            System.out.println(acao+" - "+id+" - "+Instant.now()+" - "+operacao);
        }
    } catch (IOException e) {
        gravaLogErro("ERR", id, e.toString());
    }
  }
  
  //Mateus Roberto Algayer - 16/12/2021
  /**
   * Esse metodo cria um arquivo de log específico para Erros, usado para marcar erros da aplicação
   * @param acao descrição simples do que está sendo feito Ex: "GET", "DEL", "UPD", "REQ"
   * @param id Usado para criar um arquivo com essa id, é usado principalmente no servidor para diferenciar logs de diferentes threads
   * @param operacao uma string com informações adicionais para aquele log e informações do erro
   */
  public static void gravaLogErro(String acao, int id, String operacao){
      
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
            grava.close();
            
            System.out.println(acao+" - "+id+" - "+Instant.now()+" - "+operacao);
        }
    } catch (IOException e) {
    }
  }
  
  //João Jorge Stahl Gomes - 04/01/2022
  /**
   * Gera um código aleatório para a verificação via email
   * @return um inteiro que será usado como código de verificação
   */
  public static String gerarCodigo(){
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
  public static void criaConf(String pNomeConf, String pConteudoConf){
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
      gravaLogErro("CFG", 0, "Erro ao criar o arquivo de configuração\n"+e.toString());
    }
  }
  
  //Mateus Roberto Algayer - 04/01/2022
  /**
   * Esse metodo le o conteudo de um arquivo de configuração
   * @param pNomeConf nome da configuração a ser lida
   * @return o conteudo da configuração especificada ou vazio caso não exista configuração
   */
  public static String leConf(String pNomeConf){
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
      gravaLogErro("CFG", 0, "Erro ao ler o arquivo de configuração\n"+e.toString());
      return "";
    }
  }
  
  /**
   * Método para ajustar os tamanhos das colunas
   * @param tabela a tabela onde este modelo será aplicado
   * @param arrayTamanhos array com tamanhos de cada coluna, caso o valor passado seja 0, então a própria tabela define os mínimos e máximos
   */
  public static void ajustaColunas(JTable tabela,int[] arrayTamanhos){
    TableColumnModel coluna = tabela.getColumnModel();
    int colCount = tabela.getColumnCount();
    for (int i = 0; i < colCount; i++) {
      
      //se for zero ou não ter valor vai pro próximo 
      if(arrayTamanhos[i] == 0 || arrayTamanhos == null)
        continue;
        
      coluna.getColumn(i).setMinWidth(arrayTamanhos[i]);
      coluna.getColumn(i).setMaxWidth(arrayTamanhos[i]*6);
    }
    tabela.setColumnModel(coluna);
  }
  
  /**
   * Método usado para padronizar as mensagens de resposta do servidor
   * @param titulo titulo da tela
   * @param res resposta do servidor
   * @param sucesso resposta exibida em caso de sucesso
   * @param erro resposta complementar a ser demonstrada em casos de erro
   * @return true caso dê sucesso false em outros casos
   */
  public static boolean processaMsgServidor(String titulo, String res, String sucesso, String erro){
    switch(pedaco(res,"^",1)){
        case "A" -> {
          Metodos.aviso(titulo, pedaco(res,"^",2));
          break;
        }
        case "S" -> {
          if(!sucesso.isEmpty())
            Metodos.sucesso(titulo, sucesso);
          return true;
        }
        default -> {
          String msgErro = pedaco(res,"^",2);
          
          if(msgErro.equals(""))
            msgErro = erro;
          
          Metodos.erro(titulo, erro+"\n"+msgErro);
          break;
        }
    } 
    return false;
  }
}
