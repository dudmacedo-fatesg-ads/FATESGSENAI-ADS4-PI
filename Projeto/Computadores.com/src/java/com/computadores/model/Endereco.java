package com.computadores.model;

/**
 *
 * @author eduardo
 */
public class Endereco implements IEntidade {

    private Integer codigo;
    private Integer cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private Cidade cidade;
    private Boolean padrao = false;
    private Cliente cliente;

    @Override
    public boolean validar() {
        // Valida a existência do CEP
        if (cep == null || cep <= 0) {
            return false;
        } //
        // Valida o campo Logradouro
        else if (logradouro == null || logradouro.length() == 0 || logradouro.length() > 80) {
            return false;
        } //
        // Valida o campo Complemento
        else if (complemento != null && (complemento.length() == 0 || complemento.length() > 50)) {
            return false;
        } //
        // Valida o campo Bairro
        else if (bairro == null || bairro.length() == 0 || bairro.length() > 80) {
            return false;
        } //
        // Valida o campo Cidade
        else if (cidade == null) {
            return false;
        } //
        // Valida a existência do Cliente
        else if (cliente == null || cliente.getCodigo() <= 0) {
            return false;
        }

        return true;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Integer getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public boolean isPadrao() {
        return padrao;
    }

    public void setPadrao(boolean padrao) {
        this.padrao = padrao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
