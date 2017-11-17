package com.computadores.dal;

import com.computadores.error.DatabaseException;
import com.computadores.model.Cliente;
import com.computadores.model.TipoPessoa;
import com.computadores.util.DBFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eduardo
 */
public class ClienteDAO implements IEntidadeDAO<Cliente> {
    
    Connection cnx = DBFactory.getConnection();

    @Override
    public String getTabela() {
        return "cliente";
    }

    @Override
    public void create(Cliente obj) throws DatabaseException {
        String pessoaCampos = "", pessoaValores = "";
        if (obj.getTipo() == TipoPessoa.FISICA) {
            pessoaCampos = "";
            pessoaValores = "";
        } else if (obj.getTipo() == TipoPessoa.JURIDICA) {
            pessoaCampos = "";
            pessoaValores = "";
            
        }
        String sql = String.format(
                "INSERT INTO %s (%s, )",
                getTabela(),
                (obj.getTipo() == TipoPessoa.FISICA)?"cpf, rg, nome":""
        );
        
//        codigo serial NOT NULL,
//        tipo character(1) NOT NULL DEFAULT 'F'::bpchar,
//        cpf bigint,
//        rg integer,
//        nome character varying(60),
//        dtnasc date,
//        cnpj bigint,
//        ie character varying(18),
//        ie_estadoemissor integer,
//        razaosocial character varying(60),
//        telresidencial character varying(14),
//        telcomercial character varying(14),
//        telcelular character varying(15),
//        email character varying(80) NOT NULL,
//        senha character(60),
//        administrador boolean NOT NULL DEFAULT false
        
        
        String sql = String.format(
                "INSERT INTO %s (idf, tipo, nome, endereco, fone, email, dtcadastro, status) "
                + "VALUES(?,?,?,?,?,?,?,?)", getTabela());

        try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            pstmt.setLong(1, obj.getIdf());
            pstmt.setString(2, String.valueOf(obj.getTipo()));
            pstmt.setString(3, obj.getNome());
            pstmt.setString(4, obj.getEndereco());
            pstmt.setString(5, obj.getFone());
            pstmt.setString(6, obj.getEmail());
            pstmt.setDate(7, new java.sql.Date(obj.getDtcadastro().getTime()));
            pstmt.setBoolean(8, obj.getStatus());

            pstmt.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException(ex, "Erro ao inserir registro");
        }
    }

    @Override
    public Cliente retrieve(Object key) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Cliente obj) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Cliente obj) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cliente> getAll() throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
