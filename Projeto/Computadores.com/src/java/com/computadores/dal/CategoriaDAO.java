package com.computadores.dal;

import com.computadores.error.DatabaseException;
import com.computadores.model.Categoria;
import com.computadores.util.DBFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eduardo
 */
public class CategoriaDAO implements IEntidadeDAO<Categoria> {

    Connection cnx = DBFactory.getConnection();

    @Override
    public String getTabela() {
        return "categoria";
    }

    @Override
    public void create(Categoria obj) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Categoria retrieve(int key) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Categoria obj) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Categoria obj) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Categoria> list() throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Iterator<Categoria> list(Categoria pai) {
        String sql = "SELECT * FROM categoria WHERE "
                + ((pai == null) ? "pai IS NULL" : "pai = ?");

        try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            if (pai != null) {
                pstmt.setInt(1, pai.getCodigo());
            }

            ResultSet rs = pstmt.executeQuery();

            List<Categoria> retorno = new ArrayList<>();
            while (rs.next()) {
                Categoria cat = new Categoria();

                cat.setCodigo(rs.getInt("codigo"));
                cat.setNome(rs.getString("nome"));

                retorno.add(cat);
            }
            System.out.println(retorno.size());
            return retorno.iterator();
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
