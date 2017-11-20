package com.computadores.dal;

import com.computadores.error.DatabaseException;
import com.computadores.model.Endereco;
import com.computadores.util.DBFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eduardo
 */
public class EnderecoDAO implements IEntidadeDAO<Endereco> {

    Connection cnx = DBFactory.getConnection();

    @Override
    public String getTabela() {
        return "endereco";
    }

    @Override
    public void create(Endereco obj) throws DatabaseException {
        if (!obj.validar()) {
            throw new DatabaseException(null, "O endereço informado não é válido");
        }

        String sql = String.format(
                "INSERT INTO %s "
                + "(cep, logradouro, complemento, bairro, cidade, padrao, cliente) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING codigo",
                getTabela());

        try (PreparedStatement pstmt = cnx.prepareCall(sql)) {
            pstmt.setInt(1, obj.getCep());
            pstmt.setString(2, obj.getLogradouro());
            pstmt.setString(3, obj.getComplemento());
            pstmt.setString(4, obj.getBairro());
            pstmt.setInt(5, obj.getCidade().getCodigo());
            pstmt.setBoolean(6, obj.isPadrao());
            pstmt.setInt(7, obj.getCliente().getCodigo());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                obj.setCodigo(rs.getInt("codigo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException(ex, "Erro ao inserir registro");
        }
    }

    @Override
    public Endereco retrieve(int key) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Endereco obj) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Endereco obj) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Endereco> getAll() throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
