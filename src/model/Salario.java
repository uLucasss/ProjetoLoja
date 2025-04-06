package model;

import java.util.Date;

public class Salario {
    private int id;
    private double valor;
    private Date dataAjuste;
    private int usuarioId;
    private Usuario usuario;

    // Construtores
    public Salario() {
    }

    public Salario(int id, double valor, Date dataAjuste, int usuarioId, Usuario usuario) {
        this.id = id;
        this.valor = valor;
        this.dataAjuste = dataAjuste;
        this.usuarioId = usuarioId;
        this.usuario = usuario;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDataAjuste() {
        return dataAjuste;
    }

    public void setDataAjuste(Date dataAjuste) {
        this.dataAjuste = dataAjuste;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
