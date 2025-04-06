package model;

import java.util.List;

public class Usuario {
    private int id;
    private String nome;
    private String cpf;
    private String endereco;
    private String login;
    private String senha;
    private int cargoId;
    private Cargo cargo;
    private List<Telefone> telefones;
    private List<Venda> vendas;
    private List<Salario> salarios;

    // Construtores
    public Usuario() {
    }

    public Usuario(int id, String nome, String cpf, String endereco, String login, String senha, int cargoId, Cargo cargo, List<Telefone> telefones, List<Venda> vendas, List<Salario> salarios) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.login = login;
        this.senha = senha;
        this.cargoId = cargoId;
        this.cargo = cargo;
        this.telefones = telefones;
        this.vendas = vendas;
        this.salarios = salarios;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getCargoId() {
        return cargoId;
    }

    public void setCargoId(int cargoId) {
        this.cargoId = cargoId;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    public List<Salario> getSalarios() {
        return salarios;
    }

    public void setSalarios(List<Salario> salarios) {
        this.salarios = salarios;
    }
    
    
}
