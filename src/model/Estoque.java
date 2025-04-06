package model;

public class Estoque {
    private int id;
    private int quantidadeEstoque;
    private int produtoId;
    private Produto produto;

    // Construtores
    public Estoque() {
    }

    public Estoque(int id, int quantidadeEstoque, int produtoId, Produto produto) {
        this.id = id;
        this.quantidadeEstoque = quantidadeEstoque;
        this.produtoId = produtoId;
        this.produto = produto;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

}
