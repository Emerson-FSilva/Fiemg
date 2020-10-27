
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
            JOptionPane.showMessageDialog(null,"Conexão Fechada"+ e);
        }
    }
    
}
