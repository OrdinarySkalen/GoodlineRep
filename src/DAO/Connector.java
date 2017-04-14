package DAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Artem 2 on 14.04.2017.
 */
public class Connector {
    private static final Logger logger = LogManager.getLogger(Connector.class);

    private static Connection getDBConnection(String url, String user, String password) {
        Connection dbConnection = null;
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            logger.debug(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(url, user, password);
            return dbConnection;
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
        return dbConnection;
    }

    private static void closeConnection(Connection dbConnection)
    {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
