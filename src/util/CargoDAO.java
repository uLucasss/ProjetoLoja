package util;


import model.Cargo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CargoDAO {
    private final ConexaoDAO conexao = new ConexaoDAO();

    // CREATE (Inserir Cargo)
    public void inserirCargo(Cargo cargo) {
        String sql = "INSERT INTO cargo (nome) VALUES (?)";
        try (Connection conn = conexao.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cargo.getNome());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cargo cadastrado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir cargo: " + e.getMessage());
        }
    }

    // READ (Listar Cargos)
    public List<Cargo> listarCargos() {
        List<Cargo> lista = new ArrayList<>();
        String sql = "SELECT * FROM cargo";
        try (Connection conn = conexao.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Cargo cargo = new Cargo();
                cargo.setId(rs.getInt("id"));
                cargo.setNome(rs.getString("nome"));
                lista.add(cargo);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar cargos: " + e.getMessage());
        }
        return lista;
    }

    // UPDATE (Atualizar Cargo)
    public void atualizarCargo(Cargo cargo) {
        String sql = "UPDATE cargo SET nome = ? WHERE id = ?";
        try (Connection conn = conexao.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cargo.getNome());
            stmt.setInt(2, cargo.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cargo atualizado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar cargo: " + e.getMessage());
        }
    }

    // DELETE (Excluir Cargo)
    public void excluirCargo(int id) {
        String sql = "DELETE FROM cargo WHERE id = ?";
        try (Connection conn = conexao.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cargo excluído com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir cargo: " + e.getMessage());
        }
    }
}
