package com.computadores.dal;

import com.computadores.error.DatabaseException;
import com.computadores.model.Estado;
import com.computadores.util.DBFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eduardo
 */
public class EstadoDAO {

    Connection cnx = DBFactory.getConnection();

    public String getTabela() {
        return "end_estado";
    }

    public List<Estado> listAll() throws DatabaseException {
        String sql = "SELECT * FROM " + getTabela();

        try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            List<Estado> retorno = new ArrayList<>();
            while (rs.next()) {
                Estado est = new Estado(rs.getInt("codigo"), rs.getString("nome"), rs.getString("sigla"));
                retorno.add(est);
            }

            retorno.sort((Estado o1, Estado o2) -> o1.getSigla().compareTo(o2.getSigla()));
            return retorno;
        } catch (SQLException ex) {
            Logger.getLogger(EstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
