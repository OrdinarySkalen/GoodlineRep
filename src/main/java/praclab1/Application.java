package praclab1;

import praclab1.dao.*;
import praclab1.domain.*;
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
        AAAService service = new AAAService();
        Connection dbConnection = connector.getConnection(URL, USER, PASSWORD);

        UserInput userInput = validator.getUserInput(args);
        User reqUser;
        Resource reqRes;


        try {
            reqUser = connector.getUserFromDataBase(userInput, dbConnection);

            //Проверить существоание пользователя
            if (reqUser == null) {
                logger.error("User {} doesn't exist.(Exit-code 1)",
                        userInput.getLogin());
                System.exit(1);
            }

            //Проверить пароль
            if (!service.checkPasswordByUser(userInput.getPassword(), reqUser)) {
                logger.error("Password {} is incorrect for the user {}.(Exit-code - 2)",
                        userInput.getPassword(), userInput.getLogin());
                System.exit(2);
            }

            logger.debug("Authentication: success");

            if (userInput.isAuthorisation()) {
                reqRes = connector.getResourceFromDataBase(userInput, dbConnection);

                if (!service.isRoleValid(userInput.getRole())) {
                    logger.error("Role {} doesn't exist.(Exit-code - 3)", userInput.getRole());
                    System.exit(3);
                }

                //Вылавить неизвестные ресурсы
                if (reqRes == null) {
                    logger.error("Resource {} with role {} isn't found.(Exit-code - 4)", userInput.getResource(), userInput.getRole());
                    System.exit(4);
                }

                //Проверить доступ
                if (reqUser.getId() != reqRes.getUserId()) {
                    logger.error("User {} don't have access to the resource {}.(Exit-code - 4)",
                            userInput.getLogin(), userInput.getResource());
                    System.exit(4);
                }

                logger.debug("Resource {} - ok", reqRes.getPath());
                logger.debug("Authorisation: success");

                if (userInput.isAccounting()) {
                    //ловим ошибку 5
                    if (!service.isDateValid(userInput.getDateEnd(), userInput.getDateStart())) {
                        logger.error("Date {} or {} doesn't correct.(Exit-code - 5)",
                                userInput.getDateStart(), userInput.getDateEnd());
                        System.exit(5);
                    }

                    if (!service.isVolumeValid(userInput.getVolume())) {
                        logger.error("Volume {} doesn't correct.(Exit-code - 5)",
                                userInput.getVolume());
                        System.exit(5);
                    }

                    //добавляем запись о использовании ресурса в БД
                    connector.insertRecordIntoDataBase(userInput, reqRes, dbConnection);
                    logger.debug("Accounting: success");
                }
            }
        } finally {
            Connector.closeConnection(dbConnection);
        }
        logger.debug("End session");
    }
}



