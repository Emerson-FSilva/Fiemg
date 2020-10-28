package br.com.fiemg.view;

import br.com.fieng.conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class CadAluno extends javax.swing.JInternalFrame {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public CadAluno() {
        initComponents();
        con = conexao.conectar();
    }

    private void inserir() {
        String sql = "insert into aluno (nome, sexo) values (?,?);";
        if (txtCadAlunoNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha os campo");
        } else {
            try {
                pst = con.prepareStatement(sql);
                pst.setString(1, txtCadAlunoNome.getText());
                pst.setString(2, validaSexo());
                int op = pst.executeUpdate();
                if (op > 0) {
                    JOptionPane.showMessageDialog(null, "Inserido com sucesso");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro" + e);
            }
        }
    }

    private String validaSexo() {
        if (jrAlunoMasculino.isSelected()) {
            return jrAlunoMasculino.getText();
        } else {
            return jrAlunoFeminino.getText();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GrupoSexo = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCadAlunoID = new javax.swing.JTextField();
        txtCadAlunoNome = new javax.swing.JTextField();
        jrAlunoMasculino = new javax.swing.JRadioButton();
        jrAlunoFeminino = new javax.swing.JRadioButton();
        btnGravar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelaAluno = new javax.swing.JTable();

        setTitle("Cadastro Aluno");

        jLabel1.setText("id");

        jLabel2.setText("Nome");

        jLabel3.setText("Sexo");

        txtCadAlunoID.setEnabled(false);
        txtCadAlunoID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCadAlunoIDActionPerformed(evt);
            }
        });

        GrupoSexo.add(jrAlunoMasculino);
        jrAlunoMasculino.setSelected(true);
        jrAlunoMasculino.setText("Masculino");

        GrupoSexo.add(jrAlunoFeminino);
        jrAlunoFeminino.setText("Feminino");

        btnGravar.setText("Gravar");
        btnGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravarActionPerformed(evt);
            }
        });

        TabelaAluno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nome", "Sexo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TabelaAluno);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCadAlunoID, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCadAlunoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jrAlunoMasculino)
                                    .addComponent(jrAlunoFeminino)))
                            .addComponent(btnGravar))
                        .addContainerGap(296, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCadAlunoID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCadAlunoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jrAlunoMasculino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jrAlunoFeminino)))
                .addGap(18, 18, 18)
                .addComponent(btnGravar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCadAlunoIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCadAlunoIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCadAlunoIDActionPerformed

    private void btnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarActionPerformed
      inserir();
    }//GEN-LAST:event_btnGravarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup GrupoSexo;
    private javax.swing.JTable TabelaAluno;
    private javax.swing.JButton btnGravar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton jrAlunoFeminino;
    private javax.swing.JRadioButton jrAlunoMasculino;
    private javax.swing.JTextField txtCadAlunoID;
    private javax.swing.JTextField txtCadAlunoNome;
    // End of variables declaration//GEN-END:variables
}
