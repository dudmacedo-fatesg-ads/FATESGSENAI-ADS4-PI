package com.computadores.dal;

import com.computadores.error.DatabaseException;
import com.computadores.model.Cidade;
import com.computadores.model.Estado;
import com.computadores.util.DBFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Collator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eduardo
 */
public class CidadeDAO {

    Connection cnx = DBFactory.getConnection();

    public String getTabela() {
        return "end_cidade";
    }

    public Iterator<Cidade> listCidades(Estado estado) throws DatabaseException {
        if (estado != null) {
            String sql = String.format("SELECT * FROM %s WHERE estado = ?", getTabela());

            try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
                pstmt.setInt(1, estado.getCodigo());

                ResultSet rs = pstmt.executeQuery();

                List<Cidade> lista = new LinkedList<>();
                while (rs.next()) {
                    Cidade cid = new Cidade(rs.getInt("codigo"), rs.getString("nome"));
                    lista.add(cid);
                }
                System.out.println(lista.size());

                lista.sort((Cidade c1, Cidade c2) -> Collator.getInstance(new Locale("pt", "BR")).compare(c1.getNome(), c2.getNome()));
                return lista.iterator();
            } catch (SQLException ex) {
                Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
