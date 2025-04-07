package model;

public class Produto {
    private static int contadorId = 1;
    private int id;
    private String nome;
    private double preco;
    private int estoque;


    // Construtores
    public Produto() {
    }

    public Produto(String nome, double preco, int estoque) {
        this.id = contadorId++;
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
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

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }  
    
}
