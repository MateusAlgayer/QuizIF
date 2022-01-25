
//Mateus Roberto Algayer - 17/01/2022

package view;

import ModelDominio.Jogo;
import ModelDominio.Pergunta;
import ModelDominio.Prova;
import ModelDominio.Usuario;
import controller.InfoApp;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import util.Metodos;
import static util.Metodos.Pedaco;

public class FormJogo extends javax.swing.JDialog {
  
  private final int GNumPerguntas;
  private final ArrayList<Pergunta> GListaPergunta = new ArrayList<>();
  private final HashMap<Integer, Boolean> GRespostas = new HashMap<>();
  private final Prova GNumProva;
  private int GProgresso = 0;
  private Pergunta GPerAtual;
  private Jogo GResultJogo;
  private boolean GGravado;
  
  public FormJogo(Prova jogo) {
    initComponents();
      
    lbNomeProva.setText(jogo.getNomeProva());
    lbDif.setText(jogo.getDificuldadeLiteral());
    
    GNumProva = new Prova(jogo.getCodigoProva());
    
    panelResultado.setVisible(false);
    
    QuizIFCliente.ccont.getPerguntasJogo(GListaPergunta,  jogo.getCodigoProva());
    
    if(GListaPergunta == null){
      Metodos.Erro(this.getTitle(), "Ocorreu um erro ao carregar a prova.");
      dispose();
    }
    
    GNumPerguntas = GListaPergunta.size();
    
    lbNumPerguntas.setText(String.valueOf(GNumPerguntas));
    
    CarregaProxPergunta();
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    panelPrincipal = new javax.swing.JPanel();
    jPanel1 = new javax.swing.JPanel();
    btAlt3 = new javax.swing.JButton();
    btAlt1 = new javax.swing.JButton();
    btAlt2 = new javax.swing.JButton();
    btAlt4 = new javax.swing.JButton();
    jScrollPane1 = new javax.swing.JScrollPane();
    taPergunta = new javax.swing.JTextArea();
    lbProgresso = new javax.swing.JLabel();
    btSair = new javax.swing.JButton();
    panelResultado = new javax.swing.JPanel();
    jPanel2 = new javax.swing.JPanel();
    jScrollPane2 = new javax.swing.JScrollPane();
    tbResumo = new javax.swing.JTable();
    jLabel1 = new javax.swing.JLabel();
    lbNumPerguntas = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    lbNumAcertos = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    lbNomeProva = new javax.swing.JLabel();
    jLabel4 = new javax.swing.JLabel();
    lbDif = new javax.swing.JLabel();
    btSalvar = new javax.swing.JButton();
    btSair2 = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
    setMinimumSize(new java.awt.Dimension(780, 600));
    setName("Jogo"); // NOI18N
    setPreferredSize(new java.awt.Dimension(780, 600));
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent evt) {
        formWindowClosing(evt);
      }
    });

    btAlt3.setText("Alt3");
    btAlt3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btAlt3ActionPerformed(evt);
      }
    });

    btAlt1.setText("Alt1");
    btAlt1.setActionCommand("<html><div style=\"\"word-wrap: break-word\">FINALIZAR COMPRA</div>");
    btAlt1.setDefaultCapable(false);
    btAlt1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btAlt1ActionPerformed(evt);
      }
    });

    btAlt2.setText("Alt2");
    btAlt2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btAlt2ActionPerformed(evt);
      }
    });

    btAlt4.setText("Alt4");
    btAlt4.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btAlt4ActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addGap(28, 28, 28)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(btAlt3, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
          .addComponent(btAlt1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGap(95, 95, 95)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(btAlt2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
          .addComponent(btAlt4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGap(28, 28, 28))
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
        .addContainerGap(14, Short.MAX_VALUE)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(btAlt2)
          .addComponent(btAlt1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(18, 18, 18)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(btAlt3)
          .addComponent(btAlt4))
        .addContainerGap())
    );

    jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btAlt1, btAlt2, btAlt3, btAlt4});

    taPergunta.setEditable(false);
    taPergunta.setColumns(20);
    taPergunta.setLineWrap(true);
    taPergunta.setRows(5);
    taPergunta.setWrapStyleWord(true);
    jScrollPane1.setViewportView(taPergunta);

    lbProgresso.setText("ProgProva");

    btSair.setText("Sair");
    btSair.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btSairActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
    panelPrincipal.setLayout(panelPrincipalLayout);
    panelPrincipalLayout.setHorizontalGroup(
      panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelPrincipalLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
            .addGap(55, 55, 55)
            .addComponent(jScrollPane1)
            .addGap(51, 51, 51))
          .addGroup(panelPrincipalLayout.createSequentialGroup()
            .addGap(25, 25, 25)
            .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addComponent(lbProgresso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btSair, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(26, 26, 26)))))
        .addGap(0, 0, 0))
    );
    panelPrincipalLayout.setVerticalGroup(
      panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelPrincipalLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(btSair, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(lbProgresso))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
    );

    panelResultado.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resumo da prova", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

    jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Resultado"));

    tbResumo.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {

      },
      new String [] {
        "Pergunta", "Correta"
      }
    ) {
      Class[] types = new Class [] {
        java.lang.String.class, java.lang.String.class
      };
      boolean[] canEdit = new boolean [] {
        false, false
      };

      public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    tbResumo.getTableHeader().setReorderingAllowed(false);
    jScrollPane2.setViewportView(tbResumo);

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        .addContainerGap())
    );
    jPanel2Layout.setVerticalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        .addContainerGap())
    );

    jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
    jLabel1.setText("Perguntas respondidas:");

    lbNumPerguntas.setText("numPerguntas");

    jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
    jLabel2.setText("Acertos:");

    lbNumAcertos.setText("numAcertos");

    jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
    jLabel3.setText("Prova:");

    lbNomeProva.setText("nomeProva");

    jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
    jLabel4.setText("Dificuldade:");

    lbDif.setText("dif");

    btSalvar.setText("Salvar");
    btSalvar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btSalvarActionPerformed(evt);
      }
    });

    btSair2.setText("Sair");
    btSair2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btSair2ActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout panelResultadoLayout = new javax.swing.GroupLayout(panelResultado);
    panelResultado.setLayout(panelResultadoLayout);
    panelResultadoLayout.setHorizontalGroup(
      panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelResultadoLayout.createSequentialGroup()
        .addGap(26, 26, 26)
        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(panelResultadoLayout.createSequentialGroup()
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(lbNumPerguntas)
              .addComponent(jLabel1)
              .addComponent(jLabel2)
              .addComponent(lbNumAcertos)
              .addComponent(jLabel3)
              .addComponent(lbNomeProva)
              .addComponent(jLabel4)
              .addComponent(lbDif)))
          .addGroup(panelResultadoLayout.createSequentialGroup()
            .addGap(27, 27, 27)
            .addGroup(panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(btSair2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))))
        .addGap(50, 50, 50))
    );
    panelResultadoLayout.setVerticalGroup(
      panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panelResultadoLayout.createSequentialGroup()
        .addGroup(panelResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addGroup(panelResultadoLayout.createSequentialGroup()
            .addGap(40, 40, 40)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          .addGroup(panelResultadoLayout.createSequentialGroup()
            .addGap(54, 54, 54)
            .addComponent(jLabel3)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(lbNomeProva)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jLabel4)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(lbDif)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(lbNumPerguntas)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jLabel2)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(lbNumAcertos)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
            .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(31, 31, 31)
            .addComponent(btSair2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addGap(42, 42, 42))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addComponent(panelResultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGap(0, 0, 0)
        .addComponent(panelResultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    pack();
    setLocationRelativeTo(null);
  }// </editor-fold>//GEN-END:initComponents

  private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
    if(Metodos.msgConfirma("Tem certeza que deseja sair? a prova será cancelada!")){
      dispose();
    }
  }//GEN-LAST:event_btSairActionPerformed

  private void btAlt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlt1ActionPerformed
    RespondePergunta(1);
  }//GEN-LAST:event_btAlt1ActionPerformed

  private void btAlt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlt2ActionPerformed
    RespondePergunta(2);
  }//GEN-LAST:event_btAlt2ActionPerformed

  private void btAlt3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlt3ActionPerformed
    RespondePergunta(3);
  }//GEN-LAST:event_btAlt3ActionPerformed

  private void btAlt4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlt4ActionPerformed
    RespondePergunta(4);
  }//GEN-LAST:event_btAlt4ActionPerformed

  private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    if(panelPrincipal.isVisible()){
      if(Metodos.msgConfirma("Tem certeza que deseja sair? a prova será cancelada!")){
        dispose();
      }
    } else if(panelResultado.isVisible()){
      if(Metodos.msgConfirma("Tem certeza que deseja sair? o resultado da prova não será gravado!")){
        dispose();
      }
    }
  }//GEN-LAST:event_formWindowClosing

  private void btSair2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSair2ActionPerformed
    if(Metodos.msgConfirma("Tem certeza que deseja sair? o resultado da prova não será gravado!")){
      dispose();
    }
  }//GEN-LAST:event_btSair2ActionPerformed

  private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
    if(!Metodos.msgConfirma("Após salvar a tela será fechada.\nDeseja continuar?"))
      return;
    
    if(QuizIFCliente.ccont.GravaResultJogo(GResultJogo)){
      Metodos.Sucesso(this.getTitle(), "Jogo gravado com sucesso!");
      dispose();
    } else {
      Metodos.Erro(this.getTitle(), "Erro ao gravar o resultado");
    }
  }//GEN-LAST:event_btSalvarActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btAlt1;
  private javax.swing.JButton btAlt2;
  private javax.swing.JButton btAlt3;
  private javax.swing.JButton btAlt4;
  private javax.swing.JButton btSair;
  private javax.swing.JButton btSair2;
  private javax.swing.JButton btSalvar;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JLabel lbDif;
  private javax.swing.JLabel lbNomeProva;
  private javax.swing.JLabel lbNumAcertos;
  private javax.swing.JLabel lbNumPerguntas;
  private javax.swing.JLabel lbProgresso;
  private javax.swing.JPanel panelPrincipal;
  private javax.swing.JPanel panelResultado;
  private javax.swing.JTextArea taPergunta;
  private javax.swing.JTable tbResumo;
  // End of variables declaration//GEN-END:variables

  private void CarregaProxPergunta() {
    if(GProgresso == GNumPerguntas){
      TerminaProva();
      return;
    }
    
    lbProgresso.setText(String.valueOf(GProgresso+1)+"/"+String.valueOf(GNumPerguntas));
    
    GPerAtual = GListaPergunta.get(GProgresso);
    
    taPergunta.setText(GPerAtual.getPergunta());
    
    //o html e o br são adições para formatar as linhas com quebra de linha.
    btAlt1.setText("<html>"+Pedaco(GPerAtual.getAlternativas(),"Ѫ", 1)+"<br>");
    btAlt2.setText("<html>"+Pedaco(GPerAtual.getAlternativas(),"Ѫ", 2)+"<br>");
    btAlt3.setText("<html>"+Pedaco(GPerAtual.getAlternativas(),"Ѫ", 3)+"<br>");
    btAlt4.setText("<html>"+Pedaco(GPerAtual.getAlternativas(),"Ѫ", 4)+"<br>");
  }

  private void RespondePergunta(int resposta) {
    
    GRespostas.put(GProgresso, GPerAtual.getCorreta() == resposta);
    
    GProgresso++;
    CarregaProxPergunta();
  }

  private void TerminaProva() {
    panelPrincipal.setVisible(false);
    panelResultado.setVisible(true);    
    
    String col[] = {"Pergunta", "Correta"};    
    DefaultTableModel model = new DefaultTableModel(col, 0);
    
    int numAcertos = 0;
    
    for (int i = 0; i < GListaPergunta.size(); i++) {       
      Pergunta pergunta = GListaPergunta.get(i);
      
      boolean certo = GRespostas.get(i);
      
      String rowData[] = {pergunta.getPergunta(),certo  ? "Acertou" : "Errou"};
      
      if(certo)
        numAcertos++;
      
      model.addRow(rowData);
    }

    tbResumo.setModel(model);
    lbNumAcertos.setText(String.valueOf(numAcertos));
    
    GResultJogo = new Jogo(GNumProva, new Usuario(InfoApp.getGUsuLogado().getCodUsuario()), GNumPerguntas, numAcertos);   
  }
}
