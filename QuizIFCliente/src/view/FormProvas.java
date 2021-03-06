
//Mateus Roberto Algayer - 10/01/2022

package view;

import ModelDominio.Prova;
import ModelDominio.Area;
import java.util.ArrayList;
import util.Metodos;
import view.util.ComboBoxArea;
import ModelDominio.Pergunta;
import java.awt.Color;
import view.tablemodel.PerguntasTableModel;

public class FormProvas extends javax.swing.JFrame {

  private PerguntasTableModel GPerguntasDisModel;
  private PerguntasTableModel GPerguntasSelModel;
  
  private final boolean GModoEdicao;
  private Prova GProva;
  private ArrayList<Area> GListaCombo;
  
  public FormProvas(Prova prova) {
    initComponents();
    Metodos.configuraForms(this);
    
    if(prova != null){
      AtualizaTabelas(prova.getCodigoProva());
      btExcluir.setVisible(true);
      GModoEdicao = true;
      GProva = prova;
      CarregaInfoProva(prova); 
    } else {
      AtualizaTabelas(0);
      btExcluir.setVisible(false);
      GModoEdicao = false;
    }
        
    attComboArea();
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    tfNome = new javax.swing.JTextField();
    jPanel1 = new javax.swing.JPanel();
    jScrollPane3 = new javax.swing.JScrollPane();
    tbPerSel = new javax.swing.JTable();
    btRemover = new javax.swing.JButton();
    btAdicionar = new javax.swing.JButton();
    jScrollPane4 = new javax.swing.JScrollPane();
    tbPerDis = new javax.swing.JTable();
    lbPerguntasSel = new javax.swing.JLabel();
    lbPerguntasDisp = new javax.swing.JLabel();
    jLabel7 = new javax.swing.JLabel();
    jLabel8 = new javax.swing.JLabel();
    btSair = new javax.swing.JButton();
    jLabel2 = new javax.swing.JLabel();
    cbArea = new javax.swing.JComboBox<>();
    jLabel3 = new javax.swing.JLabel();
    cbSituacao = new javax.swing.JComboBox<>();
    btSalvar = new javax.swing.JButton();
    btExcluir = new javax.swing.JButton();
    jLabel4 = new javax.swing.JLabel();
    cbDificuldade = new javax.swing.JComboBox<>();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Manuten????o de provas");
    setIconImage(null);
    setMinimumSize(new java.awt.Dimension(800, 600));
    setName("Manuten????o de provas"); // NOI18N

    jLabel1.setText("Nome:");

    tfNome.setToolTipText("Nome");
    tfNome.setName("TABPRO.NOME"); // NOI18N

    jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

    tbPerSel.setModel(new javax.swing.table.DefaultTableModel(
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
    tbPerSel.setShowGrid(true);
    tbPerSel.getTableHeader().setReorderingAllowed(false);
    jScrollPane3.setViewportView(tbPerSel);

    btRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icoRemove32.png"))); // NOI18N
    btRemover.setText("Remover");
    btRemover.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btRemoverActionPerformed(evt);
      }
    });

    btAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icoAdd32.png"))); // NOI18N
    btAdicionar.setText("Adicionar");
    btAdicionar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btAdicionarActionPerformed(evt);
      }
    });

    tbPerDis.setModel(new javax.swing.table.DefaultTableModel(
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
    tbPerDis.setShowHorizontalLines(true);
    tbPerDis.setShowVerticalLines(true);
    tbPerDis.getTableHeader().setReorderingAllowed(false);
    jScrollPane4.setViewportView(tbPerDis);

    lbPerguntasSel.setText("0/30");

    lbPerguntasDisp.setText("contPer");

    jLabel7.setText("Perguntas selecionadas:");

    jLabel8.setText("Perguntas dispon??veis:");

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(lbPerguntasSel)
              .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbPerguntasDisp))
              .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                  .addComponent(btAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                  .addComponent(btRemover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(jLabel7)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel8)))
        .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel7)
          .addComponent(jLabel8))
        .addGap(4, 4, 4)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(btAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(33, 33, 33)
            .addComponent(btRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
          .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
          .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        .addGap(4, 4, 4)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(lbPerguntasSel)
          .addComponent(lbPerguntasDisp))
        .addContainerGap())
    );

    btSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/imagens/icoFechar32.png"))); // NOI18N
    btSair.setText("Voltar");
    btSair.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btSairActionPerformed(evt);
      }
    });

    jLabel2.setText("??rea:");

    jLabel3.setText("Situa????o:");

    cbSituacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ativo", "Inativo" }));
    cbSituacao.setName("Situa????o"); // NOI18N

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

    jLabel4.setText("Dificuldade:");

    cbDificuldade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 - F??cil", "2 - M??dio", "3 - Dif??cil" }));
    cbDificuldade.setSelectedIndex(1);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(jLabel1)
              .addComponent(jLabel2))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
              .addGroup(layout.createSequentialGroup()
                .addComponent(cbArea, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbDificuldade, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addComponent(tfNome))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
            .addComponent(btSair, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addGroup(layout.createSequentialGroup()
                .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap())))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(btSair, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addGroup(layout.createSequentialGroup()
            .addGap(16, 16, 16)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(jLabel1)
              .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(cbArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jLabel2)
              .addComponent(jLabel4)
              .addComponent(cbDificuldade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jLabel3)
              .addComponent(cbSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(btSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
          .addComponent(btExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
    );

    pack();
    setLocationRelativeTo(null);
  }// </editor-fold>//GEN-END:initComponents

  private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
    dispose();
  }//GEN-LAST:event_btSairActionPerformed

  private void btRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoverActionPerformed
    if(tbPerSel.getSelectedRow() == -1)
      return;
    
    Pergunta p = GPerguntasSelModel.getPergunta(tbPerSel.getSelectedRow());
    
    GPerguntasSelModel.removePergunta(tbPerSel.getSelectedRow());
    
    GPerguntasDisModel.adicionaPergunta(p);
    
    AtualizaInfo();
  }//GEN-LAST:event_btRemoverActionPerformed

  private void btAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAdicionarActionPerformed
    if(tbPerDis.getSelectedRow() == -1)
      return;
    
    if(GPerguntasSelModel.getRowCount() >= 30){
      Metodos.aviso(this.getTitle(), "N??mero m??ximo de perguntas atingido!");
      return;
    }  
    
    Pergunta p = GPerguntasDisModel.getPergunta(tbPerDis.getSelectedRow());
    
    GPerguntasDisModel.removePergunta(tbPerDis.getSelectedRow());
    
    GPerguntasSelModel.adicionaPergunta(p);
    
    AtualizaInfo();
  }//GEN-LAST:event_btAdicionarActionPerformed

  private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
    if(Metodos.msgConfirma("Tem certeza que deseja excluir a prova '"+GProva.getNomeProva()+"'?")){
//      switch(QuizIFCliente.ccont.ExcluirProva(GProva.getCodigoProva())){
//        case 1 -> Metodos.Aviso(this.getTitle(), "Prova n??o pode ser deletada pois j?? foi realizada por algum usu??rio\n utilize o campo 'Situa????o' para inativar a prova");
//        case 2 -> Metodos.Erro(this.getTitle(), "Erro ao deletar a prova!");
//        default -> {
//          Metodos.Sucesso(this.getTitle(), "Prova deletada com sucesso!");
//          dispose();
//        }
//      }  
      String res = QuizIFCliente.ccont.excluirProva(GProva.getCodigoProva());
      
      if(Metodos.processaMsgServidor(this.getTitle(), res, "Prova deletada com sucesso!", "Erro ao deletar a prova!")){     
        dispose();
      }
      
//      switch(Pedaco(res,"^",1)){
//        case "A" -> Metodos.Aviso(this.getTitle(), Pedaco(res,"^",2));
//        case "E" -> Metodos.Erro(this.getTitle(), "Erro ao deletar a prova!\n"+Pedaco(res,"^",2));
//        default -> {
//          Metodos.Sucesso(this.getTitle(), "Prova deletada com sucesso!");
//          dispose();
//        }
//      }  
    }
  }//GEN-LAST:event_btExcluirActionPerformed

  private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
    if (!Metodos.consistencia(true, tfNome)) 
      return;
    
    if (GPerguntasSelModel.getRowCount() == 0){
      Metodos.aviso(this.getTitle(), "Ao menos uma pergunta deve ser selecionada!");
      return;
    }
    
    if (GPerguntasSelModel.getRowCount() > 30){
      Metodos.aviso(this.getTitle(), "N??mero de perguntas selecionadas n??o pode ultrapassar 30!");
      return;
    }
    
    Area a = GListaCombo.get(cbArea.getSelectedIndex());
    
    //pega s?? o n??mero da dificuldade pra guardar no banco
    int dif = Integer.parseInt(Metodos.pedaco((String)cbDificuldade.getSelectedItem(), " - ", 1));

    char sit = ((String)cbSituacao.getSelectedItem()).charAt(0);

    Prova p = new Prova(tfNome.getText(), a, dif, sit);

    ArrayList<Pergunta> cadPerSel = new ArrayList<>();

    for(int x = 0;x < GPerguntasSelModel.getRowCount();x++)
      cadPerSel.add(GPerguntasSelModel.getPergunta(x));  
    
    String res;
    
    if (GModoEdicao){
      //seta o codigo do objeto de altera????o pra o c??digo da prova a ser editada.
      //cuidado, se entrar aqui sem uma prova explode
      p.setCodigoProva(GProva.getCodigoProva());
      res = QuizIFCliente.ccont.modificarProva(p, cadPerSel);
      
      if(Metodos.processaMsgServidor(this.getTitle(), res, "Prova alterada com sucesso!", "Erro ao alterar a prova!")){     
        dispose();
      }
      
//      switch(Pedaco(res,"^",1)){
//        case "A" -> Metodos.Aviso(this.getTitle(), Pedaco(res,"^",2));
//        case "E" -> Metodos.Erro(this.getTitle(), "Erro ao alterar a prova!\n"+Pedaco(res,"^",2));
//        default -> {
//          Metodos.Sucesso(this.getTitle(), "Prova alterada com sucesso!");
//          dispose();
//        }
//      } 
//      if(QuizIFCliente.ccont.ModificarProva(p, cadPerSel)){
//        Metodos.Sucesso(this.getTitle(), "Prova alterada com sucesso!");
//        dispose();
//      } else {
//        Metodos.Erro(this.getTitle(),"Erro ao editar a prova!");
//      }
    } else {
      res = QuizIFCliente.ccont.inserirProva(p, cadPerSel);
      
      if(Metodos.processaMsgServidor(this.getTitle(), res, "Prova gravada com sucesso!", "Erro ao gravar a prova!")){     
        dispose();
      }
      
//      switch(pedaco(res,"^",1)){;
//        case "A" -> Metodos.aviso(this.getTitle(), pedaco(res,"^",2));
//        case "E" -> Metodos.erro(this.getTitle(), "Erro ao gravar a prova!\n"+pedaco(res,"^",2));
//        default -> {
//          Metodos.sucesso(this.getTitle(), "prova gravada com sucesso!");
//          dispose();
//        }
//      } 
//      if(QuizIFCliente.ccont.InserirProva(p, cadPerSel)){
//        Metodos.Sucesso(this.getTitle(), "Prova gravada com sucesso!");
//        dispose();
//      } else {
//        Metodos.Erro(this.getTitle(),"Erro ao cadastrar a prova!");
//      }
    }
  }//GEN-LAST:event_btSalvarActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btAdicionar;
  private javax.swing.JButton btExcluir;
  private javax.swing.JButton btRemover;
  private javax.swing.JButton btSair;
  private javax.swing.JButton btSalvar;
  private javax.swing.JComboBox<Area> cbArea;
  private javax.swing.JComboBox<String> cbDificuldade;
  private javax.swing.JComboBox<String> cbSituacao;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JLabel jLabel8;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JScrollPane jScrollPane3;
  private javax.swing.JScrollPane jScrollPane4;
  private javax.swing.JLabel lbPerguntasDisp;
  private javax.swing.JLabel lbPerguntasSel;
  private javax.swing.JTable tbPerDis;
  private javax.swing.JTable tbPerSel;
  private javax.swing.JTextField tfNome;
  // End of variables declaration//GEN-END:variables

  private void AtualizaTabelas(int numProva) {
    
    ArrayList<Pergunta> listaSel = new ArrayList<>();
    ArrayList<Pergunta> listaDis = new ArrayList<>();
    
    QuizIFCliente.ccont.getPerguntasProva(listaSel, listaDis, numProva);
    
    GPerguntasDisModel = new PerguntasTableModel(listaDis);
    tbPerDis.setModel(GPerguntasDisModel);
    GPerguntasDisModel.AtualizaColunas(tbPerDis);
    
    GPerguntasSelModel = new PerguntasTableModel(listaSel);
    tbPerSel.setModel(GPerguntasSelModel);
    GPerguntasSelModel.AtualizaColunas(tbPerSel);
    
    AtualizaInfo();
  }

  private void attComboArea() {
    GListaCombo = QuizIFCliente.ccont.getListaArea();
    
    ComboBoxArea.preencheComboboxArea((GProva != null ? GProva.getAreaGeral().getCodArea() : -1), cbArea, GListaCombo, false);
  }
  
  public void AtualizaInfo(){
    
    GPerguntasDisModel.ordenaLista();
    GPerguntasDisModel.fireTableDataChanged();
    
    GPerguntasSelModel.ordenaLista();
    GPerguntasSelModel.fireTableDataChanged();
    
    lbPerguntasDisp.setText(Integer.toString(GPerguntasDisModel.getRowCount()));
    lbPerguntasSel.setText(Integer.toString(GPerguntasSelModel.getRowCount())+"/30");
    
    if(GPerguntasSelModel.getRowCount() >= 30){
      lbPerguntasSel.setForeground(Color.red);
    } else {
      lbPerguntasSel.setForeground(Color.black);
    }
  }
  
  private void CarregaInfoProva(Prova prova) {
    tfNome.setText(prova.getNomeProva());
    
    int dif = switch (prova.getDificuldade()) {
      case 1 -> 0;  //f??cil
      case 2 -> 1;  //m??dio
      case 3 -> 2;  //dif??cil
      default -> 1; //m??dio
    };
    
    cbDificuldade.setSelectedIndex(dif);   
    cbSituacao.setSelectedIndex((prova.getSituacao() == 'A' ? 0 : 1));
  }
}
