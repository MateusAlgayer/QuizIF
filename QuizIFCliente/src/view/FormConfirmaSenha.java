
    //Staub - 30/12/2021 :: Criação da Tela

package view;

import controller.InfoApp;
import java.awt.Color;
import util.Metodos;
import util.CriptoHash;

public class FormConfirmaSenha extends javax.swing.JDialog {

    String GSal = "";
    
    public FormConfirmaSenha(String sal, boolean trocasenha) {
      GSal = sal;
      initComponents();
      Metodos.configuraForms(this);
      lbAntigaSenha.setVisible(false);
      lbNova.setVisible(false);
      pfAntigaSenha.setVisible(false);
      btConfirmaSenha.setVisible(false);
      
      if(trocasenha){
        lbAntigaSenha.setVisible(true);
        lbNova.setVisible(true);
        pfAntigaSenha.setVisible(true);
        btConfirmaSenha.setVisible(true);
        btSalvar.setVisible(false);
      }
      
      lbSenha.setText(trocasenha ? "senha:" : "Senha:");
    }
    
    public boolean consistSenha() {
        String senha = String.valueOf(pfSenha.getPassword());
        
        String confirmaSenha = String.valueOf(pfConfirmaSenha.getPassword());
        
        boolean validSenha = true;
        
        if(senha.equals("")){
            lbCaracteres.setForeground(Color.red);
            lbDigito.setForeground(Color.red);
            lbMaiuscula.setForeground(Color.red);
            lbMinuscula.setForeground(Color.red);
            lbSenhas.setForeground(Color.red);
            lbTamanho.setForeground(Color.red);
            return false;
        }
        
        if(senha.equals(confirmaSenha)){
            lbSenhas.setForeground(Color.green);
        } else{
            lbSenhas.setForeground(Color.red);
            validSenha = false;
        }
        
        if(senha.chars().anyMatch(Character::isLowerCase)){
            lbMinuscula.setForeground(Color.green);
        } else{
            lbMinuscula.setForeground(Color.red);
            validSenha = false;
        }
        
        if(senha.chars().anyMatch(Character::isUpperCase)){
            lbMaiuscula.setForeground(Color.green);
        } else{
            lbMaiuscula.setForeground(Color.red);
            validSenha = false;
        }
        
        if(senha.chars().anyMatch(Character::isDigit)){
            lbDigito.setForeground(Color.green);
        } else{
            lbDigito.setForeground(Color.red);
            validSenha = false;
        }
        
        if(senha.matches(".*[^A-Za-z0-9 ].*")){
          lbCaracteres.setForeground(Color.green);
        } else{
          lbCaracteres.setForeground(Color.red);
          validSenha = false;
        }
        
        if(senha.length() >= 8 && senha.length() <= 20){
          lbTamanho.setForeground(Color.green);
        } else {
          lbTamanho.setForeground(Color.red);
          validSenha = false;
        }
        
        return validSenha;
    }
    
    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jPanel1 = new javax.swing.JPanel();
    lbSenha = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    pfSenha = new javax.swing.JPasswordField();
    pfConfirmaSenha = new javax.swing.JPasswordField();
    btSalvar = new javax.swing.JButton();
    lbSenhas = new javax.swing.JLabel();
    lbMaiuscula = new javax.swing.JLabel();
    lbMinuscula = new javax.swing.JLabel();
    lbCaracteres = new javax.swing.JLabel();
    lbDigito = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    lbTamanho = new javax.swing.JLabel();
    lbNova = new javax.swing.JLabel();
    btConfirmaSenha = new javax.swing.JButton();
    pfAntigaSenha = new javax.swing.JPasswordField();
    lbAntigaSenha = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Cadastro de senha");
    setName("Cadastro de senha"); // NOI18N
    setResizable(false);
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent evt) {
        formWindowClosing(evt);
      }
    });

    jPanel1.setToolTipText("");

    lbSenha.setText("Senha:");

    jLabel2.setText("Confirma senha:");

    pfSenha.setToolTipText("Senha");
    pfSenha.setName("Senha"); // NOI18N
    pfSenha.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyReleased(java.awt.event.KeyEvent evt) {
        pfSenhaKeyReleased(evt);
      }
    });

    pfConfirmaSenha.setToolTipText("Confirma senha");
    pfConfirmaSenha.setName("ConfirmaSenha"); // NOI18N
    pfConfirmaSenha.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyReleased(java.awt.event.KeyEvent evt) {
        pfConfirmaSenhaKeyReleased(evt);
      }
    });

    btSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icoSenha32.png"))); // NOI18N
    btSalvar.setText("Cadastrar");
    btSalvar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btSalvarActionPerformed(evt);
      }
    });

    lbSenhas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    lbSenhas.setForeground(new java.awt.Color(255, 0, 0));
    lbSenhas.setText("Ser iguais");

    lbMaiuscula.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    lbMaiuscula.setForeground(new java.awt.Color(255, 0, 0));
    lbMaiuscula.setText("Possuir ao menos uma letra maiúscula");

    lbMinuscula.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    lbMinuscula.setForeground(new java.awt.Color(255, 0, 0));
    lbMinuscula.setText("Possuir ao menos uma letra minúscula");

    lbCaracteres.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    lbCaracteres.setForeground(new java.awt.Color(255, 0, 0));
    lbCaracteres.setText("Possuir ao menos um caracter especial");

    lbDigito.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    lbDigito.setForeground(new java.awt.Color(255, 0, 0));
    lbDigito.setText("Possuir ao menos um dígito");

    jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    jLabel3.setText("As senhas devem:");

    lbTamanho.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    lbTamanho.setForeground(new java.awt.Color(255, 0, 0));
    lbTamanho.setText("Possuir no mínimo 8 e no máximo 20 caracteres");

    lbNova.setText("Nova");

    btConfirmaSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icoSenha32.png"))); // NOI18N
    btConfirmaSenha.setText("Confirma Senha");
    btConfirmaSenha.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btConfirmaSenhaActionPerformed(evt);
      }
    });

    pfAntigaSenha.setToolTipText("Antiga senha");

    lbAntigaSenha.setText("Antiga senha:");

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(40, 40, 40)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(pfConfirmaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                  .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(lbNova)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lbSenha))
                  .addComponent(lbAntigaSenha))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                  .addComponent(pfAntigaSenha, javax.swing.GroupLayout.Alignment.TRAILING)
                  .addComponent(pfSenha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)))))
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(50, 50, 50)
            .addComponent(btConfirmaSenha)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btSalvar)))
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(45, 45, 45)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(lbDigito)
              .addComponent(lbCaracteres)
              .addComponent(lbMinuscula)
              .addComponent(lbMaiuscula)
              .addComponent(lbSenhas)
              .addComponent(lbTamanho)))
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(29, 29, 29)
            .addComponent(jLabel3)))
        .addContainerGap(15, Short.MAX_VALUE))
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(12, 12, 12)
            .addComponent(jLabel3)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(lbSenhas))
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(28, 28, 28)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(pfAntigaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(lbAntigaSenha))))
        .addGap(3, 3, 3)
        .addComponent(lbMaiuscula)
        .addGap(9, 9, 9)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(lbMinuscula)
          .addComponent(pfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(lbSenha)
          .addComponent(lbNova))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(lbCaracteres)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(lbDigito)
          .addComponent(pfConfirmaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel2))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(lbTamanho)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(btSalvar)
          .addComponent(btConfirmaSenha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap())
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(0, 0, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(0, 38, Short.MAX_VALUE))
    );

    pack();
    setLocationRelativeTo(null);
  }// </editor-fold>//GEN-END:initComponents

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        if(!Metodos.consistencia(true, pfSenha, pfConfirmaSenha)) return;
        
        if(consistSenha()){
          String senhaCripto = CriptoHash.Cripto(String.valueOf(pfSenha.getPassword()), GSal, 0);
          InfoApp.setGSenhaCripto(senhaCripto);
          dispose();
        } else {
          Metodos.aviso(this.getTitle(), "Reveja a política de senha!");
        }
        
    }//GEN-LAST:event_btSalvarActionPerformed

  private void pfSenhaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pfSenhaKeyReleased
    consistSenha();
  }//GEN-LAST:event_pfSenhaKeyReleased

  private void pfConfirmaSenhaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pfConfirmaSenhaKeyReleased
    consistSenha();
  }//GEN-LAST:event_pfConfirmaSenhaKeyReleased

  private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    InfoApp.setGSenhaCripto("Fechou");
  }//GEN-LAST:event_formWindowClosing

    private void btConfirmaSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConfirmaSenhaActionPerformed
        if(!Metodos.consistencia(true, pfAntigaSenha, pfSenha, pfConfirmaSenha)) return;
        
        String antigaSenha = CriptoHash.Cripto(String.valueOf(pfAntigaSenha.getPassword()), InfoApp.getGUsuLogado().getSal(), 0);
        
        if(antigaSenha.equals(InfoApp.getGUsuLogado().getSenha())){
            if(consistSenha()){
                String senhaCripto = CriptoHash.Cripto(String.valueOf(pfSenha.getPassword()), GSal, 0);
                InfoApp.setGSenhaCripto(senhaCripto);
                dispose();
            } else {
                Metodos.aviso(this.getTitle(), "Reveja a política de senha!");
            }
        } else {
            Metodos.aviso(this.getTitle(), "Senha antiga incorreta!");
        }
    }//GEN-LAST:event_btConfirmaSenhaActionPerformed


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btConfirmaSenha;
  private javax.swing.JButton btSalvar;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JLabel lbAntigaSenha;
  private javax.swing.JLabel lbCaracteres;
  private javax.swing.JLabel lbDigito;
  private javax.swing.JLabel lbMaiuscula;
  private javax.swing.JLabel lbMinuscula;
  private javax.swing.JLabel lbNova;
  private javax.swing.JLabel lbSenha;
  private javax.swing.JLabel lbSenhas;
  private javax.swing.JLabel lbTamanho;
  private javax.swing.JPasswordField pfAntigaSenha;
  private javax.swing.JPasswordField pfConfirmaSenha;
  private javax.swing.JPasswordField pfSenha;
  // End of variables declaration//GEN-END:variables
}
