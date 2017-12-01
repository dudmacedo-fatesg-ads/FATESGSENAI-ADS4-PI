package com.computadores.model;

/**
 *
 * @author eduardo
 */
public class PessoaJuridica extends Cliente {

    private Long cnpj;
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

    public Long getCnpj() {
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

    @Override
    public String toString() {
        return String.format(
                "%s - %s - %s - %s - %s - %s - %s - %s - %s - %s - %s - %s - %s",
                (cnpj == null) ? "null" : cnpj,
                (inscricaoestadual == null) ? "null" : inscricaoestadual,
                (estadoemissor == null) ? "null" : estadoemissor.getNome(),
                (razaoSocial == null) ? "null" : razaoSocial,
                (codigo == null) ? "null" : codigo,
                (tipo == null) ? "null" : tipo,
                (telresidencial == null) ? "null" : telresidencial,
                (telcomercial == null) ? "null" : telcomercial,
                (telcelular == null) ? "null" : telcelular,
                (email == null) ? "null" : email,
                (senha == null) ? "null" : senha,
                (enderecos == null) ? "null" : enderecos.size() + " endereços",
                (administrador) ? "Administrador" : "Cliente"
        );
    }
}
