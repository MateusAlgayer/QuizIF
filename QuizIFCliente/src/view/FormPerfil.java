
//Mateus Roberto Algayer - 03/01/2022 :: Criação

package view;

import ModelDominio.Prova;
import ModelDominio.Usuario;
import controller.InfoApp;
import java.util.ArrayList;
import util.Metodos;
import view.tablemodel.ProvasTableModel;
import static util.Metodos.gravaLog;

public class FormPerfil extends javax.swing.JDialog {

  public FormPerfil() {
    initComponents();
    
    tfNomeUsu.setText(InfoApp.getGUsuLogado().getNomeUsuario());
    tfApelidoUsu.setText(InfoApp.getGUsuLogado().getApelido());
    tfEmailUsu.setText(InfoApp.getGUsuLogado().getEmail());
    
    atualizaTabela();
  }
  
    

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    btVoltar = new javax.swing.JButton();
    jPanel1 = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();
    tfNomeUsu = new javax.swing.JTextField();
    jLabel2 = new javax.swing.JLabel();
    tfApelidoUsu = new javax.swing.JTextField();
    jLabel3 = new javax.swing.JLabel();
    tfEmailUsu = new javax.swing.JTextField();
    btAltSenha = new javax.swing.JButton();
    btExcluirConta = new javax.swing.JButton();
    jScrollPane1 = new javax.swing.JScrollPane();
    tbHist = new javax.swing.JTable();
    jLabel4 = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Perfil");
    setMinimumSize(new java.awt.Dimension(664, 330));
    setName("Perfil"); // NOI18N
    setType(java.awt.Window.Type.UTILITY);

    btVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icoFechar32.png"))); // NOI18N
    btVoltar.setText("Voltar");
    btVoltar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btVoltarActionPerformed(evt);
      }
    });

    jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

    jLabel1.setText("Nome:");

    tfNomeUsu.setEditable(false);

    jLabel2.setText("Apelido:");

    tfApelidoUsu.setEditable(false);

    jLabel3.setText("Email:");

    tfEmailUsu.setEditable(false);

    btAltSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icoSenha32.png"))); // NOI18N
    btAltSenha.setText("Alterar Senha");
    btAltSenha.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btAltSenhaActionPerformed(evt);
      }
    });

    btExcluirConta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icoExcluirUsu32.png"))); // NOI18N
    btExcluirConta.setText("Excluir Conta");
    btExcluirConta.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btExcluirContaActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(20, 20, 20)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(jLabel3)
              .addComponent(jLabel1)
              .addComponent(jLabel2))
            .addGap(18, 18, 18)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
              .addComponent(tfNomeUsu)
              .addComponent(tfApelidoUsu, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
              .addComponent(tfEmailUsu))
            .addGap(0, 0, Short.MAX_VALUE))
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(btAltSenha)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btExcluirConta)))
        .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addGap(24, 24, 24)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(jLabel1)
            .addGap(21, 21, 21)
            .addComponent(jLabel2)
            .addGap(31, 31, 31)
            .addComponent(jLabel3))
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(tfNomeUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(tfApelidoUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(22, 22, 22)
            .addComponent(tfEmailUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addGap(18, 18, 18)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(btExcluirConta, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(btAltSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(20, Short.MAX_VALUE))
    );

    tbHist.setModel(new javax.swing.table.DefaultTableModel(
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
    jScrollPane1.setViewportView(tbHist);

    jLabel4.setText("Histórico:");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(15, 15, 15)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGap(28, 28, 28))
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel4)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 343, Short.MAX_VALUE)))
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(16, 16, 16))
          .addComponent(btVoltar, javax.swing.GroupLayout.Alignment.TRAILING)))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addGroup(layout.createSequentialGroup()
            .addGap(11, 11, 11)
            .addComponent(jLabel4)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
          .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
            .addComponent(btVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap())
    );

    pack();
    setLocationRelativeTo(null);
  }// </editor-fold>//GEN-END:initComponents

    private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
        dispose();
    }//GEN-LAST:event_btVoltarActionPerformed

    private void btExcluirContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirContaActionPerformed
        gravaLog("DelUsu", 0, "Excluir Usuário - INI");
        
        if(!Metodos.msgConfirma("Tem certeza que deseja excluir o usuário? ")){
            return;
        }
        String res = QuizIFCliente.ccont.enviaDelUsu(tfEmailUsu.getText());
        
        if(Metodos.processaMsgServidor(this.getTitle(), res, "", "Erro ao validar o código via e-mail")){
          Usuario usu = new Usuario(InfoApp.getGUsuLogado().getCodUsuario());
          
          res = QuizIFCliente.ccont.deletaUsu(usu);
          
          if(Metodos.processaMsgServidor(this.getTitle(), res, "Usuário deletado!! \n A aplicação será encerrada!", "Erro ao deletar o usuário")){
            gravaLog("DelUsu", 0, "Excluir Usuário - FIM");
            System.exit(0);
          }
        }
    }//GEN-LAST:event_btExcluirContaActionPerformed

    private void btAltSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAltSenhaActionPerformed
       gravaLog("UPD", 0, "Alterar Usuario - INI");
       String res = QuizIFCliente.ccont.alteraSenhaUsu();
       
       if(Metodos.processaMsgServidor(this.getTitle(), res, "Sucesso ao alterar a senha!", "Erro ao alterar a senha!")){
          InfoApp.getGUsuLogado().setSenha(InfoApp.getGSenhaCripto());
       }
       gravaLog("UPD", 0, "Alterar Usuario - FIM");
    }//GEN-LAST:event_btAltSenhaActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btAltSenha;
  private javax.swing.JButton btExcluirConta;
  private javax.swing.JButton btVoltar;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTable tbHist;
  private javax.swing.JTextField tfApelidoUsu;
  private javax.swing.JTextField tfEmailUsu;
  private javax.swing.JTextField tfNomeUsu;
  // End of variables declaration//GEN-END:variables

  private void atualizaTabela() {
    ArrayList<Prova> listaHistProva = QuizIFCliente.ccont.getProvasHist();
    
    if(listaHistProva != null){
      ProvasTableModel hist = new ProvasTableModel(listaHistProva, true);
      tbHist.setModel(hist);
      hist.AtualizaColunas(tbHist);
    }
  }
}
