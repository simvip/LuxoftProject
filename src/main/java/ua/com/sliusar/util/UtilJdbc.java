package ua.com.sliusar.util;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Class UtilJdbc
 *
 * @author create by ivanslusar
 * 3/22/19
 * @project MyLuxoftProject
 */
public class UtilJdbc {
    private static final Logger LOGGER = Logger.getLogger(UtilJdbc.class);

    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException ex) {
            LOGGER.error("Initial driver failed.",ex);
            throw new ExceptionInInitializerError(ex);
        }

    }

    public static Connection getConnection(String db_url, String user, String password) {
        try {
            Connection connection = DriverManager.getConnection(db_url, user, password);
            connection.setAutoCommit(false);
            return connection;
        } catch (Throwable ex) {
            LOGGER.error("Initial connection failed.",ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}

