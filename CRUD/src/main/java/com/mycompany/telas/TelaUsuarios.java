package com.mycompany.telas;

import java.sql.*;
import com.mycompany.dal.ModuloConexao;
import javax.swing.JOptionPane;

/**
 *
 * @author Matheus Ambrosio
 */
public class TelaUsuarios extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public TelaUsuarios() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
    
    private void consultar(){
        String sql = "SELECT * FROM usuarios WHERE iduser=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtId.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                txtNome.setText(rs.getString(2));
                txtFone.setText(rs.getString(3));
                txtLogin.setText(rs.getString(4));
                txtSenha.setText(rs.getString(5));
                comboPerfil.setSelectedItem(rs.getString(6));
            } else {
                JOptionPane.showMessageDialog(null,"Usuário não cadastrado!");
                txtNome.setText(null);
                txtFone.setText(null);
                txtLogin.setText(null);
                txtSenha.setText(null);
                comboPerfil.setSelectedItem(null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void adicionar(){
        String sql = "INSERT INTO usuarios(iduser,usuario,fone,login,senha,perfil) VALUES(?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtId.getText());
            pst.setString(2, txtNome.getText());
            pst.setString(3, txtFone.getText());
            pst.setString(4, txtLogin.getText());
            String captura_senha = new String(txtSenha.getPassword());
            pst.setString(5, captura_senha);
            pst.setString(6, comboPerfil.getSelectedItem().toString());
            if (txtId.getText().isEmpty() || txtNome.getText().isEmpty() || txtFone.getText().isEmpty() || 
                    txtLogin.getText().isEmpty() || txtSenha.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatórios!");
                
            } else {
                int adicionado = pst.executeUpdate();
            if (adicionado>0){
                JOptionPane.showMessageDialog(null,"Usuário adicionado com sucesso!");
                txtId.setText(null);
                txtNome.setText(null);
                txtFone.setText(null);
                txtLogin.setText(null);
                txtSenha.setText(null);
                
            }
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void alterar(){
        String sql = "UPDATE usuarios set usuario=?, fone=?, login=?, senha=?, "
                + "perfil=? WHERE iduser=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtFone.getText());
            pst.setString(3, txtLogin.getText());
            String captura_senha = new String(txtSenha.getPassword());
            pst.setString(4, captura_senha);
            pst.setString(5, comboPerfil.getSelectedItem().toString());
            pst.setString(6, txtId.getText());
            if (txtId.getText().isEmpty() || txtNome.getText().isEmpty() || txtFone.getText().isEmpty() || 
                    txtLogin.getText().isEmpty() || txtSenha.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatórios!");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado>0){
                    JOptionPane.showMessageDialog(null,"Dados do usuário alterado com sucesso!");
                    txtId.setText(null);
                    txtNome.setText(null);
                    txtFone.setText(null);
                    txtLogin.setText(null);
                    txtSenha.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void remover(){
        //antes de remover fazer uma confirmação de remoção
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja remover este usuário?",
                "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION){
            String sql = "DELETE FROM usuarios WHERE iduser=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtId.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0){
                    JOptionPane.showMessageDialog(null, "Usuário removido com sucesso!");
                    txtId.setText(null);
                    txtNome.setText(null);
                    txtFone.setText(null);
                    txtLogin.setText(null);
                    txtSenha.setText(null);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        comboPerfil = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        txtFone = new javax.swing.JTextField();
        txtLogin = new javax.swing.JTextField();
        txtSenha = new javax.swing.JPasswordField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Usuários");
        setMaximumSize(new java.awt.Dimension(640, 480));
        setMinimumSize(new java.awt.Dimension(640, 480));
        setPreferredSize(new java.awt.Dimension(680, 440));

        jLabel1.setText("ID");

        jLabel2.setText("Nome");

        jLabel3.setText("Telefone");

        jLabel4.setText("Login");

        jLabel5.setText("Senha");

        comboPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "user" }));
        comboPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboPerfilActionPerformed(evt);
            }
        });

        jLabel6.setText("Perfil");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(46, 46, 46)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(30, 30, 30)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFone)
                            .addComponent(txtSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLogin)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(comboPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(119, 119, 119))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(txtFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(comboPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(236, Short.MAX_VALUE))
        );

        setBounds(0, 0, 680, 440);
    }// </editor-fold>//GEN-END:initComponents

    private void comboPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPerfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboPerfilActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboPerfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField txtFone;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtNome;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}
