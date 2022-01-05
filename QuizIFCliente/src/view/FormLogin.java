
//Mateus Roberto Algayer - 04/01/2022 :: Implementado botão de Cadastro
//Staub - 30/12/2021 :: Add sal wLogin
//Mateus Roberto Algayer - 29/12/2021 :: Criação

package view;

import ModelDominio.Usuario;
import controller.InfoApp;
import util.Metodos;
import static util.Metodos.Consistencia;
import static util.Metodos.Pedaco;

public class FormLogin extends javax.swing.JFrame{

  public FormLogin() {
    initComponents();
    Metodos.GeraConsistenciaCampos(this.rootPane);
    lbAviso.setVisible(false);
    
    String usu = Metodos.LeConf("lembraUsu");
    
    if(Pedaco(usu, "-", 1).equals("1")){
      chbLembraUsu.setSelected(true);
      tfUsu.setText(Pedaco(usu,"-",2));
    } else {
      chbLembraUsu.setSelected(false);
    }
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    btLogar = new javax.swing.JButton();
    btCadastro = new javax.swing.JButton();
    tfUsu = new javax.swing.JTextField();
    pfSenha = new javax.swing.JPasswordField();
    lbAviso = new javax.swing.JLabel();
    jButton1 = new javax.swing.JButton();
    chbLembraUsu = new javax.swing.JCheckBox();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Login");
    setName("Login"); // NOI18N
    setResizable(false);
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent evt) {
        formWindowClosing(evt);
      }
    });

    jLabel1.setText("Usuário:");

    jLabel2.setText("Senha:");

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

    jButton1.setText("Esqueci minha senha");

    chbLembraUsu.setText("Lembrar usuário");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGap(22, 22, 22)
            .addComponent(jLabel1)
            .addGap(12, 12, 12)
            .addComponent(tfUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(layout.createSequentialGroup()
            .addGap(29, 29, 29)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(12, 12, 12)
                .addComponent(pfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addComponent(jButton1)
              .addComponent(chbLembraUsu))
            .addGap(12, 12, 12)
            .addComponent(lbAviso, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      .addGroup(layout.createSequentialGroup()
        .addGap(43, 43, 43)
        .addComponent(btCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(btLogar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(31, 31, 31))
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
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jButton1)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(chbLembraUsu)
        .addGap(11, 11, 11)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(btCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(btLogar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(35, Short.MAX_VALUE))
    );

    pack();
    setLocationRelativeTo(null);
  }// </editor-fold>//GEN-END:initComponents

  private void btCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastroActionPerformed
      FormCadastro frmCad = new FormCadastro();
      frmCad.setVisible(true);
  }//GEN-LAST:event_btCadastroActionPerformed

  private void btLogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLogarActionPerformed
    if (!Consistencia(true, tfUsu, pfSenha)) return;    
    
    Usuario wLogin = new Usuario(tfUsu.getText(), String.valueOf(pfSenha.getPassword()), "");
    
    Usuario wUsu = QuizIFCliente.ccont.Login(wLogin);
    
    if(wUsu != null){
      InfoApp.setGUsuLogado(wUsu);
      
      FormPrincipal fp = new FormPrincipal();
      fp.setVisible(true);
      
      dispose();
    } else {
      lbAviso.setVisible(true);
    }
  }//GEN-LAST:event_btLogarActionPerformed

  private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    if(chbLembraUsu.isSelected()){
      Metodos.CriaConf("lembraUsu","1-"+tfUsu.getText());
    } else {
      Metodos.CriaConf("lembraUsu","0");
    }
  }//GEN-LAST:event_formWindowClosing

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btCadastro;
  private javax.swing.JButton btLogar;
  private javax.swing.JCheckBox chbLembraUsu;
  private javax.swing.JButton jButton1;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel lbAviso;
  private javax.swing.JPasswordField pfSenha;
  private javax.swing.JTextField tfUsu;
  // End of variables declaration//GEN-END:variables
}
