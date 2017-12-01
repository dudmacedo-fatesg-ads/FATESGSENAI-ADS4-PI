package com.computadores.model;

import java.util.List;

/**
 *
 * @author eduardo
 */
public abstract class Cliente implements IEntidade {

    protected Integer codigo;
    protected TipoPessoa tipo;
    protected String telresidencial;
    protected String telcomercial;
    protected String telcelular;
    protected String email;
    protected String senha;
    protected List<Endereco> enderecos;
    protected boolean administrador = false;

    protected Cliente(TipoPessoa tipo) {
        this.tipo = tipo;
    }

    public Cliente(int codigo, TipoPessoa tipo) {
        this.codigo = codigo;
        this.tipo = tipo;
    }
    
    public static Cliente getNovoCliente(TipoPessoa tipo) {
        switch (tipo) {
            case FISICA:
                return new PessoaFisica();
            case JURIDICA:
                return new PessoaJuridica();
            default:
                return null;
        }
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public TipoPessoa getTipo() {
        return tipo;
    }

    public void setTipo(TipoPessoa tipo) {
        this.tipo = tipo;
    }

    public String getTelresidencial() {
        return telresidencial;
    }

    public void setTelresidencial(String telresidencial) {
        this.telresidencial = telresidencial;
    }

    public String getTelcomercial() {
        return telcomercial;
    }

    public void setTelcomercial(String telcomercial) {
        this.telcomercial = telcomercial;
    }

    public String getTelcelular() {
        return telcelular;
    }

    public void setTelcelular(String telcelular) {
        this.telcelular = telcelular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }
    
    @Override
    public abstract String toString();
}
