package com.computadores.dal;

import com.computadores.error.DatabaseException;
import com.computadores.model.Cliente;
import com.computadores.model.Estado;
import com.computadores.model.PessoaFisica;
import com.computadores.model.PessoaJuridica;
import com.computadores.model.TipoPessoa;
import com.computadores.util.DBFactory;
import com.computadores.util.Hash;
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
public class ClienteDAO implements IEntidadeDAO<Cliente> {

    Connection cnx = DBFactory.getConnection();

    @Override
    public String getTabela() {
        return "cliente";
    }

    @Override
    public void create(Cliente obj) throws DatabaseException {
        // Auxílio para definir os campos do Query que fará o Insert
        String pessoaCampos = "", pessoaValores = "";
        if (obj.getTipo() == TipoPessoa.FISICA) {
            pessoaCampos = "cpf, rg, nome, dtnasc";
            pessoaValores = "?, ?, ?, ?";
        } else if (obj.getTipo() == TipoPessoa.JURIDICA) {
            pessoaCampos = "cnpj, ie, ie_estadoemissor, razaosocial";
            pessoaValores = "?, ?, ?, ?";
        }

        // Insert Query
        String sql = String.format(
                "INSERT INTO %s (tipo, %s, telresidencial, telcomercial, telcelular, email, senha, administrador) "
                + "VALUES (?, %s, ?, ?, ?, ?, ?, ?) RETURNING codigo",
                getTabela(),
                pessoaCampos,
                pessoaValores
        );

        try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            pstmt.setString(1, obj.getTipo().id);

            // Campos específicos PF e PJ
            if (obj.getTipo() == TipoPessoa.FISICA) {
                pstmt.setLong(2, ((PessoaFisica) obj).getCpf());
                pstmt.setInt(3, ((PessoaFisica) obj).getRg());
                pstmt.setString(4, ((PessoaFisica) obj).getNome());
                pstmt.setDate(5, new java.sql.Date(((PessoaFisica) obj).getDtNasc().getTime()));
            } else if (obj.getTipo() == TipoPessoa.JURIDICA) {
                pstmt.setLong(2, ((PessoaJuridica) obj).getCnpj());
                pstmt.setString(3, ((PessoaJuridica) obj).getInscricaoestadual());

                if (((PessoaJuridica) obj).getEstadoemissor() != null) {
                    pstmt.setInt(4, (((PessoaJuridica) obj).getEstadoemissor()).getCodigo());
                } else {
                    pstmt.setNull(4, java.sql.Types.INTEGER);
                }

                pstmt.setString(5, ((PessoaJuridica) obj).getRazaoSocial());
            }

            // Demais campos
            pstmt.setString(6, obj.getTelresidencial());
            pstmt.setString(7, obj.getTelcomercial());
            pstmt.setString(8, obj.getTelcelular());
            pstmt.setString(9, obj.getEmail());
            pstmt.setString(10, obj.getSenha());
            pstmt.setBoolean(11, obj.isAdministrador());

            // Executa operação
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                obj.setCodigo(rs.getInt("codigo"));

                // Falta implementar o carregamento dos endereços
                // **/--/**
                //
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException(ex, "Erro ao inserir registro");
        }
    }

    @Override
    public Cliente retrieve(int key) throws DatabaseException {
        String sql = String.format("SELECT * FROM %s WHERE codigo = ?", getTabela());

        try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            pstmt.setLong(1, key);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                TipoPessoa tipo = TipoPessoa.getById(rs.getString("tipo"));
                Cliente ret = null;
                if (tipo == TipoPessoa.FISICA) {
                    PessoaFisica cli = new PessoaFisica();
                    cli.setCodigo(key);
                    cli.setTipo(TipoPessoa.FISICA);
                    cli.setCpf(rs.getLong("cpf"));
                    cli.setRg(rs.getInt("rg"));
                    cli.setNome(rs.getString("nome"));
                    cli.setDtNasc(rs.getDate("dtnasc"));
                    ret = cli;
                } else if (tipo == TipoPessoa.JURIDICA) {
                    PessoaJuridica cli = new PessoaJuridica();
                    cli.setCodigo(key);
                    cli.setTipo(TipoPessoa.JURIDICA);
                    cli.setCnpj(rs.getLong("cnpj"));
                    String ie = rs.getString("ie");
                    if (ie != null) {
                        cli.setInscricaoestadual(ie);
                        cli.setEstadoemissor(new Estado(rs.getInt("ie_estadoemissor")));
                    }
                    cli.setRazaoSocial(rs.getString("razaosocial"));
                    ret = cli;
                }

                // Falta implementar o carregamento dos endereços
                // **/--/**
                //
                if (ret != null) {
                    ret.setTelresidencial(rs.getString("telresidencial"));
                    ret.setTelcomercial(rs.getString("telcomercial"));
                    ret.setTelcelular(rs.getString("telcelular"));
                    ret.setEmail(rs.getString("email"));
                    ret.setSenha(rs.getString("senha"));
                    ret.setAdministrador(rs.getBoolean("administrador"));
                }

                return ret;
            } else {
                throw new DatabaseException(null, "Não existe nenhum registro com a chave informada");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException(ex, "Erro ao recuperar registro");
        }
    }

    public Cliente retrieveLogin(String login, String senha) throws DatabaseException {
        String sql = String.format("SELECT codigo, senha FROM %s WHERE email = ?", getTabela());

        try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            pstmt.setString(1, login);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String senha_banco = rs.getString("senha");

                if (Hash.validar(senha, senha_banco, Hash.BCRYPT)) {
                    return retrieve(rs.getInt("codigo"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void update(Cliente obj) throws DatabaseException {
        // Auxílio para definir os campos do Query que fará o Update
        String pessoaCampos = "", pessoaValores = "";
        if (obj.getTipo() == TipoPessoa.FISICA) {
            pessoaCampos = "rg, nome, dtnasc";
            pessoaValores = "?, ?, ?";
        } else if (obj.getTipo() == TipoPessoa.JURIDICA) {
            pessoaCampos = "ie, ie_estadoemissor, razaosocial";
            pessoaValores = "?, ?, ?";
        }

        String sql = String.format(
                "UPDATE %s SET "
                + "(%s, telresidencial, telcomercial, telcelular, email, senha, administrador) = "
                + "(%s, ?, ?, ?, ?, ?, ?) "
                + "WHERE codigo = ?",
                getTabela(),
                pessoaCampos,
                pessoaValores);

        try (PreparedStatement pstmt = cnx.prepareCall(sql)) {
            // Campos específicos PF e PJ
            if (obj.getTipo() == TipoPessoa.FISICA) {
                pstmt.setInt(1, ((PessoaFisica) obj).getRg());
                pstmt.setString(2, ((PessoaFisica) obj).getNome());
                pstmt.setDate(3, new java.sql.Date(((PessoaFisica) obj).getDtNasc().getTime()));
            } else if (obj.getTipo() == TipoPessoa.JURIDICA) {
                pstmt.setString(1, ((PessoaJuridica) obj).getInscricaoestadual());

                if (((PessoaJuridica) obj).getEstadoemissor() != null) {
                    pstmt.setInt(2, (((PessoaJuridica) obj).getEstadoemissor()).getCodigo());
                } else {
                    pstmt.setNull(2, java.sql.Types.INTEGER);
                }

                pstmt.setString(3, ((PessoaJuridica) obj).getRazaoSocial());
            }

            // Demais campos
            pstmt.setString(4, obj.getTelresidencial());
            pstmt.setString(5, obj.getTelcomercial());
            pstmt.setString(6, obj.getTelcelular());
            pstmt.setString(7, obj.getEmail());
            pstmt.setString(8, obj.getSenha());
            pstmt.setBoolean(9, obj.isAdministrador());

            pstmt.setInt(10, obj.getCodigo());

            // Executa operação
            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException(ex, "Erro ao atualizar registro");
        }
    }

    @Override
    public void delete(Cliente obj) throws DatabaseException {
        String sql = String.format(
                "DELETE FROM %s "
                + "WHERE codigo = ?", getTabela());

        try (PreparedStatement pstmt = cnx.prepareStatement(sql)) {
            // Falta implementar a exclusão dos endereços
            // Não vai funcionar se o cliente tiver endereço cadastrado
            // **/--/**
            //

            pstmt.setInt(1, obj.getCodigo());

            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException(ex, "Erro ao excluir registro");
        }
    }

    @Override
    public List<Cliente> list() throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
