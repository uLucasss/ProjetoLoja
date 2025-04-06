package model;

public class Telefone {
    private int id;
    private String numero;
    private int clienteId;
    private int usuarioId;
    private Cliente cliente;
    private Usuario usuario;
    
    // Construtores
    public Telefone() {
    }

    public Telefone(int id, String numero, int clienteId, int usuarioId, Cliente cliente, Usuario usuario) {
        this.id = id;
        this.numero = numero;
        this.clienteId = clienteId;
        this.usuarioId = usuarioId;
        this.cliente = cliente;
        this.usuario = usuario;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
