
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
      public void windowClosing(java.awt.event.WindowEvent evt) {
        formWindowClosing(evt);
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
        .addGap(24, 24, 24)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel1)
          .addGroup(layout.createSequentialGroup()
            .addGap(14, 14, 14)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(lbAviso)
              .addComponent(tfCodEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGap(48, 48, 48)
            .addComponent(btConfirmaCodEmail)
            .addGap(58, 58, 58)))
        .addContainerGap(30, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(35, 35, 35)
        .addComponent(jLabel1)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(lbAviso)
        .addGap(17, 17, 17)
        .addComponent(tfCodEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
        .addComponent(btConfirmaCodEmail)
        .addGap(30, 30, 30))
    );

    pack();
    setLocationRelativeTo(null);
  }// </editor-fold>//GEN-END:initComponents

    private void btConfirmaCodEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConfirmaCodEmailActionPerformed
      String codEmail;
      codEmail = tfCodEmail.getText(); 
      InfoApp.setGCodConfirmacao(codEmail);
      dispose();
    }//GEN-LAST:event_btConfirmaCodEmailActionPerformed

  private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    InfoApp.setGCodConfirmacao("Fechou");
  }//GEN-LAST:event_formWindowClosing

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btConfirmaCodEmail;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel lbAviso;
  private javax.swing.JTextField tfCodEmail;
  // End of variables declaration//GEN-END:variables
}
