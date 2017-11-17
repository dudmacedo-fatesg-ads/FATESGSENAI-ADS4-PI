package com.computadores.model;

/**
 *
 * @author eduardo
 */
public enum TipoPessoa {
    FISICA("Pessoa Física"),
    JURIDICA("Pessoa Jurídica");

    public final String descricao;

    private TipoPessoa(String descricao) {
        this.descricao = descricao;
    }

    public static TipoPessoa getById(char id) {
        switch (id) {
            case 'F':
                return FISICA;
            case 'J':
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
