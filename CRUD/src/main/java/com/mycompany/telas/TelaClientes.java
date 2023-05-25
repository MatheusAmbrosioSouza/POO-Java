package com.mycompany.telas;

import java.sql.*;
import com.mycompany.dal.ModuloConexao;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class TelaClientes extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public TelaClientes() {
        conexao = ModuloConexao.conector();
        initComponents();
        pesquisar_cliente();
    }

    private void adicionar(){
        String sql =  "INSERT INTO company (\"Empresa\", \"Comercial\", \"OBS\")VALUES (?,?,?);";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtEmpresa.getText());
            pst.setString(2, txtComercial.getText());
            pst.setString(3, txtOBS.getText());          
            if (txtEmpresa.getText().isEmpty() || txtComercial.getText().isEmpty() || txtOBS.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatórios!");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado>0){
                    JOptionPane.showMessageDialog(null,"Cliente adicionado com sucesso!");
                    limpar();
                }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    //método para pesquisar um cliente pelo nome
    private void pesquisar_cliente(){
        
        String sql = "SELECT \"id\" as ID, \"Empresa\" as Empresa, \"Comercial\" as Comercial, \"OBS\" as OBS FROM company "
                + "WHERE \"Empresa\" LIKE ?";
        try {
            pst = conexao.prepareStatement(sql);
            //passando o conteúdo da caixa de pesquisa para o ?
            //atenção ao "%" - continuação da string sql
            pst.setString(1, txtBuscaCliente.getText() + "%");
            rs = pst.executeQuery();
            //a linha abaixo usa a biblioteca rs2xml.jar para preencher a tabela
            tblClientes.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    //método para setar os campos do formulário com o conteudo da tabela
    public void setar_campos(){
        int setar = tblClientes.getSelectedRow();
        txtId.setText(tblClientes.getModel().getValueAt(setar, 0).toString());
        txtEmpresa.setText(tblClientes.getModel().getValueAt(setar, 1).toString());
        txtComercial.setText(tblClientes.getModel().getValueAt(setar, 2).toString());
        txtOBS.setText(tblClientes.getModel().getValueAt(setar, 3).toString());       
    }
    
    //criação do método para alterar dados do cliente
    private void alterar(){
        String sql = "UPDATE company set \"Empresa\"=?, \"Comercial\"=?, \"OBS\"=? WHERE id=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtEmpresa.getText());
            pst.setString(2, txtComercial.getText());
            pst.setString(3, txtOBS.getText());
            pst.setInt(4, Integer.parseInt(txtId.getText()));
            
            if (txtEmpresa.getText().isEmpty() || txtComercial.getText().isEmpty() || txtOBS.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatórios!");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado>0){
                    JOptionPane.showMessageDialog(null,"Dados do cliente alterado com sucesso!");
                    limpar();
                }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void remover(){
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja remover este cliente?",
                "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION){
            String sql = "DELETE FROM company WHERE id=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setInt(1, Integer.parseInt(txtId.getText()));
                int apagado = pst.executeUpdate();
                if (apagado > 0){
                    JOptionPane.showMessageDialog(null, "Cliente removido com sucesso!");
                    limpar();
                }
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
    private void limpar(){
        txtBuscaCliente.setText(null);
        txtId.setText(null);
        txtEmpresa.setText(null);
        txtComercial.setText(null);
        txtOBS.setText(null);
        ((DefaultTableModel) tblClientes.getModel()).setRowCount(0);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtEmpresa = new javax.swing.JTextField();
        txtComercial = new javax.swing.JTextField();
        txtOBS = new javax.swing.JTextField();
        txtBuscaCliente = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        btn_add = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_del = new javax.swing.JButton();
        btn_clean = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Clientes");

        jLabel1.setText("Empresa");

        jLabel2.setText("Comercial");

        jLabel3.setText("OBS");

        txtBuscaCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscaClienteKeyReleased(evt);
            }
        });

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Empresa", "Comercial", "OBS"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);
        if (tblClientes.getColumnModel().getColumnCount() > 0) {
            tblClientes.getColumnModel().getColumn(0).setHeaderValue("ID");
            tblClientes.getColumnModel().getColumn(1).setHeaderValue("Empresa");
            tblClientes.getColumnModel().getColumn(2).setHeaderValue("Comercial");
            tblClientes.getColumnModel().getColumn(3).setHeaderValue("OBS");
        }

        jLabel6.setText("Id");

        txtId.setEnabled(false);

        btn_add.setText("Adicionar");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        btn_update.setText("Atualizar");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_del.setText("Deletar");
        btn_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delActionPerformed(evt);
            }
        });

        btn_clean.setText("Limpar");
        btn_clean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cleanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBuscaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel6))
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtEmpresa, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                                    .addComponent(txtComercial)
                                    .addComponent(txtOBS)
                                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_clean, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_add)
                        .addGap(27, 27, 27)
                        .addComponent(btn_update)
                        .addGap(32, 32, 32)
                        .addComponent(btn_del)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtBuscaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtComercial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtOBS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_add)
                    .addComponent(btn_update)
                    .addComponent(btn_del))
                .addGap(26, 26, 26)
                .addComponent(btn_clean)
                .addGap(38, 38, 38))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscaClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaClienteKeyReleased
       
        pesquisar_cliente();
      
        
    }//GEN-LAST:event_txtBuscaClienteKeyReleased

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        
        setar_campos();
        
    }//GEN-LAST:event_tblClientesMouseClicked

    private void btn_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delActionPerformed
       remover();
        pesquisar_cliente();
    }//GEN-LAST:event_btn_delActionPerformed

    private void btn_cleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cleanActionPerformed
       limpar();
        pesquisar_cliente();
    }//GEN-LAST:event_btn_cleanActionPerformed

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
       adicionar();
        pesquisar_cliente();
    }//GEN-LAST:event_btn_addActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        alterar();
         pesquisar_cliente();
    }//GEN-LAST:event_btn_updateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_clean;
    private javax.swing.JButton btn_del;
    private javax.swing.JButton btn_update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtBuscaCliente;
    private javax.swing.JTextField txtComercial;
    private javax.swing.JTextField txtEmpresa;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtOBS;
    // End of variables declaration//GEN-END:variables
}
