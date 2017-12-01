package com.computadores.model;

/**
 *
 * @author eduardo
 */
public enum TipoPessoa {
    FISICA("F", "Pessoa Física"),
    JURIDICA("J", "Pessoa Jurídica");

    public final String descricao;
    public final String id;

    private TipoPessoa(String id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
    
    public String getId() {
        return id;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public static TipoPessoa getById(String id) {
        switch (id) {
            case "F":
                return FISICA;
            case "J":
                return JURIDICA;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return descricao;
    }
}
