
//Mateus Roberto Algayer - 04/01/2022 :: Implementado botão de Cadastro
//Staub - 30/12/2021 :: Add sal wLogin
//Mateus Roberto Algayer - 29/12/2021 :: Criação

package view;

import ModelDominio.Usuario;
import controller.InfoApp;
import util.Metodos;
import static util.Metodos.consistencia;
import static util.Metodos.pedaco;

public class FormLogin extends javax.swing.JFrame{

  public FormLogin() {
    initComponents();
    
    Metodos.configuraForms(this);
    lbAviso.setVisible(false);
    
    String usu = Metodos.leConf("lembraUsu");
    
    if(pedaco(usu, "Ɵ", 1).equals("1")){
      chbLembraUsu.setSelected(true);
      tfUsu.setText(pedaco(usu,"Ɵ",2));
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
    btRedefSenha = new javax.swing.JButton();
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

    jLabel1.setText("Email:");

    jLabel2.setText("Senha:");

    btLogar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icoLogin32.png"))); // NOI18N
    btLogar.setText("Logar");
    btLogar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btLogarActionPerformed(evt);
      }
    });

    btCadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icoCadastro32.png"))); // NOI18N
    btCadastro.setText("Cadastro");
    btCadastro.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btCadastroActionPerformed(evt);
      }
    });

    tfUsu.setToolTipText("Email");
    tfUsu.setName("TABUSU.EMAIL"); // NOI18N

    pfSenha.setToolTipText("Senha");
    pfSenha.setName(""); // NOI18N

    lbAviso.setForeground(new java.awt.Color(255, 51, 51));
    lbAviso.setText("Usuário ou senha Incorretos!");

    btRedefSenha.setText("Esqueci minha senha");
    btRedefSenha.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btRedefSenhaActionPerformed(evt);
      }
    });

    chbLembraUsu.setText("Lembrar usuário");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGap(78, 78, 78)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(chbLembraUsu)
              .addComponent(btRedefSenha)))
          .addGroup(layout.createSequentialGroup()
            .addGap(22, 22, 22)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addGroup(layout.createSequentialGroup()
                .addComponent(btCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(btLogar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(jLabel2)
                  .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(layout.createSequentialGroup()
                    .addComponent(pfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(lbAviso, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                  .addComponent(tfUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        .addContainerGap(22, Short.MAX_VALUE))
    );

    layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btCadastro, btLogar});

    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(16, 16, 16)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel1)
          .addComponent(tfUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(21, 21, 21)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel2)
          .addComponent(pfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(lbAviso, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(1, 1, 1)
        .addComponent(btRedefSenha)
        .addGap(5, 5, 5)
        .addComponent(chbLembraUsu)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(btLogar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(btCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
        .addGap(20, 20, 20))
    );

    pack();
    setLocationRelativeTo(null);
  }// </editor-fold>//GEN-END:initComponents

  private void btCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastroActionPerformed
      FormCadastro frmCad = new FormCadastro();
      frmCad.setModal(true);
      frmCad.setVisible(true);
      
      if(!InfoApp.getGEmailUsu().equals(""))
        tfUsu.setText(InfoApp.getGEmailUsu());
  }//GEN-LAST:event_btCadastroActionPerformed

  private void btLogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLogarActionPerformed
    if (!consistencia(true, tfUsu, pfSenha)) return;    
   
    Usuario wLogin = new Usuario(tfUsu.getText(), String.valueOf(pfSenha.getPassword()));
   
    Usuario wUsu = QuizIFCliente.ccont.login(wLogin);
   
    if(wUsu != null){
      InfoApp.setGUsuLogado(wUsu);
      
      if(chbLembraUsu.isSelected()){
      Metodos.criaConf("lembraUsu","1Ɵ"+tfUsu.getText());
      } else {
        Metodos.criaConf("lembraUsu","0");
      }
      
      FormPrincipal fp = new FormPrincipal();
      fp.setVisible(true);

      dispose();
   } else {
      lbAviso.setVisible(true);
   }
  }//GEN-LAST:event_btLogarActionPerformed

  private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    if(chbLembraUsu.isSelected()){
      Metodos.criaConf("lembraUsu","1Ɵ"+tfUsu.getText());
    } else {
      Metodos.criaConf("lembraUsu","0");
    }
  }//GEN-LAST:event_formWindowClosing

  private void btRedefSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRedefSenhaActionPerformed
    if (!consistencia(true, tfUsu)) return;
    
    Metodos.gravaLog("UPD", 0, "Redefinição de senha - INI");
    
    String res = QuizIFCliente.ccont.enviaRedefSenha(tfUsu.getText());
    
    if(Metodos.processaMsgServidor(this.getTitle(), res, "", "Erro ao redefinir a senha!")){
      res = QuizIFCliente.ccont.AltSenhaPosRedef(tfUsu.getText());
      
      Metodos.processaMsgServidor(this.getTitle(), res, "Senha redefinida com sucesso!", "Erro ao redefinir a senha!");
    } 
    
    Metodos.gravaLog("UPD", 0, "Redefinição de senha - FIM");
  }//GEN-LAST:event_btRedefSenhaActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btCadastro;
  private javax.swing.JButton btLogar;
  private javax.swing.JButton btRedefSenha;
  private javax.swing.JCheckBox chbLembraUsu;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel lbAviso;
  private javax.swing.JPasswordField pfSenha;
  private javax.swing.JTextField tfUsu;
  // End of variables declaration//GEN-END:variables
}
