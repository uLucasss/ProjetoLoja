package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexaoDAO {
    
    // Configuração (substitua pelos seus valores)
    private static final String URL = "jdbc:mysql://localhost/lojadb";
    private static final String USER = "root";
    private static final String PASSWORD = "12345";
    private static final String SSL = "?useSSL=false";
    
    public Connection connectDB() {
        try {
            // Monta a URL completa
            String urlCompleta = URL + SSL;
            
            // Estabelece a conexão
            return DriverManager.getConnection(urlCompleta, USER, PASSWORD);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Erro ao conectar ao banco de dados:\n" + e.getMessage(),
                "Erro de Conexão",
                JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public void desconectar(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao desconectar: " + ex.getMessage());
        }
    }
}
