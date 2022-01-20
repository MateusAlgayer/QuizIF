
//Mateus Roberto Algayer - 03/01/2022 :: Criação
//João Felipe Staub - 19/01/2022 :: Término

package view;

import ModelDominio.Usuario;
import util.Metodos;
import view.tablemodel.UsuarioTableModel;

public class FormManutPermissao extends javax.swing.JFrame {

    private UsuarioTableModel usuModel;
    
    private void atualizaTabela(){
        usuModel = new UsuarioTableModel(QuizIFCliente.ccont.getUsuarios());
        
        tUsu.setModel(usuModel);
    }
    
  public FormManutPermissao() {
    initComponents();
    atualizaTabela();
  }

  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tUsu = new javax.swing.JTable();
        btUsuPadrao = new javax.swing.JToggleButton();
        btUsuCriador = new javax.swing.JToggleButton();
        btUsuAdm = new javax.swing.JToggleButton();
        btVoltar = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Permissões de usuário");
        setMinimumSize(new java.awt.Dimension(900, 460));
        setName("Permissões de usuário"); // NOI18N
        setPreferredSize(new java.awt.Dimension(900, 460));

        tUsu.setModel(new javax.swing.table.DefaultTableModel(
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
        tUsu.getTableHeader().setResizingAllowed(false);
        tUsu.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tUsu);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                .addContainerGap())
        );

        btUsuPadrao.setText("Tornar comum");
        btUsuPadrao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUsuPadraoActionPerformed(evt);
            }
        });

        btUsuCriador.setText("Tornar Criador");
        btUsuCriador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUsuCriadorActionPerformed(evt);
            }
        });

        btUsuAdm.setText("Tornar Administrador");
        btUsuAdm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUsuAdmActionPerformed(evt);
            }
        });

        btVoltar.setText("Voltar");
        btVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btUsuPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btUsuAdm, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btUsuCriador, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(btUsuPadrao, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btUsuCriador, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btUsuAdm, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btUsuCriadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUsuCriadorActionPerformed
        AlteraTipoUsuario("J");
    }//GEN-LAST:event_btUsuCriadorActionPerformed

    private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
        dispose();
    }//GEN-LAST:event_btVoltarActionPerformed

    private void btUsuPadraoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUsuPadraoActionPerformed
        AlteraTipoUsuario("C");
    }//GEN-LAST:event_btUsuPadraoActionPerformed

    private void btUsuAdmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUsuAdmActionPerformed
        AlteraTipoUsuario("A");
    }//GEN-LAST:event_btUsuAdmActionPerformed

    private void AlteraTipoUsuario(String tipo){
        
        if(tUsu.getSelectedRow() == -1){
            Metodos.Aviso(this.getTitle(), "Necessário a seleção de usuário!");
            return;
        }
        
        Usuario usu = usuModel.getUsuario(tUsu.getSelectedRow());
        
        String tipoLiteral = switch(tipo){
            case "A" -> "Administrador";
            case "J" -> "Criador";
            case "C" -> "Comum";
            default -> "Comum";
        };
        
        if(Metodos.msgConfirma("Tem certeza que deseja alterar a permissão \n"+ 
                               "do usuário "+ usu.getNomeUsuario() +" para " + tipoLiteral + "?")){
            //sim
            if(QuizIFCliente.ccont.AlteraTipoUsu(usu, tipo)){
                atualizaTabela();
                Metodos.Sucesso(this.getTitle(), "Sucesso em alterar o tipo!");
            }
            
        }
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btUsuAdm;
    private javax.swing.JToggleButton btUsuCriador;
    private javax.swing.JToggleButton btUsuPadrao;
    private javax.swing.JToggleButton btVoltar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tUsu;
    // End of variables declaration//GEN-END:variables
}
