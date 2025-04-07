package model;

import java.util.List;

public class Cliente {
    private static int contadorId = 1;
    private int id;
    private String nome;
    private String cpf;
    private String endereco;
    private String telefone;

    // Contrutores
    public Cliente() {
    }

    
    public Cliente(String nome, String cpf, String endereco, String telefone) {
        this.id = contadorId++;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
     
}
