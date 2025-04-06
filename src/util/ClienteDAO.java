package util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Cliente;
import model.Telefone;

public class ClienteDAO {
    private final ConexaoDAO conexao = new ConexaoDAO();

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
    
    public void inserirClienteComTelefones(Cliente cliente) {
        String sqlCliente = "INSERT INTO cliente (nome, cpf, endereco) VALUES (?, ?, ?)";
        String sqlTelefone = "INSERT INTO telefone (numero, cliente_id) VALUES (?, ?)";
        
        try (Connection conn = conexao.connectDB()) {
            conn.setAutoCommit(false); // Inicia transação

            // 1. Insere o Cliente e obtém o ID gerado
            try (PreparedStatement stmtCliente = conn.prepareStatement(sqlCliente, Statement.RETURN_GENERATED_KEYS)) {
                stmtCliente.setString(1, cliente.getNome());
                stmtCliente.setString(2, cliente.getCpf());
                stmtCliente.setString(3, cliente.getEndereco());
                stmtCliente.executeUpdate();

                // Obtém o ID do cliente inserido
                ResultSet rs = stmtCliente.getGeneratedKeys();
                int clienteId = 0;
                if (rs.next()) {
                    clienteId = rs.getInt(1);
                }

                // 2. Insere os telefones
                if (cliente.getTelefones() != null && !cliente.getTelefones().isEmpty()) {
                    try (PreparedStatement stmtTelefone = conn.prepareStatement(sqlTelefone)) {
                        for (Telefone telefone : cliente.getTelefones()) {
                            stmtTelefone.setString(1, telefone.getNumero());
                            stmtTelefone.setInt(2, clienteId);
                            stmtTelefone.addBatch(); // Otimiza inserções em lote
                        }
                        stmtTelefone.executeBatch(); // Executa todas as inserções
                    }
                }

                conn.commit(); // Confirma a transação
                JOptionPane.showMessageDialog(null, "Cliente e telefones cadastrados com sucesso!");
            }
            } catch (SQLException e) {
                try {
                    conexao.connectDB().rollback(); // Em caso de erro, desfaz a transação
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente: " + e.getMessage());
            }
        }


    // READ (Listar Clientes)
    public List<Cliente> listarClientes() {
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
