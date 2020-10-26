
package br.com.fieng;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class conexao {
    
public static Connection conectar(){
        Connection con = null;
        final String URL = "jdbc:mysql://localhost:3306/fiemg?useTimezone=true&serverTimezone=UTC";
        final String USER = "root";
        final String PASS = "";
        
        try {
            con = DriverManager.getConnection(URL, USER, PASS);
            return con;
        } catch (Exception e) {
            return null;
        }
    }
    public static void desconectar (Connection con)
    {
        try {
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void editar() {
        String sql = "UPDATE cliente SET nome = ?, endereco = ?, bairro = ?, cidade = ? , cep = ?, sexo = ?, "
                + " cpf = ?, rg = ?, estado_civil = ?, data_nasc =?, celular = ?, email = ? WHERE id = ?";

        try {
            if ((txtClieNome.getText().isEmpty()) || (txtClieEndereco.getText().isEmpty()) || (txtClieBairro.getText().isEmpty() || (txtClieCidade.getText().isEmpty()) || (txtClieCep.getText().isEmpty()) || (txtClieCpf.getText().isEmpty()) || (txtClieRg.getText().isEmpty()) || (txtClieEstadoCivil.getText().isEmpty()) || (txtClieDataNasc.getText().isEmpty()) || (txtClieCelular.getText().isEmpty()) ||
                    (txtClieEMail.getText().isEmpty()))) {
                JOptionPane.showMessageDialog(null, "Favor Preencher Todos os Campos que estao vazios por favor!!  );
            } else {
                pst = con.prepareCall(sql);
                pst.setString(1, txtClieNome.getText());
                pst.setString(2, txtClieEndereco.getText());
                pst.setString(3, txtClieBairro.getText());
                pst.setString(4, txtClieCidade.getText());
                pst.setString(5, txtClieCep.getText());
                pst.setString(6, cbClieSexo.getSelectedItem().toString());
                pst.setString(7, txtClieCpf.getText());
                pst.setString(8, txtClieRg.getText());
                pst.setString(9, txtClieEstadoCivil.getText());
                pst.setString(10, txtClieDataNasc.getText());
                pst.setString(11, txtClieCelular.getText());
                pst.setString(12, txtClieEMail.getText());
                pst.setString(13, txtClieId.getText());
                int valida = pst.executeUpdate();
                if (valida > 0) {
                    JOptionPane.showMessageDialog(null, "Atualizado com Sucesso!!!!!!!");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void excluir() {
        String sql = "DELETE FROM cliente WHERE id=?";
        int confirma = JOptionPane.showConfirmDialog(null, "Deseja Realmente Excluir este Dado?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            try {
                pst = con.prepareStatement(sql);
                pst.setString(1, txtClieId.getText());
                int op = pst.executeUpdate();
                if (op > 0) {
                    JOptionPane.showMessageDialog(null, "Excluido com Sucesso!");
                }

                desabilitarCampos();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro tente de novo, e de novo terceira"+e);
		
            }
        }
    }
}
