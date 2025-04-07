package util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Cliente;

public class ClienteDAO {
    private final ConexaoDAO conexao = new ConexaoDAO();

    /*===================================MÉTODOS PROVISÓRIOS===================================*/
    /*===================================MÉTODOS PROVISÓRIOS===================================*/
    private static List<Cliente> listaClientes = new ArrayList<>();

    public void adicionarCliente(Cliente cliente) {
        listaClientes.add(cliente);
    }

    public List<Cliente> listarClientes() {
        return listaClientes;
    }
    
    public void removerCliente(int indice) {
    if (indice >= 0 && indice < listaClientes.size()) {
        listaClientes.remove(indice);
        }
    }
    
    public static List<Cliente> getListaClientes() {
        return listaClientes;
    }

    /*===================================MÉTODOS PROVISÓRIOS===================================*/
    /*===================================MÉTODOS PROVISÓRIOS===================================*/
    
    // CREATE (Inserir Cliente)
    public void inserirCliente(Cliente cliente) {
        String sql = "INSERT INTO cliente (nome, cpf, endereco) VALUES (?, ?, ?)";
        try (Connection conn = conexao.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getEndereco());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir cliente: " + e.getMessage());
        }
    }
    
   
    // READ (Listar Clientes)
    public List<Cliente> listarClientes222222222() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (Connection conn = conexao.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEndereco(rs.getString("endereco"));
                lista.add(cliente);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes: " + e.getMessage());
        }
        return lista;
    }

    // UPDATE (Atualizar Cliente)
    public void atualizarCliente(Cliente cliente) {
        String sql = "UPDATE cliente SET nome = ?, cpf = ?, endereco = ? WHERE id = ?";
        try (Connection conn = conexao.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getEndereco());
            stmt.setInt(4, cliente.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    // DELETE (Excluir Cliente)
    public void excluirCliente(int id) {
        String sql = "DELETE FROM cliente WHERE id = ?";
        try (Connection conn = conexao.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir cliente: " + e.getMessage());
        }
    }
}
