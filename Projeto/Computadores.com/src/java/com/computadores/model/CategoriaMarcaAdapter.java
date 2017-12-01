package com.computadores.model;

/**
 *
 * @author eduardo
 */
public class CategoriaMarcaAdapter extends Categoria {

    private final Marca marca;

    public CategoriaMarcaAdapter(Marca marca) {
        this.marca = marca;
    }

    @Override
    public int getCodigo() {
        return marca.getCodigo();
    }

    @Override
    public void setCodigo(int codigo) {
        marca.setCodigo(codigo);
    }

    @Override
    public String getNome() {
        return marca.getNome();
    }

    @Override
    public void setNome(String nome) {
        marca.setNome(nome);
    }
}
