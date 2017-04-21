package praclab1.dao;

import praclab1.UserInput;
import praclab1.domain.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;

public class Connector {
    private static final Logger logger = LogManager.getLogger(Connector.class);

    public Connection getConnection(String url, String user, String password) {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            logger.debug(e.getMessage());
        }
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
        return null;
    }

    public static void closeConnection(Connection dbConnection) {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
    }

    public User getUserFromDataBase(UserInput userInput, Connection connection) {
        User user = null;
        ResultSet result;
        PreparedStatement statement;

        try {
            statement = connection.prepareStatement("SELECT * FROM USER WHERE (USER.NAME LIKE ?)");
            statement.setString(1, userInput.getLogin());
            result = statement.executeQuery();
            while (result.next()) {
                user = new User(result.getString("NAME"), result.getString("PASS"),
                        result.getString("SALT"), result.getInt("ID"));
            }
            return user;
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
        return user;
    }

    public Resource getResourceFromDataBase(UserInput userInput, Connection connection) {
        Resource resource = null;
        ResultSet result;
        String[] masOfPath = userInput.getResource().split("\\.");
        String currentPath = masOfPath[0];
        PreparedStatement statement;

        try {
            try {
                statement = connection.prepareStatement("SELECT * FROM RESOURCE WHERE (PATH=?) AND (ROLE=?)");

                for (String nextLevel : masOfPath) {
                    statement.setString(1, currentPath);
                    statement.setString(2, userInput.getRole());
                    result = statement.executeQuery();
                    currentPath += '.' + nextLevel;
                    if (result != null) {
                        while (result.next()) {
                            resource = new Resource(userInput.getResource(), result.getInt("USER_ID"),
                                    Roles.valueOf(result.getString("ROLE")), result.getInt("ID"));
                        }
                        break;
                    }
                }
            } catch (Exception e) {
                logger.error("Role {} doesn't exist.(Exit-code - 3)", userInput.getRole());
                System.exit(3);
            }
            return resource;
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return resource;
    }

    public void insertRecordIntoDataBase(UserInput userInput, Resource resource, Connection connection) {
        PreparedStatement statement;

        try {
            statement = connection.prepareStatement("INSERT INTO ACCOUNTING VALUES(?, ?, ?, ?)");
            statement.setInt(1, resource.getId());
            statement.setDate(2, Date.valueOf(userInput.getDateStart()));
            statement.setDate(3, Date.valueOf(userInput.getDateEnd()));
            statement.setString(4, userInput.getVolume());
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
    }
}
