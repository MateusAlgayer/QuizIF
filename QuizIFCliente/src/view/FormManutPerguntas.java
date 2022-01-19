
//Mateus Roberto Algayer - 03/01/2022 :: Criação

package view;

import ModelDominio.Area;
import ModelDominio.Pergunta;
import java.util.ArrayList;
import util.Metodos;
import view.tablemodel.PerguntasTableModel;
import view.util.ComboBoxArea;

public class FormManutPerguntas extends javax.swing.JFrame {

  private ArrayList<Area> GListaCombo;
  private PerguntasTableModel GPerguntas;
  
  public FormManutPerguntas() {
    initComponents();
    Metodos.GeraConsistenciaCampos(rootPane);
    
    attComboBoxArea(-1);
    
    AtualizaTabela();
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    btVoltar = new javax.swing.JButton();
    jScrollPane1 = new javax.swing.JScrollPane();
    tbPerguntas = new javax.swing.JTable();
    btAtualizar = new javax.swing.JButton();
    jButton1 = new javax.swing.JButton();
    jButton2 = new javax.swing.JButton();
    cbArea = new javax.swing.JComboBox<>();
    cbDificuldade = new javax.swing.JComboBox<>();
    cbSituacao = new javax.swing.JComboBox<>();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    jScrollPane2 = new javax.swing.JScrollPane();
    tfPergunta = new javax.swing.JTextArea();
    jLabel4 = new javax.swing.JLabel();
    jLabel5 = new javax.swing.JLabel();
    Correta1 = new javax.swing.JCheckBox();
    jLabel6 = new javax.swing.JLabel();
    Correta2 = new javax.swing.JCheckBox();
    jLabel7 = new javax.swing.JLabel();
    Correta3 = new javax.swing.JCheckBox();
    jLabel8 = new javax.swing.JLabel();
    Correta4 = new javax.swing.JCheckBox();
    tfAlt1 = new javax.swing.JTextField();
    tfAlt2 = new javax.swing.JTextField();
    tfAlt3 = new javax.swing.JTextField();
    tfAlt4 = new javax.swing.JTextField();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Manutenção de perguntas");
    setMinimumSize(new java.awt.Dimension(910, 750));
    setName("Manutenção de perguntas"); // NOI18N
    setPreferredSize(new java.awt.Dimension(910, 750));

    btVoltar.setText("Voltar");
    btVoltar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btVoltarActionPerformed(evt);
      }
    });

    tbPerguntas.setModel(new javax.swing.table.DefaultTableModel(
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
    tbPerguntas.getTableHeader().setResizingAllowed(false);
    tbPerguntas.getTableHeader().setReorderingAllowed(false);
    tbPerguntas.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        tbPerguntasMouseClicked(evt);
      }
    });
    jScrollPane1.setViewportView(tbPerguntas);

    btAtualizar.setText("Atualizar");
    btAtualizar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btAtualizarActionPerformed(evt);
      }
    });

    jButton1.setText("Salvar");

    jButton2.setText("Excluir");

    cbDificuldade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 - Fácil", "2 - Médio", "3 - Difícil" }));
    cbDificuldade.setSelectedIndex(1);

    cbSituacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ativo", "Inativo" }));

    jLabel1.setText("Área:");

    jLabel2.setText("Dificuldade:");

    jLabel3.setText("Situação:");

    tfPergunta.setColumns(20);
    tfPergunta.setRows(5);
    tfPergunta.setName("Pergunta"); // NOI18N
    jScrollPane2.setViewportView(tfPergunta);

    jLabel4.setText("Pergunta:");

    jLabel5.setText("Alternativa 1:");

    Correta1.setText("Correta");

    jLabel6.setText("Alternativa 2:");

    Correta2.setText("Correta");

    jLabel7.setText("Alternativa 3:");

    Correta3.setText("Correta");

    jLabel8.setText("Alternativa 4:");

    Correta4.setText("Correta");

    tfAlt1.setName("Alternativa 1"); // NOI18N

    tfAlt2.setName("Alternativa 2"); // NOI18N

    tfAlt3.setName("Alternativa 3"); // NOI18N

    tfAlt4.setName("Alternativa 4"); // NOI18N

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jScrollPane1)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addComponent(btAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Correta3))
              .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Correta4)))
            .addGap(0, 0, Short.MAX_VALUE))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(tfAlt3, javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(tfAlt4, javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(tfAlt1, javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(tfAlt2, javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1))
              .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(cbArea, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(cbDificuldade, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(cbSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                  .addComponent(jLabel4)
                  .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel5)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(Correta1))
                  .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel6)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(Correta2)))
                .addGap(0, 208, Short.MAX_VALUE)))
            .addContainerGap())))
    );

    layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2});

    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(btVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(btAtualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(cbArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(cbDificuldade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(cbSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel1)
          .addComponent(jLabel2)
          .addComponent(jLabel3))
        .addGap(7, 7, 7)
        .addComponent(jLabel4)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel5)
          .addComponent(Correta1))
        .addGap(1, 1, 1)
        .addComponent(tfAlt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel6)
          .addComponent(Correta2))
        .addGap(3, 3, 3)
        .addComponent(tfAlt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel7)
          .addComponent(Correta3))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(tfAlt3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel8)
          .addComponent(Correta4))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(tfAlt4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jButton1))
        .addContainerGap())
    );

    layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2});

    pack();
    setLocationRelativeTo(null);
  }// </editor-fold>//GEN-END:initComponents

  private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
    dispose();
  }//GEN-LAST:event_btVoltarActionPerformed

  private void btAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtualizarActionPerformed
    dispose();
  }//GEN-LAST:event_btAtualizarActionPerformed

  private void tbPerguntasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPerguntasMouseClicked
    if(tbPerguntas.getSelectedRow() == -1)
      return; 
    
    AtualizaInfoPer(tbPerguntas.getSelectedRow());
  }//GEN-LAST:event_tbPerguntasMouseClicked

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JCheckBox Correta1;
  private javax.swing.JCheckBox Correta2;
  private javax.swing.JCheckBox Correta3;
  private javax.swing.JCheckBox Correta4;
  private javax.swing.JButton btAtualizar;
  private javax.swing.JButton btVoltar;
  private javax.swing.JComboBox<Area> cbArea;
  private javax.swing.JComboBox<String> cbDificuldade;
  private javax.swing.JComboBox<String> cbSituacao;
  private javax.swing.JButton jButton1;
  private javax.swing.JButton jButton2;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JLabel jLabel8;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JTable tbPerguntas;
  private javax.swing.JTextField tfAlt1;
  private javax.swing.JTextField tfAlt2;
  private javax.swing.JTextField tfAlt3;
  private javax.swing.JTextField tfAlt4;
  private javax.swing.JTextArea tfPergunta;
  // End of variables declaration//GEN-END:variables

  private void attComboBoxArea(int i) {
    GListaCombo = QuizIFCliente.ccont.getListaArea();
    
    ComboBoxArea.preencheComboboxArea(i, cbArea, GListaCombo, false);
  }

  private void AtualizaTabela() {
     
    ArrayList<Pergunta> listaPer = QuizIFCliente.ccont.getPerguntas();
    if(listaPer != null){
      GPerguntas = new PerguntasTableModel(listaPer);
      tbPerguntas.setModel(GPerguntas);
    }
  }

  private void AtualizaInfoPer(int selectedRow) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
}
