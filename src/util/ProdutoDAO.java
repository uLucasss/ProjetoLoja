package util;

import java.util.ArrayList;
import java.util.List;
import model.Produto;

public class ProdutoDAO {
    private final ConexaoDAO conexao = new ConexaoDAO();
    
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
    
}
    

