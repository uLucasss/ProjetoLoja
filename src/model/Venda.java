package model;

import java.util.Date;
import java.util.List;

public class Venda {
    private int id;
    private Date dataVenda;
    private double total;
    private int clienteId;
    private int usuarioId;
    private Cliente cliente;
    private Usuario usuario;
    private List<VendaProduto> vendaProdutos;

    // Construtores
    public Venda() {
    }

    public Venda(int id, Date dataVenda, double total, int clienteId, int usuarioId, Cliente cliente, Usuario usuario, List<VendaProduto> vendaProdutos) {
        this.id = id;
        this.dataVenda = dataVenda;
        this.total = total;
        this.clienteId = clienteId;
        this.usuarioId = usuarioId;
        this.cliente = cliente;
        this.usuario = usuario;
        this.vendaProdutos = vendaProdutos;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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

    public List<VendaProduto> getVendaProdutos() {
        return vendaProdutos;
    }

    public void setVendaProdutos(List<VendaProduto> vendaProdutos) {
        this.vendaProdutos = vendaProdutos;
    }
    
    
}
