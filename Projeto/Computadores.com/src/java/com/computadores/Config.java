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

    private static final Config CONFIG;

    // Recursos para acesso ao Banco de Dados
    private String banco_driver;
    private String banco_url;
    private String banco_user;
    private String banco_pass;

    // Recursos de auxílio aos formulários
    private List<Estado> estados = null;

    private Config() {
        banco_driver = "org.postgresql.Driver";
        banco_url = "jdbc:postgresql://localhost:5432/computadores";
        banco_user = "postgres";
        banco_pass = "postgres";
        try {
            EstadoDAO dao = new EstadoDAO();
            estados = dao.listAll();
        } catch (DatabaseException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static {
        CONFIG = new Config();
    }

    public static String getBancoDriver() {
        return CONFIG.banco_driver;
    }

    public static String getBancoURL() {
        return CONFIG.banco_url;
    }

    public static String getBancoUser() {
        return CONFIG.banco_user;
    }

    public static String getBancoPass() {
        return CONFIG.banco_pass;
    }

    public static Iterator<Estado> getEstados() {
        return (CONFIG.estados != null) ? CONFIG.estados.iterator() : null;
    }
}
