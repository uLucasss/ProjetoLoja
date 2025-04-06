package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.VendaProduto;

public class VendaProdutoDAO {
    
    private final ConexaoDAO conexaoDAO = new ConexaoDAO();
    
    public void inserir(VendaProduto vendaProduto) {
        String sql = "INSERT INTO venda_produto (venda_id, produto_id, quantidade) VALUES (?, ?, ?)";
        try (Connection conn = conexaoDAO.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vendaProduto.getVendaId());
            stmt.setInt(2, vendaProduto.getProdutoId());
            stmt.setInt(3, vendaProduto.getQuantidade());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Venda cadastrada com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir venda: " + e.getMessage());
        }
    }
    
    public List<VendaProduto> listar() {
        List<VendaProduto> vendaProdutos = new ArrayList<>();
        String sql = "SELECT * FROM venda_produto";
        try (Connection conn = conexaoDAO.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                VendaProduto vendaProduto = new VendaProduto();
                vendaProduto.setVendaId(rs.getInt("venda_id"));
                vendaProduto.setProdutoId(rs.getInt("produto_id"));
                vendaProduto.setQuantidade(rs.getInt("quantidade"));
                vendaProdutos.add(vendaProduto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar venda: " + e.getMessage());
        }
        return vendaProdutos;
    }
    
    public void atualizar(VendaProduto vendaProduto) {
        String sql = "UPDATE venda_produto SET quantidade = ? WHERE venda_id = ? AND produto_id = ?";
        try (Connection conn = conexaoDAO.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vendaProduto.getQuantidade());
            stmt.setInt(2, vendaProduto.getVendaId());
            stmt.setInt(3, vendaProduto.getProdutoId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Venda atualizada com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar venda: " + e.getMessage());
        }
    }
    
    public void deletar(int vendaId, int produtoId) {
        String sql = "DELETE FROM venda_produto WHERE venda_id = ? AND produto_id = ?";
        try (Connection conn = conexaoDAO.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vendaId);
            stmt.setInt(2, produtoId);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Venda excluída com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir venda: " + e.getMessage());
        }
    }
}