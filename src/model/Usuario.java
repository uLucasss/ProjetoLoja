package model;

public class Usuario {
    private static int contadorId = 1; // Contador para gerar IDs automaticamente
    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private String endereco;
    private String login;
    private String senha;
    private String cargo;
 
    // Construtores
    public Usuario() {
    }

    public Usuario(String nome, String cpf, String telefone, String endereco, String login, String senha, String cargo) {
        this.id = contadorId++; // Gera um ID único
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.login = login;
        this.senha = senha;
        this.cargo = cargo;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
}
