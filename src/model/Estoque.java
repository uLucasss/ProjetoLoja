package model;

public class Estoque {
    private int id;
    private int quantidadeEstoque;
    private Produto produto; // Referência ao Produto

    public Estoque() {
        // Construtor padrão
    }

    public Estoque(int quantidadeEstoque, Produto produto) {
        this.quantidadeEstoque = quantidadeEstoque;
        this.produto = produto;
    }


    // Getters e Setters
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

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
