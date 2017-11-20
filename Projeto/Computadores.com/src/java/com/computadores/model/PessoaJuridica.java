package com.computadores.model;

/**
 *
 * @author eduardo
 */
public class PessoaJuridica extends Cliente {

    private long cnpj;
    private String inscricaoestadual;
    private Estado estadoemissor;
    private String razaoSocial;
    
    public PessoaJuridica() {
        super(TipoPessoa.JURIDICA);
    }
    
    public PessoaJuridica(int codigo) {
        super(codigo, TipoPessoa.JURIDICA);
    }

    @Override
    public boolean validar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public long getCnpj() {
        return cnpj;
    }

    public void setCnpj(long cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoestadual() {
        return inscricaoestadual;
    }

    public void setInscricaoestadual(String inscricaoestadual) {
        this.inscricaoestadual = inscricaoestadual;
    }

    public Estado getEstadoemissor() {
        return estadoemissor;
    }

    public void setEstadoemissor(Estado estadoemissor) {
        this.estadoemissor = estadoemissor;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

}
