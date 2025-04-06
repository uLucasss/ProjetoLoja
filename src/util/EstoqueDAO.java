package util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Estoque;

public class EstoqueDAO {
    private final ConexaoDAO conexao = new ConexaoDAO();

    // CREATE (Inserir Estoque)
    public void inserirEstoque(Estoque estoque) {
        String sql = "INSERT INTO estoque (quantidadeEstoque, produtoId) VALUES (?, ?)";
        try (Connection conn = conexao.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, estoque.getQuantidadeEstoque());
            stmt.setInt(2, estoque.getProdutoId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Estoque cadastrado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir estoque: " + e.getMessage());
        }
    }

    // READ (Listar Estoques)
    public List<Estoque> listarEstoques() {
        List<Estoque> lista = new ArrayList<>();
        String sql = "SELECT * FROM estoque";
        try (Connection conn = conexao.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Estoque estoque = new Estoque();
                estoque.setId(rs.getInt("id"));
                estoque.setQuantidadeEstoque(rs.getInt("quantidadeEstoque"));
                estoque.setProdutoId(rs.getInt("produtoId"));
                lista.add(estoque);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar estoques: " + e.getMessage());
        }
        return lista;
    }

    // UPDATE (Atualizar Estoque)
    public void atualizarEstoque(Estoque estoque) {
        String sql = "UPDATE estoque SET quantidadeEstoque = ?, produtoId = ? WHERE id = ?";
        try (Connection conn = conexao.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, estoque.getQuantidadeEstoque());
            stmt.setInt(2, estoque.getProdutoId());
            stmt.setInt(3, estoque.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Estoque atualizado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar estoque: " + e.getMessage());
        }
    }

    // DELETE (Excluir Estoque)
    public void excluirEstoque(int id) {
        String sql = "DELETE FROM estoque WHERE id = ?";
        try (Connection conn = conexao.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Estoque excluído com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir estoque: " + e.getMessage());
        }
    }
}