package util;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO {
    private final ConexaoDAO conexao = new ConexaoDAO();

    public boolean cadastrarCliente(Cliente cliente) {
        Connection conn = conexao.connectDB();
        String sql = "INSERT INTO cliente (nome, cpf, endereco, telefone) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getCpf());
            pstmt.setString(3, cliente.getEndereco());
            pstmt.setString(4, cliente.getTelefone());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        cliente.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente: " + e.getMessage(),
                    "Erro no Banco de Dados", JOptionPane.ERROR_MESSAGE);
        } finally {
            conexao.desconectar(conn);
        }
        return false;
    }

    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        Connection conn = conexao.connectDB();
        String sql = "SELECT * FROM cliente";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setTelefone(rs.getString("telefone"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes: " + e.getMessage(),
                    "Erro no Banco de Dados", JOptionPane.ERROR_MESSAGE);
        } finally {
            conexao.desconectar(conn);
        }
        return clientes;
    }
    
    public boolean excluirCliente(int id) {
        Connection conn = conexao.connectDB();
        String sql = "DELETE FROM cliente WHERE id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir cliente: " + e.getMessage(),
                    "Erro no Banco de Dados", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            conexao.desconectar(conn);
        }
    }
    
    public Cliente buscarClientePorId(int id) {
        Connection conn = conexao.connectDB();
        String sql = "SELECT * FROM cliente WHERE id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getInt("id"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setCpf(rs.getString("cpf"));
                    cliente.setEndereco(rs.getString("endereco"));
                    cliente.setTelefone(rs.getString("telefone"));
                    return cliente;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar cliente: " + e.getMessage(),
                    "Erro no Banco de Dados", JOptionPane.ERROR_MESSAGE);
        } finally {
            conexao.desconectar(conn);
        }
            return null;
        }
    public boolean atualizarCliente(Cliente cliente) {
        Connection conn = conexao.connectDB();
        String sql = "UPDATE cliente SET nome = ?, cpf = ?, endereco = ?, telefone = ? WHERE id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getCpf());
            pstmt.setString(3, cliente.getEndereco());
            pstmt.setString(4, cliente.getTelefone());
            pstmt.setInt(5, cliente.getId());
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar cliente: " + e.getMessage(),
                    "Erro no Banco de Dados", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            conexao.desconectar(conn);
        }
    }
   

    /*===================================MÉTODOS PROVISÓRIOS===================================*/
    /*private static List<Cliente> listaClientes = new ArrayList<>();

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
    */
       
}
