package com.computadores.dal;

import com.computadores.error.DatabaseException;
import com.computadores.model.Marca;
import com.computadores.util.DBFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eduardo
 */
public class MarcaDAO implements IEntidadeDAO<Marca> {

    Connection cnx = DBFactory.getConnection();

    @Override
    public String getTabela() {
        return "marca";
    }

    @Override
    public void create(Marca obj) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Marca retrieve(int key) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Marca obj) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Marca obj) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Marca> list() throws DatabaseException {
        String sql = String.format("SELECT * FROM %s ORDER BY nome", getTabela());

        try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            List<Marca> retorno = new ArrayList<>();
            while (rs.next()) {
                Marca mar = new Marca();

                mar.setCodigo(rs.getInt("codigo"));
                mar.setNome(rs.getString("nome"));

                retorno.add(mar);
            }

            return retorno;
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
