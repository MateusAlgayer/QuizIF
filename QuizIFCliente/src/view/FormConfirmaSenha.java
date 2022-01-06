
    //Staub - 30/12/2021 :: Criação da Tela

package view;

import java.awt.Color;
import util.Metodos;


public class FormConfirmaSenha extends javax.swing.JFrame {

    
    public FormConfirmaSenha() {
        initComponents();
    }

    public boolean consistSenha() {
        String senha = String.valueOf(pfSenha.getPassword());
        
        String confirmaSenha = String.valueOf(pfConfirmaSenha.getPassword());
        
        boolean validSenha = false;
        
        if(senha.equals("")){
            lbCaracteres.setForeground(Color.red);
            lbDigito.setForeground(Color.red);
            lbMaiuscula.setForeground(Color.red);
            lbMinuscula.setForeground(Color.red);
            lbSenhas.setForeground(Color.red);
            return false;
        }
        
        if(senha.equals(confirmaSenha)){
            lbSenhas.setForeground(Color.green);
            validSenha = true;
        } else{
            lbSenhas.setForeground(Color.red);
            validSenha=false;
        }
        
        if(senha.chars().anyMatch(Character::isLowerCase)){
            lbMinuscula.setForeground(Color.green);
            validSenha = true;
        } else{
            lbMinuscula.setForeground(Color.red);
            validSenha=false;
        }
        
        if(senha.chars().anyMatch(Character::isUpperCase)){
            lbMaiuscula.setForeground(Color.green);
            validSenha = true;
        } else{
            lbMaiuscula.setForeground(Color.red);
            validSenha=false;
        }
        
        if(senha.chars().anyMatch(Character::isDigit)){
            lbDigito.setForeground(Color.green);
            validSenha = true;
        } else{
            lbDigito.setForeground(Color.red);
            validSenha=false;
        }
        
        if(!senha.matches("[^A-Za-z0-9 ]")){
            lbCaracteres.setForeground(Color.green);
            validSenha = true;
        } else{
            lbCaracteres.setForeground(Color.red);
            validSenha=false;
        }
        
        
        return validSenha;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pfSenha = new javax.swing.JPasswordField();
        pfConfirmaSenha = new javax.swing.JPasswordField();
        bSalvar = new javax.swing.JButton();
        lbSenhas = new javax.swing.JLabel();
        lbMaiuscula = new javax.swing.JLabel();
        lbMinuscula = new javax.swing.JLabel();
        lbCaracteres = new javax.swing.JLabel();
        lbDigito = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Senha:");

        jLabel2.setText("Confirma Senha:");

        pfSenha.setName("Senha"); // NOI18N
        pfSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pfSenhaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pfSenhaKeyTyped(evt);
            }
        });

        pfConfirmaSenha.setName("ConfirmaSenha"); // NOI18N
        pfConfirmaSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pfConfirmaSenhaKeyReleased(evt);
            }
        });

        bSalvar.setText("Salvar");
        bSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalvarActionPerformed(evt);
            }
        });
        bSalvar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bSalvarKeyPressed(evt);
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
        lbCaracteres.setText("Possuir caracteres especiais");

        lbDigito.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbDigito.setForeground(new java.awt.Color(255, 0, 0));
        lbDigito.setText("Possuir 1 digito");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("As senhas devem:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pfConfirmaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(106, 106, 106)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDigito)
                            .addComponent(lbCaracteres)
                            .addComponent(lbMinuscula)
                            .addComponent(lbMaiuscula)
                            .addComponent(lbSenhas)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(394, 394, 394)
                        .addComponent(jLabel3)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(pfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbSenhas)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(pfConfirmaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addComponent(bSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lbMaiuscula)
                        .addGap(9, 9, 9)
                        .addComponent(lbMinuscula)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbCaracteres)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbDigito)))
                .addContainerGap(155, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalvarActionPerformed
        if(!Metodos.Consistencia(true, pfSenha, pfConfirmaSenha)) return;
        
        
        
    }//GEN-LAST:event_bSalvarActionPerformed

    private void pfSenhaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pfSenhaKeyTyped

    }//GEN-LAST:event_pfSenhaKeyTyped

    private void bSalvarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bSalvarKeyPressed
        
    }//GEN-LAST:event_bSalvarKeyPressed

    private void pfSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pfSenhaKeyPressed
        consistSenha();
    }//GEN-LAST:event_pfSenhaKeyPressed

    private void pfConfirmaSenhaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pfConfirmaSenhaKeyReleased
        consistSenha();
    }//GEN-LAST:event_pfConfirmaSenhaKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbCaracteres;
    private javax.swing.JLabel lbDigito;
    private javax.swing.JLabel lbMaiuscula;
    private javax.swing.JLabel lbMinuscula;
    private javax.swing.JLabel lbSenhas;
    private javax.swing.JPasswordField pfConfirmaSenha;
    private javax.swing.JPasswordField pfSenha;
    // End of variables declaration//GEN-END:variables
}
