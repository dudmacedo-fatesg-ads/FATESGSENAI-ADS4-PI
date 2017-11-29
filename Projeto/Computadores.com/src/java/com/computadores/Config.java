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
    private static String BANCO_DRIVER = "org.postgresql.Driver";
    private static String BANCO_URL = "jdbc:postgresql://localhost:5432/computadores";
    private static String BANCO_USER = "postgres";
    private static String BANCO_PASS = "postgres";
    
    // Recursos de auxílio aos formulários
    private static List<Estado> ESTADOS = null;

    static {
        try {
            EstadoDAO dao = new EstadoDAO();
            ESTADOS = dao.listAll();
        } catch (DatabaseException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            ESTADOS = null;
        }
    }

    public static String getBancoDriver() {
        return BANCO_DRIVER;
    }

    public static String getBancoURL() {
        return BANCO_URL;
    }

    public static String getBancoUser() {
        return BANCO_USER;
    }

    public static String getBancoPass() {
        return BANCO_PASS;
    }
    
    public static Iterator<Estado> getEstados() {
        return ESTADOS.iterator();
    }
}
