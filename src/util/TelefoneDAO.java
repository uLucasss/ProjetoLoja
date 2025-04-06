package util;

import model.Telefone;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class TelefoneDAO {
    
    private final ConexaoDAO conexaoDAO = new ConexaoDAO();
    
    // CREATE (Inserir Telefone)
    public void inserirTelefone(Telefone telefone) {
        String sql = "INSERT INTO telefone (numero, cliente_id, usuario_id) VALUES (?, ?, ?)";
        try (Connection conn = conexaoDAO.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, telefone.getNumero());
            stmt.setInt(2, telefone.getClienteId());
            stmt.setInt(3, telefone.getUsuarioId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Telefone cadastrado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir telefone: " + e.getMessage());
        }
        
    }
    
    // READ (Listar Telefone)
    public List<Telefone> listarTelefones() {
        List<Telefone> telefones = new ArrayList<>();
        String sql = "SELECT * FROM telefone";
        try (Connection conn = conexaoDAO.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Telefone telefone = new Telefone();
                telefone.setId(rs.getInt("id"));
                telefone.setNumero(rs.getString("numero"));
                telefone.setClienteId(rs.getInt("cliente_id"));
                telefone.setUsuarioId(rs.getInt("usuario_id"));
                telefones.add(telefone);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar telefone: " + e.getMessage());
        }
        return telefones;
    }
    
    // UPDATE (Atualizar Telefone)
    public void atualizarTelefone(Telefone telefone) {
        String sql = "UPDATE telefone SET numero = ?, cliente_id = ?, usuario_id = ? WHERE id = ?";
        try (Connection conn = conexaoDAO.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, telefone.getNumero());
            stmt.setInt(2, telefone.getClienteId());
            stmt.setInt(3, telefone.getUsuarioId());
            stmt.setInt(4, telefone.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Telefone atualizado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar telefone: " + e.getMessage());
        }
    }
    
    // DELETE (Excluir Telefone)
    public void excluirTelefone(int id) {
        String sql = "DELETE FROM telefone WHERE id = ?";
        try (Connection conn = conexaoDAO.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Telefone excluído com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir telefone: " + e.getMessage());
        }
    }
}