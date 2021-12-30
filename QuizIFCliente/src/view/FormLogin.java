
//Mateus Roberto Algayer - 29/12/2021 :: Criação

package view;

import ModelDominio.Usuario;
import controller.InfoApp;
import util.Metodos;
import static util.Metodos.Consistencia;

public class FormLogin extends javax.swing.JFrame{

  public FormLogin() {
    initComponents();
    Metodos.GeraConsistenciaCampos(this.rootPane);
    lbAviso.setVisible(false);
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    lbTrocaSenha = new javax.swing.JLabel();
    btLogar = new javax.swing.JButton();
    btCadastro = new javax.swing.JButton();
    tfUsu = new javax.swing.JTextField();
    pfSenha = new javax.swing.JPasswordField();
    lbAviso = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Login");
    setName("Login"); // NOI18N
    setResizable(false);

    jLabel1.setText("Usuário:");

    jLabel2.setText("Senha:");

    lbTrocaSenha.setText("Esqueci minha senha!");
    lbTrocaSenha.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        lbTrocaSenhaMouseClicked(evt);
      }
    });

    btLogar.setText("Logar");
    btLogar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btLogarActionPerformed(evt);
      }
    });

    btCadastro.setText("Cadastro");
    btCadastro.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btCadastroActionPerformed(evt);
      }
    });

    tfUsu.setName("Usuário"); // NOI18N

    pfSenha.setName("Senha"); // NOI18N

    lbAviso.setForeground(new java.awt.Color(255, 51, 51));
    lbAviso.setText("Usuário ou senha Incorretos!");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(22, 22, 22)
        .addComponent(jLabel1)
        .addGap(12, 12, 12)
        .addComponent(tfUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
      .addGroup(layout.createSequentialGroup()
        .addGap(29, 29, 29)
        .addComponent(jLabel2)
        .addGap(12, 12, 12)
        .addComponent(pfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(12, 12, 12)
        .addComponent(lbAviso, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
      .addGroup(layout.createSequentialGroup()
        .addGap(112, 112, 112)
        .addComponent(lbTrocaSenha))
      .addGroup(layout.createSequentialGroup()
        .addGap(43, 43, 43)
        .addComponent(btCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(57, 57, 57)
        .addComponent(btLogar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(16, 16, 16)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGap(3, 3, 3)
            .addComponent(jLabel1))
          .addComponent(tfUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(12, 12, 12)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGap(16, 16, 16)
            .addComponent(jLabel2))
          .addGroup(layout.createSequentialGroup()
            .addGap(13, 13, 13)
            .addComponent(pfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addComponent(lbAviso, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(6, 6, 6)
        .addComponent(lbTrocaSenha)
        .addGap(43, 43, 43)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(btCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(btLogar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
    );

    pack();
    setLocationRelativeTo(null);
  }// </editor-fold>//GEN-END:initComponents

  private void lbTrocaSenhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbTrocaSenhaMouseClicked
    //Abrir o form de troca de senha
  }//GEN-LAST:event_lbTrocaSenhaMouseClicked

  private void btCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastroActionPerformed
    //abrir o form de cadastro
  }//GEN-LAST:event_btCadastroActionPerformed

  private void btLogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLogarActionPerformed
    if (!Consistencia(true, tfUsu, pfSenha)) return;

    Usuario wLogin = new Usuario(tfUsu.getText(), String.valueOf(pfSenha.getPassword()));
    
    Usuario wUsu = QuizIFCliente.ccont.Login(wLogin);
    
    if(wUsu != null){
      InfoApp.GUsuLogado = wUsu;
      
      FormPrincipal fp = new FormPrincipal();
      fp.setVisible(true);
      
      dispose();
    } else {
      lbAviso.setVisible(true);
    }
  }//GEN-LAST:event_btLogarActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btCadastro;
  private javax.swing.JButton btLogar;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel lbAviso;
  private javax.swing.JLabel lbTrocaSenha;
  private javax.swing.JPasswordField pfSenha;
  private javax.swing.JTextField tfUsu;
  // End of variables declaration//GEN-END:variables
}
