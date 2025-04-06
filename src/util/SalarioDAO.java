package util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Salario;

public class SalarioDAO {
    private final ConexaoDAO conexao = new ConexaoDAO();

    // CREATE (Inserir Salário)
    public void inserirSalario(Salario salario) {
        String sql = "INSERT INTO salario (valor, dataAjuste, usuarioId) VALUES (?, ?, ?)";
        try (Connection conn = conexao.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, salario.getValor());
            stmt.setDate(2, new java.sql.Date(salario.getDataAjuste().getTime()));
            stmt.setInt(3, salario.getUsuarioId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salário cadastrado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir salário: " + e.getMessage());
        }
    }

    // READ (Listar Salários)
    public List<Salario> listarSalarios() {
        List<Salario> lista = new ArrayList<>();
        String sql = "SELECT * FROM salario";
        try (Connection conn = conexao.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Salario salario = new Salario();
                salario.setId(rs.getInt("id"));
                salario.setValor(rs.getDouble("valor"));
                salario.setDataAjuste(rs.getDate("dataAjuste"));
                salario.setUsuarioId(rs.getInt("usuarioId"));
                lista.add(salario);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar salários: " + e.getMessage());
        }
        return lista;
    }

    // UPDATE (Atualizar Salário)
    public void atualizarSalario(Salario salario) {
        String sql = "UPDATE salario SET valor = ?, dataAjuste = ?, usuarioId = ? WHERE id = ?";
        try (Connection conn = conexao.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, salario.getValor());
            stmt.setDate(2, new java.sql.Date(salario.getDataAjuste().getTime()));
            stmt.setInt(3, salario.getUsuarioId());
            stmt.setInt(4, salario.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salário atualizado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar salário: " + e.getMessage());
        }
    }

    // DELETE (Excluir Salário)
    public void excluirSalario(int id) {
        String sql = "DELETE FROM salario WHERE id = ?";
        try (Connection conn = conexao.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salário excluído com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir salário: " + e.getMessage());
        }
    }
}
