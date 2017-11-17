package com.computadores.util;

import com.computadores.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eduardo
 */
public class DBFactory {

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        } else {
            try {
                String driver = Config.getBancoDriver();
                String url = Config.getBancoURL();
                String user = Config.getBancoUser();
                String password = Config.getBancoPass();

                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DBFactory.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(DBFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
            return connection;
        }
    }
}
