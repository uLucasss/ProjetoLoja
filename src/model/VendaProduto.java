package model;

public class VendaProduto {
    private int vendaId;
    private int produtoId;
    private Venda venda;
    private Produto produto;
    private int quantidade;

    // Construtores
    public VendaProduto() {
    }

    public VendaProduto(int vendaId, int produtoId, Venda venda, Produto produto, int quantidade) {
        this.vendaId = vendaId;
        this.produtoId = produtoId;
        this.venda = venda;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    // Getters and Setters
    public int getVendaId() {
        return vendaId;
    }

    public void setVendaId(int vendaId) {
        this.vendaId = vendaId;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    
    
}
