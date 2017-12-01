package com.computadores;

import com.computadores.dal.EstadoDAO;
import com.computadores.error.DatabaseException;
import com.computadores.model.Estado;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eduardo
 */
public class Config {

    // Recursos para acesso ao Banco de Dados
    private static String banco_driver = "org.postgresql.Driver";
    private static String banco_url = "jdbc:postgresql://localhost:5432/computadores";
    private static String banco_user = "postgres";
    private static String banco_pass = "postgres";

    // Recursos de auxílio aos formulários
    private static List<Estado> estados = createEstados();

    private Config() {

    }

    private static List<Estado> createEstados() {
        try {
            EstadoDAO dao = new EstadoDAO();
            return dao.listAll();
        } catch (DatabaseException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static String getBancoDriver() {
        return banco_driver;
    }

    public static String getBancoURL() {
        return banco_url;
    }

    public static String getBancoUser() {
        return banco_user;
    }

    public static String getBancoPass() {
        return banco_pass;
    }

    public static Iterator<Estado> getEstados() {
        return (estados != null) ? estados.iterator() : null;
    }
}
