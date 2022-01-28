
//Mateus Roberto Algayer - 03/01/2022 :: Criação

package view;

import ModelDominio.Area;
import ModelDominio.Pergunta;
import java.util.ArrayList;
import util.Metodos;
import view.tablemodel.PerguntasTableModel;
import view.util.ComboBoxArea;
import static util.Metodos.Pedaco;

public class FormManutPerguntas extends javax.swing.JFrame {

  private ArrayList<Area> GListaCombo;
  private PerguntasTableModel GPerguntas;
  private int contAtt = 0;
  private boolean GModoEdicao;
  
  
  public FormManutPerguntas() {
    initComponents();
    
    int tamanhoAlt;
    //pega o tamanho máximo que cada campo de alternativa pode ter
    tamanhoAlt = (Metodos.getTamanhoCampo("TABPER.ALTERNATIVAS")/4)-1;
    Metodos.putTamanhoCampoPerso("ALTERNATIVA.PERSONALIZADO", tamanhoAlt);
    
    Metodos.GeraConsistenciaCampos(rootPane);    
    
    attComboBoxArea(-1);
    
    AtualizaTabela();
    
    lbEdicao.setVisible(false);
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    btVoltar = new javax.swing.JButton();
    jScrollPane1 = new javax.swing.JScrollPane();
    tbPerguntas = new javax.swing.JTable();
    btAtualizar = new javax.swing.JButton();
    btSalvar = new javax.swing.JButton();
    btExcluir = new javax.swing.JButton();
    cbArea = new javax.swing.JComboBox<>();
    cbDificuldade = new javax.swing.JComboBox<>();
    cbSituacao = new javax.swing.JComboBox<>();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    jScrollPane2 = new javax.swing.JScrollPane();
    tfPergunta = new javax.swing.JTextArea();
    jLabel4 = new javax.swing.JLabel();
    jLabel5 = new javax.swing.JLabel();
    Correta1 = new javax.swing.JCheckBox();
    jLabel6 = new javax.swing.JLabel();
    Correta2 = new javax.swing.JCheckBox();
    jLabel7 = new javax.swing.JLabel();
    Correta3 = new javax.swing.JCheckBox();
    jLabel8 = new javax.swing.JLabel();
    Correta4 = new javax.swing.JCheckBox();
    tfAlt1 = new javax.swing.JTextField();
    tfAlt2 = new javax.swing.JTextField();
    tfAlt3 = new javax.swing.JTextField();
    tfAlt4 = new javax.swing.JTextField();
    jLabel9 = new javax.swing.JLabel();
    tfCodigo = new javax.swing.JTextField();
    btNovo = new javax.swing.JButton();
    lbEdicao = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Manutenção de perguntas");
    setMinimumSize(new java.awt.Dimension(910, 750));
    setName("Manutenção de perguntas"); // NOI18N

    btVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icoFechar32.png"))); // NOI18N
    btVoltar.setText("Voltar");
    btVoltar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btVoltarActionPerformed(evt);
      }
    });

    tbPerguntas.setModel(new javax.swing.table.DefaultTableModel(
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
    tbPerguntas.getTableHeader().setResizingAllowed(false);
    tbPerguntas.getTableHeader().setReorderingAllowed(false);
    tbPerguntas.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        tbPerguntasMouseClicked(evt);
      }
    });
    jScrollPane1.setViewportView(tbPerguntas);

    btAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icoAtualizar32.png"))); // NOI18N
    btAtualizar.setText("Atualizar");
    btAtualizar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btAtualizarActionPerformed(evt);
      }
    });

    btSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icoSalvar32.png"))); // NOI18N
    btSalvar.setText("Salvar");
    btSalvar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btSalvarActionPerformed(evt);
      }
    });

    btExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icoExcluir32.png"))); // NOI18N
    btExcluir.setText("Excluir");
    btExcluir.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btExcluirActionPerformed(evt);
      }
    });

    cbDificuldade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 - Fácil", "2 - Médio", "3 - Difícil" }));
    cbDificuldade.setSelectedIndex(1);

    cbSituacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ativo", "Inativo" }));

    jLabel1.setText("Área:");

    jLabel2.setText("Dificuldade:");

    jLabel3.setText("Situação:");

    tfPergunta.setColumns(20);
    tfPergunta.setLineWrap(true);
    tfPergunta.setRows(5);
    tfPergunta.setToolTipText("Pergunta");
    tfPergunta.setName("TABPER.PERGUNTA"); // NOI18N
    jScrollPane2.setViewportView(tfPergunta);

    jLabel4.setText("Pergunta:");

    jLabel5.setText("Alternativa 1:");

    Correta1.setText("Correta");

    jLabel6.setText("Alternativa 2:");

    Correta2.setText("Correta");

    jLabel7.setText("Alternativa 3:");

    Correta3.setText("Correta");

    jLabel8.setText("Alternativa 4:");

    Correta4.setText("Correta");

    tfAlt1.setToolTipText("Alternativa 1");
    tfAlt1.setName("ALTERNATIVA.PERSONALIZADO"); // NOI18N

    tfAlt2.setToolTipText("Alternativa 2");
    tfAlt2.setName("ALTERNATIVA.PERSONALIZADO"); // NOI18N

    tfAlt3.setToolTipText("Alternativa 3");
    tfAlt3.setName("ALTERNATIVA.PERSONALIZADO"); // NOI18N

    tfAlt4.setToolTipText("Alternativa 4");
    tfAlt4.setName("ALTERNATIVA.PERSONALIZADO"); // NOI18N

    jLabel9.setText("Código:");

    tfCodigo.setEditable(false);

    btNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icoNovo32.png"))); // NOI18N
    btNovo.setText("Novo");
    btNovo.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btNovoActionPerformed(evt);
      }
    });

    lbEdicao.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    lbEdicao.setText("Modo de Edição!");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jScrollPane1)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addComponent(btAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Correta3))
              .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Correta4)))
            .addGap(0, 0, Short.MAX_VALUE))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(tfAlt3, javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(tfAlt4, javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(tfAlt1, javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(tfAlt2, javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lbEdicao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btNovo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btSalvar))
              .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(jLabel4)
                  .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel5)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(Correta1))
                  .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel6)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(Correta2)))
                .addGap(0, 683, Short.MAX_VALUE))
              .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfCodigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbArea, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbDificuldade, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap())))
    );

    layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btExcluir, btNovo, btSalvar});

    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(btVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(btAtualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(cbArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(cbDificuldade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(cbSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel1)
          .addComponent(jLabel2)
          .addComponent(jLabel3)
          .addComponent(jLabel9)
          .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(7, 7, 7)
        .addComponent(jLabel4)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel5)
          .addComponent(Correta1))
        .addGap(1, 1, 1)
        .addComponent(tfAlt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel6)
          .addComponent(Correta2))
        .addGap(3, 3, 3)
        .addComponent(tfAlt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel7)
          .addComponent(Correta3))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(tfAlt3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel8)
          .addComponent(Correta4))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(tfAlt4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(btSalvar)
          .addComponent(btNovo)
          .addComponent(lbEdicao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap())
    );

    layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btExcluir, btNovo, btSalvar});

    pack();
    setLocationRelativeTo(null);
  }// </editor-fold>//GEN-END:initComponents

  private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
    dispose();
  }//GEN-LAST:event_btVoltarActionPerformed

  private void btAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtualizarActionPerformed
    AtualizaTabela();
  }//GEN-LAST:event_btAtualizarActionPerformed

  private void tbPerguntasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPerguntasMouseClicked
    if(tbPerguntas.getSelectedRow() == -1)
      return; 
    
    AtualizaInfoPer(tbPerguntas.getSelectedRow());
  }//GEN-LAST:event_tbPerguntasMouseClicked

  private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
    if(tfCodigo.getText().isEmpty())
      return;
    
    if(Metodos.msgConfirma("Tem certeza que deseja excluir a pergunta de código '"+tfCodigo.getText()+"'?")){
      String res = QuizIFCliente.ccont.ExcluirPergunta(Integer.parseInt(tfCodigo.getText()));
      switch(Pedaco(res,"^",1)){
        case "A" -> Metodos.Aviso(this.getTitle(), Pedaco(res,"^",2));
        case "E" -> Metodos.Erro(this.getTitle(), "Erro ao deletar a pergunta!"+Pedaco(res,"^",2));
        default -> {
          Metodos.Sucesso(this.getTitle(), "Pergunta deletada com sucesso!");
          LimpaCampos();
          AtualizaTabela();
        }
      }  
    }
  }//GEN-LAST:event_btExcluirActionPerformed

  private void btNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoActionPerformed
    LimpaCampos();
  }//GEN-LAST:event_btNovoActionPerformed

  private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
    if(!Metodos.Consistencia(true, tfPergunta, tfAlt1, tfAlt2, tfAlt3, tfAlt4))
      return; 
    
    //testa os quatro campos de checkbox com um XOR pra ver se 1 e apenas 1 está marcado
    if(!(Correta1.isSelected() ^ Correta2.isSelected() ^ Correta3.isSelected() ^ Correta4.isSelected())){
      Metodos.Aviso(this.getTitle(), "É necessário que uma e apenas uma alternativa seja marcada como correta!");
      return;
    }
    
    if(tfCodigo.getText().isEmpty() && GModoEdicao){
      Metodos.Aviso(this.getTitle(), "Modo de edição sem um código de pergunta, tente recarregar a pergunta!");
      return;
    }
    
    //Cria objeto 
    Area a = GListaCombo.get(cbArea.getSelectedIndex());

    //pega só o número da dificuldade pra guardar no banco
    int dif = Integer.parseInt(Metodos.Pedaco((String)cbDificuldade.getSelectedItem(), " - ", 1));

    char sit = ((String)cbSituacao.getSelectedItem()).charAt(0);
    
    String per = tfPergunta.getText();
    
    String alternativas = "";
    
    alternativas = tfAlt1.getText() + "Ѫ" + tfAlt2.getText() + "Ѫ" +
                   tfAlt3.getText() + "Ѫ" + tfAlt4.getText();
    
    int correta = 0;
    
    if(Correta1.isSelected()){
      correta = 1;
    } else if(Correta2.isSelected()){
      correta = 2;
    } else if(Correta3.isSelected()){
      correta = 3;
    } else if(Correta4.isSelected()){
      correta = 4;
    }
    //Area area, String pergunta, int dificuldade, String alternativas, int correta, char situacao
    Pergunta p = new Pergunta(a, per, dif, alternativas, correta, sit);
    
    //Cria objeto FIM
    
    if(GModoEdicao){
      p.setCodPergunta(Integer.parseInt(tfCodigo.getText()));
      
      if(QuizIFCliente.ccont.AlteraPergunta(p)){
        Metodos.Sucesso(this.getTitle(), "Pergunta alterada com sucesso!");
        AtualizaTabela();
      } else {
        Metodos.Erro(this.getTitle(), "Erro ao alterar pergunta!");
      }
    } else {
      if(QuizIFCliente.ccont.InserePergunta(p)){
        Metodos.Sucesso(this.getTitle(), "Pergunta gravada com sucesso!");
        LimpaCampos();
        AtualizaTabela();
      } else {
        Metodos.Erro(this.getTitle(), "Erro ao gravar pergunta!");
      }
    }
    
  }//GEN-LAST:event_btSalvarActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JCheckBox Correta1;
  private javax.swing.JCheckBox Correta2;
  private javax.swing.JCheckBox Correta3;
  private javax.swing.JCheckBox Correta4;
  private javax.swing.JButton btAtualizar;
  private javax.swing.JButton btExcluir;
  private javax.swing.JButton btNovo;
  private javax.swing.JButton btSalvar;
  private javax.swing.JButton btVoltar;
  private javax.swing.JComboBox<Area> cbArea;
  private javax.swing.JComboBox<String> cbDificuldade;
  private javax.swing.JComboBox<String> cbSituacao;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JLabel jLabel8;
  private javax.swing.JLabel jLabel9;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JLabel lbEdicao;
  private javax.swing.JTable tbPerguntas;
  private javax.swing.JTextField tfAlt1;
  private javax.swing.JTextField tfAlt2;
  private javax.swing.JTextField tfAlt3;
  private javax.swing.JTextField tfAlt4;
  private javax.swing.JTextField tfCodigo;
  private javax.swing.JTextArea tfPergunta;
  // End of variables declaration//GEN-END:variables

  private void attComboBoxArea(int i) {
    //não precisa pegar do banco mais de uma vez, mas atualiza caso passe 10 atualizações
    if(GListaCombo != null || contAtt != 10){
      GListaCombo = QuizIFCliente.ccont.getListaArea();
      contAtt = 0;
    } else {
      contAtt++;
    }
    ComboBoxArea.preencheComboboxArea(i, cbArea, GListaCombo, false);
  }

  private void AtualizaTabela() {
     
    ArrayList<Pergunta> listaPer = QuizIFCliente.ccont.getPerguntas();
    if(listaPer != null){
      GPerguntas = new PerguntasTableModel(listaPer);
      tbPerguntas.setModel(GPerguntas);
    }
  }

  private void AtualizaInfoPer(int selectedRow) {
    // isso carrega as informações de uma pergunta da tabela e liga o modo de edição
    GModoEdicao = true;
    lbEdicao.setVisible(true);
    
    Pergunta per = GPerguntas.getPergunta(selectedRow);
    
    tfCodigo.setText(String.valueOf(per.getCodPergunta()));
    
    attComboBoxArea(per.getArea().getCodArea());
    
    int dif = switch (per.getDificuldade()) {
      case 1 -> 0;  //fácil
      case 2 -> 1;  //médio
      case 3 -> 2;  //difícil
      default -> 1; //médio
    };
    
    cbDificuldade.setSelectedIndex(dif);   
    cbSituacao.setSelectedIndex((per.getSituacao() == 'A' ? 0 : 1));
    
    tfPergunta.setText(per.getPergunta());
    
    tfAlt1.setText(Pedaco(per.getAlternativas(),"Ѫ", 1));
    tfAlt2.setText(Pedaco(per.getAlternativas(),"Ѫ", 2));
    tfAlt3.setText(Pedaco(per.getAlternativas(),"Ѫ", 3));
    tfAlt4.setText(Pedaco(per.getAlternativas(),"Ѫ", 4));
    
    Correta1.setSelected(false);
    Correta2.setSelected(false);
    Correta3.setSelected(false);
    Correta4.setSelected(false);
    
    switch(per.getCorreta()){
      case 1 -> Correta1.setSelected(true);
      case 2 -> Correta2.setSelected(true);
      case 3 -> Correta3.setSelected(true);
      case 4 -> Correta4.setSelected(true);
      default -> {}
    }
    
  }

  private void LimpaCampos() {
    //reseta todos os campos e desliga o modo de edição
    GModoEdicao = false;
    lbEdicao.setVisible(false);
    
    tfCodigo.setText("");
    
    attComboBoxArea(-1);
    
    cbDificuldade.setSelectedIndex(1);
    
    cbSituacao.setSelectedIndex(0);
    tfPergunta.setText("");
    
    tfAlt1.setText("");
    tfAlt2.setText("");
    tfAlt3.setText("");
    tfAlt4.setText("");
    
    Correta1.setSelected(false);
    Correta2.setSelected(false);
    Correta3.setSelected(false);
    Correta4.setSelected(false);
  }
}
