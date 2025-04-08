package util;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Estoque;

public class ProdutoDAO {
    private final ConexaoDAO conexao = new ConexaoDAO();
    
// Método    
    public boolean cadastrarProduto(Produto produto) {
        Connection conn = conexao.connectDB();
        boolean sucesso = false;

        try {
            conn.setAutoCommit(false); // Inicia transação

            // 1. Primeiro, inserir o Produto (sem estoque)
            String sqlProduto = "INSERT INTO produto (nome, preco) VALUES (?, ?)";

            try (PreparedStatement pstmtProduto = conn.prepareStatement(sqlProduto, Statement.RETURN_GENERATED_KEYS)) {
                pstmtProduto.setString(1, produto.getNome());
                pstmtProduto.setDouble(2, produto.getPreco());

                int affectedRows = pstmtProduto.executeUpdate();

                if (affectedRows > 0) {
                    // Pega o ID gerado para o produto
                    try (ResultSet generatedKeys = pstmtProduto.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int produtoId = generatedKeys.getInt(1);
                            produto.setId(produtoId); // Atualiza o ID no objeto

                            // 2. Agora, insere o Estoque associado
                            Estoque estoque = produto.getEstoque();
                            if (estoque != null) {
                                String sqlEstoque = "INSERT INTO estoque (produto_id, quantidade_estoque) VALUES (?, ?)";
                                try (PreparedStatement pstmtEstoque = conn.prepareStatement(sqlEstoque)) {
                                    pstmtEstoque.setInt(1, produtoId);
                                    pstmtEstoque.setInt(2, estoque.getQuantidadeEstoque());
                                    pstmtEstoque.executeUpdate();
                                }
                            }
                            sucesso = true;
                        }
                    }
                }
            }

            if (sucesso) {
                conn.commit(); // Confirma as alterações
            } else {
                conn.rollback(); // Desfaz em caso de erro
            }
        } catch (SQLException e) {
            try {
                conn.rollback(); // Desfaz em caso de erro
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + e.getMessage(),
                    "Erro no Banco de Dados", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conn.setAutoCommit(true); // Restaura o modo padrão
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conexao.desconectar(conn);
        }
        return sucesso;
    }
    
// Método
    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        Connection conn = conexao.connectDB();
        String sql = "SELECT p.id, p.nome, p.preco, e.quantidade_estoque " +
                     "FROM produto p LEFT JOIN estoque e ON p.id = e.produto_id";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getDouble("preco"));

                Estoque estoque = new Estoque();
                estoque.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
                produto.setEstoque(estoque);

                produtos.add(produto);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage(),
                    "Erro no Banco de Dados", JOptionPane.ERROR_MESSAGE);
        } finally {
            conexao.desconectar(conn);
        }
        return produtos;
    }
    
// Método   
    public boolean excluirProduto(int id) {
        Connection conn = conexao.connectDB();
        String sql = "DELETE FROM produto WHERE id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir produto: " + e.getMessage(),
                    "Erro no Banco de Dados", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            conexao.desconectar(conn);
        }
    }
    
// Método    
    public Produto buscarProdutoPorId(int id) {
    Connection conn = conexao.connectDB();
    String sql = "SELECT p.id, p.nome, p.preco, e.quantidade_estoque " +
                 "FROM produto p LEFT JOIN estoque e ON p.id = e.produto_id " +
                 "WHERE p.id = ?";
    
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, id);
        
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getDouble("preco"));
                
                Estoque estoque = new Estoque();
                estoque.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
                produto.setEstoque(estoque);
                
                return produto;
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao buscar produto: " + e.getMessage(),
                "Erro no Banco de Dados", JOptionPane.ERROR_MESSAGE);
    } finally {
        conexao.desconectar(conn);
    }
    return null;
}
    
// Método    
    public boolean atualizarProduto(Produto produto) {
    Connection conn = conexao.connectDB();
    boolean sucesso = false;
    
    try {
        conn.setAutoCommit(false); // Inicia transação

        // 1. Atualiza o Produto (sem estoque)
        String sqlProduto = "UPDATE produto SET nome = ?, preco = ? WHERE id = ?";
        try (PreparedStatement pstmtProduto = conn.prepareStatement(sqlProduto)) {
            pstmtProduto.setString(1, produto.getNome());
            pstmtProduto.setDouble(2, produto.getPreco());
            pstmtProduto.setInt(3, produto.getId());
            
            int affectedRows = pstmtProduto.executeUpdate();
            
            if (affectedRows > 0) {
                // 2. Atualiza o Estoque associado
                Estoque estoque = produto.getEstoque();
                if (estoque != null) {
                    String sqlEstoque = "UPDATE estoque SET quantidade_estoque = ? WHERE produto_id = ?";
                    try (PreparedStatement pstmtEstoque = conn.prepareStatement(sqlEstoque)) {
                        pstmtEstoque.setInt(1, estoque.getQuantidadeEstoque());
                        pstmtEstoque.setInt(2, produto.getId());
                        pstmtEstoque.executeUpdate();
                    }
                }
                sucesso = true;
            }
        }
        
        if (sucesso) {
            conn.commit(); // Confirma as alterações
        } else {
            conn.rollback(); // Desfaz em caso de erro
        }
    } catch (SQLException e) {
        try {
            conn.rollback(); // Desfaz em caso de erro
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Erro ao atualizar produto: " + e.getMessage(),
                "Erro no Banco de Dados", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            conn.setAutoCommit(true); // Restaura o modo padrão
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conexao.desconectar(conn);
    }
    return sucesso;
}
    /*
    private static List<Produto> listaProdutos = new ArrayList<>();

    public void adicionarProduto(Produto produto) {
        listaProdutos.add(produto);
    }

    public List<Produto> listarProdutos() {
        return listaProdutos;
    }
    
    public void removerProduto(int indice) {
        if (indice >= 0 && indice < listaProdutos.size()) {
            listaProdutos.remove(indice);
        }
    } 
    
    public static List<Produto> getListaProdutos() {
        return listaProdutos;
    }
    */
}
    

