package com.computadores.model;

import java.util.Date;

/**
 *
 * @author eduardo
 */
public class PessoaFisica extends Cliente {

    private long cpf;
    private int rg;
    private String nome;
    private Date dtNasc;
    
    public PessoaFisica() {
        super(TipoPessoa.FISICA);
    }
    
    public PessoaFisica(int codigo) {
        super(codigo, TipoPessoa.FISICA);
    }

    @Override
    public boolean validar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public int getRg() {
        return rg;
    }

    public void setRg(int rg) {
        this.rg = rg;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(Date dtNasc) {
        this.dtNasc = dtNasc;
    }

}
