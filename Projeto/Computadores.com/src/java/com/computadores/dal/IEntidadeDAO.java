package com.computadores.dal;

import com.computadores.error.DatabaseException;
import java.util.List;

/**
 *
 * @author eduardo
 * @param <T>
 */
public interface IEntidadeDAO<T> {

    public String getTabela();

    public void create(T obj) throws DatabaseException;

    public T retrieve(int key) throws DatabaseException;

    public void update(T obj) throws DatabaseException;

    public void delete(T obj) throws DatabaseException;

    public List<T> list() throws DatabaseException;
}
