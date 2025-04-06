package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import model.Venda;

public class VendaDAO {
    
    private final ConexaoDAO conexaoDAO = new ConexaoDAO();
    
    // CREATE (Inserir Venda)
    public void inserirVenda(Venda venda) {
        String sql = "INSERT INTO venda (data_venda, total, cliente_id, usuario_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = conexaoDAO.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(venda.getDataVenda().getTime()));
            stmt.setDouble(2, venda.getTotal());
            stmt.setInt(3, venda.getClienteId());
            stmt.setInt(4, venda.getUsuarioId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Venda cadastrada com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir venda: " + e.getMessage());
        }
    }
    
    // READ (Listar Venda)
    public List<Venda> listarVenda() {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM venda";
        try (Connection conn = conexaoDAO.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Venda venda = new Venda();
                venda.setId(rs.getInt("id"));
                venda.setDataVenda(new Date(rs.getDate("data_venda").getTime()));
                venda.setTotal(rs.getDouble("total"));
                venda.setClienteId(rs.getInt("cliente_id"));
                venda.setUsuarioId(rs.getInt("usuario_id"));
                vendas.add(venda);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar venda: " + e.getMessage());
        }
        return vendas;
    }
    
    // UPDATE (Atualizar Venda)
    public void atualizarVenda(Venda venda) {
        String sql = "UPDATE venda SET data_venda = ?, total = ?, cliente_id = ?, usuario_id = ? WHERE id = ?";
        try (Connection conn = conexaoDAO.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(venda.getDataVenda().getTime()));
            stmt.setDouble(2, venda.getTotal());
            stmt.setInt(3, venda.getClienteId());
            stmt.setInt(4, venda.getUsuarioId());
            stmt.setInt(5, venda.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Venda atualizada com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar venda: " + e.getMessage());
        }
    }
    
    // DELETE (Excluir Venda)
    public void excluirVenda(int id) {
        String sql = "DELETE FROM venda WHERE id = ?";
        try (Connection conn = conexaoDAO.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Venda excluída com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir venda: " + e.getMessage());
        }
    }
}