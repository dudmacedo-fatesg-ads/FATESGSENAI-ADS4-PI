package com.computadores.model;

/**
 *
 * @author eduardo
 */
public class Marca implements IEntidade {

    private int codigo;
    private String nome;
    
    public Marca() {
        
    }
    
    public Marca(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public boolean validar() {
        return (nome.length() > 0);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
