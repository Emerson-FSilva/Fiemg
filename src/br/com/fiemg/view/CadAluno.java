package br.com.fiemg.view;

import br.com.fieng.conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CadAluno extends javax.swing.JInternalFrame {

    Connection con = null;
    PreparedStatement pst = null;
    PreparedStatement pstRoll = null;
    ResultSet rs = null;
    ResultSet rsRoll = null;

    public CadAluno() {
        initComponents();
        con = conexao.conectar();
        try {
            pstRoll = con.prepareStatement("select * from aluno", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rsRoll = pstRoll.executeQuery();
            rsRoll.first();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consutar" + ex);
        }
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

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro" + ex);
            }
        }
    }

    private void consultar() {
        DefaultTableModel dtm = (DefaultTableModel) TabelaAluno.getModel();
        dtm.setRowCount(0);
        String sql = "select * from aluno where nome like '%" + txtPesquisaAluno.getText() + "%'";
        System.out.println(sql);
        if (txtPesquisaAluno.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Coloque um filtro");
        } else {
            try {
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();

                while (rs.next()) {
                    dtm.addRow(new Object[]{rs.getObject(1), rs.getObject(2), rs.getObject(3)});
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);

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

    private void Avancar() {
        try {
            rsRoll.next();
            txtCadAlunoNome.setText(rs.getString(2));
            if (rsRoll.getString(3).equals("Masculino")) {
                jrAlunoMasculino.setSelected(true);
            } else {
                jrAlunoFeminino.setSelected(true);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    private void Anterior() {
        try {
            rsRoll.previous();
            txtCadAlunoNome.setText(rsRoll.getString(2));
            if (rsRoll.getString(3).equals("Masculino")) {
                jrAlunoMasculino.setSelected(true);
            } else {
                jrAlunoFeminino.setSelected(true);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    private void AvancaPrimeiro() {
        try {
            rsRoll.first();
            txtCadAlunoNome.setText(rsRoll.getString(2));
            if (rsRoll.getString(3).equals("Masculino")) {
                jrAlunoMasculino.setSelected(true);
            } else {
                jrAlunoFeminino.setSelected(true);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    private void AvancaUltimo() {
        try {
            rsRoll.last();
            txtCadAlunoNome.setText(rsRoll.getString(2));
            if (rsRoll.getString(3).equals("Masculino")) {
                jrAlunoMasculino.setSelected(true);
            } else {
                jrAlunoFeminino.setSelected(true);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
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
        btnPesuisa = new javax.swing.JButton();
        txtPesquisaAluno = new javax.swing.JTextField();
        btnPrimeiro = new javax.swing.JButton();
        btnAnterior = new javax.swing.JButton();
        btnProximo = new javax.swing.JButton();
        btnUltimo = new javax.swing.JButton();

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

        btnPesuisa.setText("Pesquisa");
        btnPesuisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesuisaActionPerformed(evt);
            }
        });

        btnPrimeiro.setText("<<");
        btnPrimeiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrimeiroActionPerformed(evt);
            }
        });

        btnAnterior.setText("<");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        btnProximo.setText(">");
        btnProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProximoActionPerformed(evt);
            }
        });

        btnUltimo.setText(">>");
        btnUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUltimoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnPesuisa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPesquisaAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jrAlunoMasculino)
                                    .addComponent(jrAlunoFeminino)
                                    .addComponent(btnGravar)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnPrimeiro)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnAnterior)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnProximo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnUltimo))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCadAlunoID, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCadAlunoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrimeiro)
                    .addComponent(btnAnterior)
                    .addComponent(btnProximo)
                    .addComponent(btnUltimo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPesuisa)
                    .addComponent(txtPesquisaAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCadAlunoIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCadAlunoIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCadAlunoIDActionPerformed

    private void btnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarActionPerformed
        inserir();
    }//GEN-LAST:event_btnGravarActionPerformed

    private void btnPesuisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesuisaActionPerformed
        consultar();
    }//GEN-LAST:event_btnPesuisaActionPerformed

    private void btnProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProximoActionPerformed
        Avancar();
    }//GEN-LAST:event_btnProximoActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed

        Anterior();
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void btnPrimeiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrimeiroActionPerformed

        AvancaPrimeiro();
    }//GEN-LAST:event_btnPrimeiroActionPerformed

    private void btnUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUltimoActionPerformed

        AvancaUltimo();
    }//GEN-LAST:event_btnUltimoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup GrupoSexo;
    private javax.swing.JTable TabelaAluno;
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnGravar;
    private javax.swing.JButton btnPesuisa;
    private javax.swing.JButton btnPrimeiro;
    private javax.swing.JButton btnProximo;
    private javax.swing.JButton btnUltimo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton jrAlunoFeminino;
    private javax.swing.JRadioButton jrAlunoMasculino;
    private javax.swing.JTextField txtCadAlunoID;
    private javax.swing.JTextField txtCadAlunoNome;
    private javax.swing.JTextField txtPesquisaAluno;
    // End of variables declaration//GEN-END:variables
}
