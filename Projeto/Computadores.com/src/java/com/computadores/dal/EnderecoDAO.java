package com.computadores.dal;

import com.computadores.error.DatabaseException;
import com.computadores.model.Cidade;
import com.computadores.model.Cliente;
import com.computadores.model.Endereco;
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
        if (!obj.validar() || obj.getCodigo() == null || obj.getCodigo() <= 0) {
            throw new DatabaseException(null, "O endereço informado não é válido");
        }

        String sql = String.format(
                "UPDATE %s SET "
                + "(cep, logradouro, complemento, bairro, cidade, padrao, cliente) = "
                + "(?, ?, ?, ?, ?, ?, ?) WHERE codigo = ?",
                getTabela());

        try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            pstmt.setInt(1, obj.getCep());
            pstmt.setString(2, obj.getLogradouro());
            pstmt.setString(3, obj.getComplemento());
            pstmt.setString(4, obj.getBairro());
            pstmt.setInt(5, obj.getCidade().getCodigo());
            pstmt.setBoolean(6, obj.isPadrao());
            pstmt.setInt(7, obj.getCliente().getCodigo());

            pstmt.setInt(8, obj.getCodigo());

            // Executa a operação
            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException(ex, "Erro ao atualizar registro");
        }
    }

    @Override
    public void delete(Endereco obj) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Endereco> getAll() throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Retorna os endereços cadastrados de um determinado Cliente
     *
     * @param cliente
     * @return Retorna os endereços cadastrados de um determinado Cliente
     * @throws com.computadores.error.DatabaseException
     */
    public List<Endereco> getAll(Cliente cliente) throws DatabaseException {
        String sql = String.format(
                "SELECT * FROM %s WHERE cliente = ?",
                getTabela());

        try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            pstmt.setInt(1, cliente.getCodigo());

            ResultSet rs = pstmt.executeQuery();
            List<Endereco> lista = new ArrayList<>();

            while (rs.next()) {
                Endereco end = new Endereco();

                end.setCodigo(rs.getInt("codigo"));
                end.setLogradouro(rs.getString("logradouro"));
                end.setComplemento(rs.getString("complemento"));
                end.setBairro(rs.getString("bairro"));
                end.setCidade(new Cidade(rs.getInt("cidade")));
                end.setPadrao(rs.getBoolean("padrao"));
//                end.setCliente(new Cliente(rs.getInt("cliente")));

                lista.add(end);
            }

            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException(ex, "Erro ao listar os endereços do Cliente");
        }
    }
}
