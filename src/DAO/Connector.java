package DAO;
import DomainClasses.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;

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

    public User getUserFromDataBase(UserInput userInput, Statement statement) {
        User user = null;
        ResultSet result;
        try {
            result = statement.executeQuery(String.format("SELECT * FROM USER WHERE (USER.NAME LIKE '%s')", userInput.getLogin()));
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

    public Resource getResourceFromDataBase(UserInput userInput, Statement statement) {
        Resource resource = null;
        ResultSet result;
        String[] masOfPath = userInput.getResource().split("\\.");
        String currentPath = masOfPath[0];
        try {
            try {
                for (String nextLevel : masOfPath
                        ) {
                    result = statement.executeQuery(String.format("SELECT * FROM RESOURCE WHERE (PATH='%s') AND (ROLE='%s')",
                            currentPath, userInput.getRole()));
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

    public void insertRecordIntoDataBase(UserInput userInput, Resource resource, Statement statement) {
        try {
            statement.executeUpdate(String.format("INSERT INTO ACCOUNTING VALUES('%s', PARSEDATETIME('%s','yyyy-MM-dd'), PARSEDATETIME('%s','yyyy-MM-dd'),'%s')",
                    resource.getId(), userInput.getDateStart(), userInput.getDateEnd(), userInput.getVolume()));
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
    }
}
