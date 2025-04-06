package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Usuario;

public class UsuarioDAO {
    
    private final ConexaoDAO conexaoDAO = new ConexaoDAO();
    
    // CREATE (Inserir Usuário)
    public void inserirUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, cpf, endereco, login, senha, cargo_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = conexaoDAO.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getEndereco());
            stmt.setString(4, usuario.getLogin());
            stmt.setString(5, usuario.getSenha());
            stmt.setInt(6, usuario.getCargoId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir usuário: " + e.getMessage());
        }
    }
    
    // READ (Listar Usuário)
    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try (Connection conn = conexaoDAO.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEndereco(rs.getString("endereco"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setCargoId(rs.getInt("cargo_id"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar usuário: " + e.getMessage());
        }
        return usuarios;
    }
    
    // UPDATE (Atualizar Usuário)
    public void atualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuario SET nome = ?, cpf = ?, endereco = ?, login = ?, senha = ?, cargo_id = ? WHERE id = ?";
        try (Connection conn = conexaoDAO.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getEndereco());
            stmt.setString(4, usuario.getLogin());
            stmt.setString(5, usuario.getSenha());
            stmt.setInt(6, usuario.getCargoId());
            stmt.setInt(7, usuario.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar usuário: " + e.getMessage());
        }
    }
    
    // DELETE (Excluir Usuário)
    public void excluirUsuario(int id) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        try (Connection conn = conexaoDAO.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir usuário: " + e.getMessage());
        }
    }
}
