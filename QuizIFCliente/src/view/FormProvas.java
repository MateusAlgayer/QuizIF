
//Mateus Roberto Algayer - 10/01/2022

package view;

import ModelDominio.Prova;
import ModelDominio.Area;
import java.util.ArrayList;
import util.Metodos;
import view.util.ComboBoxArea;
import ModelDominio.Pergunta;
import view.tablemodel.PerguntasTableModel;

public class FormProvas extends javax.swing.JFrame {

  PerguntasTableModel GPerguntasDisModel;
  PerguntasTableModel GPerguntasSelModel;
  
  boolean GModoEdicao;
  Prova GProva;
  
  public FormProvas(Prova prova) {
    initComponents();
    Metodos.GeraConsistenciaCampos(rootPane);
    
    attComboArea();
    
    if(prova != null){
      AtualizaTabelas(prova.getCodigoProva());
      btExcluir.setVisible(true);
      GModoEdicao = true;
      GProva = prova;
    } else {
      AtualizaTabelas(0);
      btExcluir.setVisible(false);
      GModoEdicao = false;
    }
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    tfNome = new javax.swing.JTextField();
    jPanel1 = new javax.swing.JPanel();
    jScrollPane3 = new javax.swing.JScrollPane();
    tbPerSel = new javax.swing.JTable();
    btRemover = new javax.swing.JButton();
    btAdicionar = new javax.swing.JButton();
    jScrollPane4 = new javax.swing.JScrollPane();
    tbPerDis = new javax.swing.JTable();
    lbPerguntasSel = new javax.swing.JLabel();
    lbPerguntasDisp = new javax.swing.JLabel();
    jLabel7 = new javax.swing.JLabel();
    jLabel8 = new javax.swing.JLabel();
    btSair = new javax.swing.JButton();
    jLabel2 = new javax.swing.JLabel();
    cbArea = new javax.swing.JComboBox<>();
    jLabel3 = new javax.swing.JLabel();
    cbSituação = new javax.swing.JComboBox<>();
    btSalvar = new javax.swing.JButton();
    btExcluir = new javax.swing.JButton();
    jLabel4 = new javax.swing.JLabel();
    cbDificuldade = new javax.swing.JComboBox<>();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Manutenção de provas");
    setMinimumSize(new java.awt.Dimension(800, 600));
    setName("Manutenção de provas"); // NOI18N
    setPreferredSize(new java.awt.Dimension(800, 600));

    jLabel1.setText("Nome:");

    tfNome.setName("Nome"); // NOI18N

    jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

    tbPerSel.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {
        {null, null, null, null},
        {null, null, null, null},
        {null, null, null, null},
        {null, null, null, null}
      },
      new String [] {
        "Title 1", "Title 2", "Title 3", "Title 4"
      }
    ));
    tbPerSel.setShowGrid(true);
    tbPerSel.getTableHeader().setReorderingAllowed(false);
    jScrollPane3.setViewportView(tbPerSel);

    btRemover.setText("Remover");
    btRemover.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btRemoverActionPerformed(evt);
      }
    });

    btAdicionar.setText("Adicionar");
    btAdicionar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btAdicionarActionPerformed(evt);
      }
    });

    tbPerDis.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {
        {null, null, null, null},
        {null, null, null, null},
        {null, null, null, null},
        {null, null, null, null}
      },
      new String [] {
        "Title 1", "Title 2", "Title 3", "Title 4"
      }
    ));
    tbPerDis.setShowHorizontalLines(true);
    tbPerDis.setShowVerticalLines(true);
    tbPerDis.getTableHeader().setReorderingAllowed(false);
    jScrollPane4.setViewportView(tbPerDis);

    lbPerguntasSel.setText("0/30");

    lbPerguntasDisp.setText("contPer");

    jLabel7.setText("Perguntas selecionadas:");

    jLabel8.setText("Perguntas disponíveis:");

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(lbPerguntasSel)
              .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbPerguntasDisp))
              .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                  .addComponent(btAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                  .addComponent(btRemover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(jLabel7)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel8)))
        .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel7)
          .addComponent(jLabel8))
        .addGap(4, 4, 4)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(btAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(33, 33, 33)
            .addComponent(btRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
          .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
          .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        .addGap(4, 4, 4)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(lbPerguntasSel)
          .addComponent(lbPerguntasDisp))
        .addContainerGap())
    );

    btSair.setText("Voltar");
    btSair.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btSairActionPerformed(evt);
      }
    });

    jLabel2.setText("Área:");

    cbArea.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

    jLabel3.setText("Situação:");

    cbSituação.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ativo", "Inativo" }));
    cbSituação.setName("Situação"); // NOI18N

    btSalvar.setText("Salvar");

    btExcluir.setText("Excluir");
    btExcluir.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btExcluirActionPerformed(evt);
      }
    });

    jLabel4.setText("Dificuldade:");

    cbDificuldade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 - Fácil", "2 - Médio", "3 - Difícil" }));
    cbDificuldade.setSelectedIndex(1);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(jLabel1)
              .addComponent(jLabel2))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
              .addGroup(layout.createSequentialGroup()
                .addComponent(cbArea, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbDificuldade, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbSituação, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addComponent(tfNome))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
            .addComponent(btSair, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addGroup(layout.createSequentialGroup()
                .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap())))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(btSair, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addGroup(layout.createSequentialGroup()
            .addGap(16, 16, 16)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(jLabel1)
              .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(cbArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jLabel2)
              .addComponent(jLabel4)
              .addComponent(cbDificuldade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jLabel3)
              .addComponent(cbSituação, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(btSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
          .addComponent(btExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
    );

    pack();
    setLocationRelativeTo(null);
  }// </editor-fold>//GEN-END:initComponents

  private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
    dispose();
  }//GEN-LAST:event_btSairActionPerformed

  private void btRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoverActionPerformed
    if(tbPerSel.getSelectedRow() == -1)
      return;
    
    Pergunta p = GPerguntasSelModel.getPergunta(tbPerSel.getSelectedRow());
    
    GPerguntasSelModel.removePergunta(tbPerSel.getSelectedRow());
    
    GPerguntasDisModel.adicionaPergunta(p);
    
    AtualizaInfo();
  }//GEN-LAST:event_btRemoverActionPerformed

  private void btAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdicionarActionPerformed
    if(tbPerDis.getSelectedRow() == -1)
      return;
     
    Pergunta p = GPerguntasDisModel.getPergunta(tbPerDis.getSelectedRow());
    
    GPerguntasDisModel.removePergunta(tbPerDis.getSelectedRow());
    
    GPerguntasSelModel.adicionaPergunta(p);
    
    AtualizaInfo();
  }//GEN-LAST:event_btAdicionarActionPerformed

  private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
    if(Metodos.msgConfirma("Tem certeza que deseja excluir a prova '"+GProva.getNomeProva()+"'?"))
      QuizIFCliente.ccont.ExcluirProva(GProva.getCodigoProva());
  }//GEN-LAST:event_btExcluirActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btAdicionar;
  private javax.swing.JButton btExcluir;
  private javax.swing.JButton btRemover;
  private javax.swing.JButton btSair;
  private javax.swing.JButton btSalvar;
  private javax.swing.JComboBox<String> cbArea;
  private javax.swing.JComboBox<String> cbDificuldade;
  private javax.swing.JComboBox<String> cbSituação;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JLabel jLabel8;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JScrollPane jScrollPane3;
  private javax.swing.JScrollPane jScrollPane4;
  private javax.swing.JLabel lbPerguntasDisp;
  private javax.swing.JLabel lbPerguntasSel;
  private javax.swing.JTable tbPerDis;
  private javax.swing.JTable tbPerSel;
  private javax.swing.JTextField tfNome;
  // End of variables declaration//GEN-END:variables

  private void AtualizaTabelas(int numProva) {
    
    ArrayList<Pergunta> listaSel = new ArrayList<>();
    ArrayList<Pergunta> listaDis = new ArrayList<>();
    
    QuizIFCliente.ccont.getPerguntasProva(listaSel, listaDis, numProva);
    
    GPerguntasDisModel = new PerguntasTableModel(listaDis);
    tbPerDis.setModel(GPerguntasDisModel);
    
    GPerguntasSelModel = new PerguntasTableModel(listaSel);
    tbPerSel.setModel(GPerguntasSelModel);
    
    AtualizaInfo();
  }

  private void attComboArea() {
    ArrayList<Area> listaCombo = QuizIFCliente.ccont.getListaArea();
    
    ComboBoxArea.preencheComboboxArea(-1, cbArea, listaCombo, false);
  }
  
  public void AtualizaInfo(){
    
    GPerguntasDisModel.ordenaLista();
    GPerguntasDisModel.fireTableDataChanged();
    
    GPerguntasSelModel.ordenaLista();
    GPerguntasSelModel.fireTableDataChanged();
    
    lbPerguntasDisp.setText(Integer.toString(GPerguntasDisModel.getRowCount()));
    lbPerguntasSel.setText(Integer.toString(GPerguntasSelModel.getRowCount())+"/30");
  }
}
