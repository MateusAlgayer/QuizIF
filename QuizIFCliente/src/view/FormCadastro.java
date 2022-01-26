
//Mateus Roberto Algayer - 06/01/2022 :: Ajustes

package view;

import controller.InfoApp;
import util.Metodos;
import static util.Metodos.Pedaco;
import static util.Metodos.GravaLog;
import util.CriptoHash;
import ModelDominio.Comum;

public class FormCadastro extends javax.swing.JDialog {

    public FormCadastro() {
        initComponents();
        Metodos.GeraConsistenciaCampos(this.rootPane);
    }

    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    btCadastro = new javax.swing.JButton();
    tfEmail = new javax.swing.JTextField();
    tfNome = new javax.swing.JTextField();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    tfApelido = new javax.swing.JTextField();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Cadastro");
    setName("Cadastro"); // NOI18N
    setResizable(false);

    jLabel1.setText("Email:");

    btCadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icoCadastro32.png"))); // NOI18N
    btCadastro.setText("Cadastrar");
    btCadastro.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btCadastroActionPerformed(evt);
      }
    });

    jLabel2.setText("Nome:");

    jLabel3.setText("Apelido:");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(23, 23, 23)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(btCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
              .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
              .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(tfNome, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addComponent(tfApelido))
              .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))))
        .addContainerGap(20, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(42, 42, 42)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel1)
          .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(15, 15, 15)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel2)
          .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(21, 21, 21)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel3)
          .addComponent(tfApelido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
        .addComponent(btCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(20, 20, 20))
    );

    pack();
    setLocationRelativeTo(null);
  }// </editor-fold>//GEN-END:initComponents

    private void btCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastroActionPerformed
      String recNome = Pedaco(tfEmail.getText(),"@", 1);
      String dominio = Pedaco(tfEmail.getText(),"@", 2);
       
      //validação de email - INI
      
      //receptor é vazio
      if(recNome.equals("")){
        Metodos.Aviso(this.getTitle(), "Email inválido!");
        return;
      }
      
      //dominio é vazio
      if(dominio.equals("")){
        Metodos.Aviso(this.getTitle(), "Email inválido!");
        return;
      }
      
      int i = 0;
      int idx = 0;
      
      //conta o número de vezes que o . aparece no dominio
      while(idx != -1){
        idx = dominio.indexOf(".", idx+1);
        
        if(idx != -1)
          i++;
      }
      
      //se i é 0 então não tem subdominio
      if(i == 0){
        Metodos.Aviso(this.getTitle(), "Email inválido!");
        return;
      }
      
      //ve se algo entre os pontos está vazio, se estiver o email é inválido
      for (int x = 1; x <= i+1; x++) {
        if(Pedaco(dominio,".", x).equals("")){
          Metodos.Aviso(this.getTitle(), "Email inválido!");
          return;
        }
      }
      
      //validação de email - FIM
      
      if(!Metodos.Consistencia(true, tfNome, tfApelido)){
        return;
      }
       
      GravaLog("CAD", 0, "Cadastro - INI");
      
      if(!QuizIFCliente.ccont.EnviaCodigoEmail(tfEmail.getText())){
         return;
      } 
      
      boolean continua = true;
      String sal = CriptoHash.getSalt(0);
      int cont = 0;
      
      InfoApp.setGSenhaCripto("");
      
      while(continua){
        GravaLog("SEN", 0, "Codigo email rep:"+(cont++));
        
        FormConfirmaSenha frm = new FormConfirmaSenha(sal, false);
        frm.setModal(true);
        frm.setVisible(true);

        if(InfoApp.getGSenhaCripto().equals("Fechou")) {
          if(Metodos.msgConfirma("Deseja interromper o processo de criação de senha? \n O Cadastro será perdido.")){
            return;
          } 
        } else if(!InfoApp.getGSenhaCripto().equals("")){
          //inclusão
          Comum novoUsu = new Comum(tfNome.getText(), tfApelido.getText(), tfEmail.getText(), InfoApp.getGSenhaCripto(), sal);
          
          if(QuizIFCliente.ccont.CadastraUsu(novoUsu)){
            Metodos.Sucesso(this.getTitle(), "Usuário cadastrado com sucesso!!");
            InfoApp.setGEmailUsu(tfEmail.getText());
            dispose();
          } else {
            Metodos.Erro(this.getTitle(), "Ocorreu um erro ao cadastrar o usuário!");
          }
          
          GravaLog("CAD", 0, "Cadastro - FIM");
          return;
        }
      }
      
    }//GEN-LAST:event_btCadastroActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btCadastro;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JTextField tfApelido;
  private javax.swing.JTextField tfEmail;
  private javax.swing.JTextField tfNome;
  // End of variables declaration//GEN-END:variables
}
