
//Mateus Roberto Algayer - 03/01/2022 :: Criação

package view;

import controller.InfoApp;

public class FormConfirmaCodigoEmail extends javax.swing.JDialog {

  public FormConfirmaCodigoEmail(boolean Rep) {
    initComponents();
    
    lbAviso.setVisible(Rep);
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    tfCodEmail = new javax.swing.JTextField();
    jLabel1 = new javax.swing.JLabel();
    btConfirmaCodEmail = new javax.swing.JButton();
    lbAviso = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Verificação por e-mail");
    setModal(true);
    setName("Verificação por e-mail"); // NOI18N
    setResizable(false);
    setType(java.awt.Window.Type.POPUP);
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosed(java.awt.event.WindowEvent evt) {
        formWindowClosed(evt);
      }
    });

    tfCodEmail.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        tfCodEmailActionPerformed(evt);
      }
    });

    jLabel1.setText("Informe o código recebido no email");

    btConfirmaCodEmail.setText("Confirmar");
    btConfirmaCodEmail.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btConfirmaCodEmailActionPerformed(evt);
      }
    });

    lbAviso.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    lbAviso.setForeground(new java.awt.Color(255, 51, 51));
    lbAviso.setText("Código inválido ou vazio!");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGap(98, 98, 98)
            .addComponent(jLabel1))
          .addGroup(layout.createSequentialGroup()
            .addGap(148, 148, 148)
            .addComponent(btConfirmaCodEmail))
          .addGroup(layout.createSequentialGroup()
            .addGap(112, 112, 112)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(lbAviso)
              .addComponent(tfCodEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))
        .addContainerGap(118, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(36, 36, 36)
        .addComponent(jLabel1)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(lbAviso)
        .addGap(17, 17, 17)
        .addComponent(tfCodEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
        .addComponent(btConfirmaCodEmail)
        .addGap(53, 53, 53))
    );

    pack();
    setLocationRelativeTo(null);
  }// </editor-fold>//GEN-END:initComponents

    private void tfCodEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCodEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCodEmailActionPerformed

    private void btConfirmaCodEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConfirmaCodEmailActionPerformed
        String codEmail;
        codEmail = tfCodEmail.getText(); 
        InfoApp.setGCodConfirmacao(codEmail);
        dispose();
    }//GEN-LAST:event_btConfirmaCodEmailActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        InfoApp.setGCodConfirmacao("Fechou");
    }//GEN-LAST:event_formWindowClosed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btConfirmaCodEmail;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel lbAviso;
  private javax.swing.JTextField tfCodEmail;
  // End of variables declaration//GEN-END:variables
}
