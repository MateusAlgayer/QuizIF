
//Mateus Roberto Algayer - 30/12/2021 :: Criação

package view;

import ModelDominio.Administrador;
import ModelDominio.Criador;
import ModelDominio.Prova;
import controller.InfoApp;
import java.util.ArrayList;

import view.tablemodel.ProvasTableModel;

public class FormPrincipal extends javax.swing.JFrame{

  ProvasTableModel GProvas;
  
  public FormPrincipal() {
    initComponents();
    
    btAdmin.setVisible(InfoApp.getGUsuLogado() instanceof Administrador);
    btCriador.setVisible(InfoApp.getGUsuLogado() instanceof Criador);
    
    AtualizaTabela();
  }                        

  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    PopAdmin = new javax.swing.JPopupMenu();
    MIManutPermisso = new javax.swing.JMenuItem();
    MIManutPerguntas = new javax.swing.JMenuItem();
    btPerfil = new javax.swing.JButton();
    btAdmin = new javax.swing.JButton();
    btCriador = new javax.swing.JButton();
    jPanel1 = new javax.swing.JPanel();
    jScrollPane1 = new javax.swing.JScrollPane();
    tbProvas = new javax.swing.JTable();
    btJogar = new javax.swing.JButton();
    btAtualizar = new javax.swing.JButton();
    btSobre = new javax.swing.JButton();
    btRanking = new javax.swing.JButton();

    MIManutPermisso.setText("Permissões de usuários");
    MIManutPermisso.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        MIManutPermissoActionPerformed(evt);
      }
    });
    PopAdmin.add(MIManutPermisso);

    MIManutPerguntas.setText("Manutenção de Perguntas");
    MIManutPerguntas.setActionCommand("Manutenção de perguntas");
    MIManutPerguntas.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        MIManutPerguntasActionPerformed(evt);
      }
    });
    PopAdmin.add(MIManutPerguntas);

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("QuizIF");
    setMinimumSize(new java.awt.Dimension(840, 600));
    setName("QuizIF"); // NOI18N

    btPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icoUsuario32.png"))); // NOI18N
    btPerfil.setText("Perfil");
    btPerfil.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btPerfilActionPerformed(evt);
      }
    });

    btAdmin.setText("Administrador");
    btAdmin.setName(""); // NOI18N
    btAdmin.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btAdminActionPerformed(evt);
      }
    });

    btCriador.setText("Criação de jogos");
    btCriador.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btCriadorActionPerformed(evt);
      }
    });

    jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Jogos"));

    tbProvas.setModel(new javax.swing.table.DefaultTableModel(
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
    tbProvas.setName("Tabela de Jogos"); // NOI18N
    tbProvas.getTableHeader().setReorderingAllowed(false);
    jScrollPane1.setViewportView(tbProvas);

    btJogar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icoJogar32.png"))); // NOI18N
    btJogar.setText("Jogar");
    btJogar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btJogarActionPerformed(evt);
      }
    });

    btAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icoAtualizar32.png"))); // NOI18N
    btAtualizar.setText("Atualizar");
    btAtualizar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btAtualizarActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addGap(21, 21, 21)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(btAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btJogar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(0, 17, Short.MAX_VALUE))
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(btJogar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(btAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap())
    );

    btSobre.setText("Sobre");
    btSobre.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btSobreActionPerformed(evt);
      }
    });

    btRanking.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icoRanking32.png"))); // NOI18N
    btRanking.setText("Ranking");
    btRanking.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btRankingActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(btAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(btCriador, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(btRanking, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(btPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
      .addGroup(layout.createSequentialGroup()
        .addComponent(btSobre, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(0, 0, Short.MAX_VALUE))
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap(23, Short.MAX_VALUE)
        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(31, 31, 31))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(btPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btRanking, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
              .addComponent(btCriador, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
              .addComponent(btAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGap(18, 18, 18)
        .addComponent(btSobre, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
    );

    pack();
    setLocationRelativeTo(null);
  }// </editor-fold>//GEN-END:initComponents

  private void btPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPerfilActionPerformed
    FormPerfil frmPerfil = new FormPerfil();
    frmPerfil.setVisible(true);
  }//GEN-LAST:event_btPerfilActionPerformed

  private void btAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdminActionPerformed
    PopAdmin.show(btAdmin, btAdmin.getX(), btAdmin.getY());
  }//GEN-LAST:event_btAdminActionPerformed

  private void btCriadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCriadorActionPerformed
    FormManutProvas frmProvas = new FormManutProvas();
    frmProvas.setVisible(true);
  }//GEN-LAST:event_btCriadorActionPerformed

  private void MIManutPermissoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MIManutPermissoActionPerformed
    FormManutPermissao frmPermisso = new FormManutPermissao();
    frmPermisso.setVisible(true);
  }//GEN-LAST:event_MIManutPermissoActionPerformed

  private void MIManutPerguntasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MIManutPerguntasActionPerformed
    FormManutPerguntas frm = new FormManutPerguntas();
    frm.setVisible(true);
  }//GEN-LAST:event_MIManutPerguntasActionPerformed

  private void btSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSobreActionPerformed
    FormSobre frmSobre = new FormSobre();
    frmSobre.setVisible(true);
  }//GEN-LAST:event_btSobreActionPerformed

  private void btAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtualizarActionPerformed
    AtualizaTabela();
  }//GEN-LAST:event_btAtualizarActionPerformed

  private void btRankingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRankingActionPerformed
    FormRanking frmRanking = new FormRanking();
    frmRanking.setModal(true);
    frmRanking.setVisible(true);
  }//GEN-LAST:event_btRankingActionPerformed

  private void btJogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btJogarActionPerformed
    if(tbProvas.getSelectedRow() == -1)
      return;
    
    FormJogo frmJogo = new FormJogo(GProvas.getProva(tbProvas.getSelectedRow()));
    frmJogo.setModal(true);
    frmJogo.setVisible(true);
    AtualizaTabela();
  }//GEN-LAST:event_btJogarActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JMenuItem MIManutPerguntas;
  private javax.swing.JMenuItem MIManutPermisso;
  private javax.swing.JPopupMenu PopAdmin;
  private javax.swing.JButton btAdmin;
  private javax.swing.JButton btAtualizar;
  private javax.swing.JButton btCriador;
  private javax.swing.JButton btJogar;
  private javax.swing.JButton btPerfil;
  private javax.swing.JButton btRanking;
  private javax.swing.JButton btSobre;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTable tbProvas;
  // End of variables declaration//GEN-END:variables

  private void AtualizaTabela(){
    
    ArrayList<Prova> listaProvas = QuizIFCliente.ccont.getProvas(0);
    
    if(listaProvas != null){
      
      GProvas = new ProvasTableModel(listaProvas, true);
      tbProvas.setModel(GProvas);
    }
  }
}
