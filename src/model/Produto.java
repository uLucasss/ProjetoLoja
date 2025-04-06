package model;

import java.util.List;

public class Produto {
    private int id;
    private String nome;
    private double preco;
    private List<Estoque> estoques;
    private List<VendaProduto> vendaProdutos;

    // Construtores
    public Produto() {
    }

    public Produto(int id, String nome, double preco, List<Estoque> estoques, List<VendaProduto> vendaProdutos) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.estoques = estoques;
        this.vendaProdutos = vendaProdutos;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public List<Estoque> getEstoques() {
        return estoques;
    }

    public void setEstoques(List<Estoque> estoques) {
        this.estoques = estoques;
    }

    public List<VendaProduto> getVendaProdutos() {
        return vendaProdutos;
    }

    public void setVendaProdutos(List<VendaProduto> vendaProdutos) {
        this.vendaProdutos = vendaProdutos;
    }
    
}
