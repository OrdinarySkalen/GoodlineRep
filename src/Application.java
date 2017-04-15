import org.flywaydb.core.Flyway;
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

        Connector connector = new Connector();
        Validator validator = new Validator();
        UserInput userInput = new UserInput();
        AAAService service = new AAAService();
        Connection dbConnection = connector.getConnection(URL, USER, PASSWORD);

        validator.getUserInput(userInput, args);
        User reqUser;
        Resource reqRes = new Resource();


        try {
            Statement statement = dbConnection.createStatement();
            reqUser = connector.getUserFromDataBase(userInput, statement);

            //Проверить существоание пользователя
            if (reqUser == null) {
                logger.error(String.format("User %s doesn't exist.(Exit-code 1)",
                        userInput.getLogin()));
                System.exit(1);
            }

            //Проверить пароль
            if (!service.checkPasswordByUser(userInput.getPassword(), reqUser)) {
                logger.error(String.format("Password %s is incorrect for the user %s.(Exit-code - 2)",
                        userInput.getPassword(), userInput.getLogin()));
                System.exit(2);
            }

            logger.debug("Authentication: success");

            if (userInput.isAuthorisation()) {
                try {
                    reqRes = connector.getResourceFromDataBase(userInput,statement);
                } catch (Exception e) {
                    logger.error(String.format("Role %s doesn't exist.(Exit-code - 3)", userInput.getRole()));
                    System.exit(3);
                }

                //Вылавить неизвестные ресурсы
                if (reqRes == null) {
                    logger.error(String.format("Resource %s with role %s isn't found.(Exit-code - 4)", userInput.getResource(), userInput.getRole()));
                    System.exit(4);
                }

                //Проверить доступ
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

                    service.tryGetDate(userInput.getDateEnd());
                    service.tryGetDate(userInput.getDateStart());
                    service.tryGetVolume(userInput.getVolume());
                    //добавляем запись о использовании ресурса в БД
                    connector.insertRecordIntoDataBase(userInput,reqRes,statement);
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



