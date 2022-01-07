
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
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();

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

    jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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

    jLabel2.setText("A mensagem pode demorar um momento para chegar");

    jLabel3.setText("e pode cair na caixa de spam");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGap(82, 82, 82)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(tfCodEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(lbAviso)))))
          .addGroup(layout.createSequentialGroup()
            .addGap(114, 114, 114)
            .addComponent(btConfirmaCodEmail))
          .addGroup(layout.createSequentialGroup()
            .addGap(18, 18, 18)
            .addComponent(jLabel2))
          .addGroup(layout.createSequentialGroup()
            .addGap(52, 52, 52)
            .addComponent(jLabel1)))
        .addContainerGap(23, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(13, 13, 13)
        .addComponent(jLabel1)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jLabel2)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLabel3)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
        .addComponent(lbAviso)
        .addGap(19, 19, 19)
        .addComponent(tfCodEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(33, 33, 33)
        .addComponent(btConfirmaCodEmail)
        .addGap(15, 15, 15))
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
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel lbAviso;
  private javax.swing.JTextField tfCodEmail;
  // End of variables declaration//GEN-END:variables
}
