import org.flywaydb.core.Flyway;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Application {
    private static final String URL = "jdbc:h2:./res/db/applicationDB";
    private static final String USER = "artem";
    private static final String PASSWORD = "123";
    private static final Logger logger = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        logger.debug("Start session");
        Flyway flyway = new Flyway();
        flyway.setDataSource(URL, USER, PASSWORD);
        try {
            flyway.migrate();
        } catch (Exception e) {
            flyway.baseline();
        }

        Validator validator = new Validator();
        UserInput userInput = new UserInput();
        AAAService service = new AAAService();
        Connector connector = new Connector();
        Connection dbConnection = null;
        Statement statement;

        validator.getUserInput(userInput, args);
        User reqUser = null;
        Resource reqRes = new Resource();


        try {
            dbConnection = connector.getDBConnection(URL, USER, PASSWORD);
            statement = dbConnection.createStatement();
            //Найти юзера по логину
            ResultSet result = statement.executeQuery(String.format("SELECT * FROM USER WHERE (USER.NAME LIKE '%s')", userInput.getLogin())); //сделать методом в коннекторе
            while (result.next()) {
                reqUser = new User(result.getString("NAME"), result.getString("PASS"),
                        result.getString("SALT"), result.getInt("ID"));
            }
            if (reqUser == null) {
                logger.error(String.format("User %s doesn't exist.(Exit-code 1)",
                        userInput.getLogin()));
                System.exit(1);
            }
            //проверить пароль
            if (!service.checkPasswordByUser(userInput.getPassword(), reqUser)) {
                logger.error(String.format("Password %s is incorrect for the user %s.(Exit-code - 2)",
                        userInput.getPassword(), userInput.getLogin()));
                System.exit(2);
            }

            logger.debug("Authentication: success");

            if (userInput.isAuthorisation()) {
                try {
                    result = statement.executeQuery(String.format("SELECT * FROM RESOURCE WHERE ((PATH='%s') or (PATH LIKE '%s')) AND (ROLE='%s') AND (USER_ID=%s)",
                            userInput.getResource(), userInput.getResource() + ".%", userInput.getRole(), reqUser.getId()));
                    while (result.next()) {
                        String role = result.getString("ROLE");
                        reqRes = new Resource(result.getString("PATH"), result.getInt("USER_ID"), Roles.valueOf(result.getString("ROLE")), result.getInt("ID"));
                    }
                } catch (Exception e) {
                    logger.error(String.format("Role %s doesn't exist.(Exit-code - 3)", userInput.getRole()));
                    System.exit(3);
                }

                //вылавливаем неизвестные ресурсы
                if (reqRes == null) {
                    logger.error(String.format("Resource %s with role %s isn't found.(Exit-code - 4)", userInput.getResource(), userInput.getRole()));
                    System.exit(4);
                }

                //проверка доступа
                if (reqUser.getId() != reqRes.getUserId()) {
                    logger.error(String.format("User %s don't have access to the resource %s.(Exit-code - 4)",
                            userInput.getLogin(), userInput.getResource()));
                    System.exit(4);
                }

                logger.debug(String.format("Resource %s - ok", reqRes.getPath()));
                logger.debug("Authorisation: success");

                if (userInput.isAccounting()) {
                    //ловим ошибку 5
                    service.isDateValid(userInput.getDateEnd(), userInput.getDateStart());
                    service.isVolumeValid(userInput.getVolume());

                    LocalDate dateE = service.tryGetDate(userInput.getDateEnd());
                    LocalDate dateS = service.tryGetDate(userInput.getDateStart());
                    int volume = service.tryGetVolume(userInput.getVolume());
                    statement.executeUpdate(String.format("INSERT INTO ACCOUNTING VALUES('%s', PARSEDATETIME('%s','yyyy-MM-dd'), PARSEDATETIME('%s','yyyy-MM-dd'),'%s')",
                            reqRes.getId(), userInput.getDateStart(), userInput.getDateEnd(), userInput.getVolume()));
                    //result = statement.executeQuery("SELECT * FROM ACCOUNTING");
                    /*while (result.next())
                    {
                        System.out.println(result.getDate("DATE_START")+" "+result.getDate("DATE_END"));
                    }*/
                    logger.debug("Accounting: success");
                }
            }
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        } finally {
            connector.closeConnection(dbConnection);
        }
        logger.debug("End session");
    }
}



