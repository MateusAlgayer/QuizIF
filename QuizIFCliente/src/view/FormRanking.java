
//Mateus Roberto Algayer - 17/01/2022
//João Felipe Staub - 18/01/2022 :: Término

package view;

import view.tablemodel.RankingTableModel;

public class FormRanking extends javax.swing.JDialog {

    private RankingTableModel rankingModel;
    
  public FormRanking() {
      
    initComponents();
    AtualizaTabela();
    
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    btVoltar = new javax.swing.JButton();
    jPanel1 = new javax.swing.JPanel();
    spResultado = new javax.swing.JScrollPane();
    tRanking = new javax.swing.JTable();
    cbFiltro = new javax.swing.JComboBox<>();
    btAtualizar = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Ranking");
    setMinimumSize(new java.awt.Dimension(500, 503));
    setName("Ranking"); // NOI18N

    jLabel1.setText("Filtro:");

    btVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icoFechar32.png"))); // NOI18N
    btVoltar.setText("Voltar");
    btVoltar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btVoltarActionPerformed(evt);
      }
    });

    spResultado.setName(""); // NOI18N

    tRanking.setModel(new javax.swing.table.DefaultTableModel(
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
    tRanking.getTableHeader().setResizingAllowed(false);
    tRanking.getTableHeader().setReorderingAllowed(false);
    spResultado.setViewportView(tRanking);

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(spResultado, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
        .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(spResultado)
        .addContainerGap())
    );

    cbFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Média de acertos", "Nº Acertos", "Nº Perguntas" }));
    cbFiltro.setName("Filtro"); // NOI18N
    cbFiltro.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cbFiltroActionPerformed(evt);
      }
    });

    btAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icoAtualizar32.png"))); // NOI18N
    btAtualizar.setText("Atualizar");
    btAtualizar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btAtualizarActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel1)
            .addGap(18, 18, 18)
            .addComponent(cbFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btAtualizar)
            .addGap(18, 18, 18)
            .addComponent(btVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(btAtualizar, javax.swing.GroupLayout.Alignment.TRAILING)
          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(btVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel1)
            .addComponent(cbFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addContainerGap())
    );

    layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btAtualizar, btVoltar});

    jPanel1.getAccessibleContext().setAccessibleName("Ranking");

    pack();
    setLocationRelativeTo(null);
  }// </editor-fold>//GEN-END:initComponents

    private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
        dispose();
    }//GEN-LAST:event_btVoltarActionPerformed

    private void btAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtualizarActionPerformed
        AtualizaTabela();
    }//GEN-LAST:event_btAtualizarActionPerformed

    private void cbFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFiltroActionPerformed
        AtualizaTabela();
    }//GEN-LAST:event_cbFiltroActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btAtualizar;
  private javax.swing.JButton btVoltar;
  private javax.swing.JComboBox<String> cbFiltro;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JScrollPane spResultado;
  private javax.swing.JTable tRanking;
  // End of variables declaration//GEN-END:variables

    private void AtualizaTabela(){
        
        switch(cbFiltro.getSelectedIndex()){
            case 1 -> rankingModel = new RankingTableModel(QuizIFCliente.ccont.getRanking(cbFiltro.getSelectedIndex()));
            case 2 -> rankingModel = new RankingTableModel(QuizIFCliente.ccont.getRanking(cbFiltro.getSelectedIndex()));
            default -> rankingModel = new RankingTableModel(QuizIFCliente.ccont.getRanking(cbFiltro.getSelectedIndex()));
        }
        
        tRanking.setModel(rankingModel);
        rankingModel.AtualizaColunas(tRanking);
    }
    
}
