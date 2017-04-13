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
        logger.trace("Entering application.");
        Flyway flyway = new Flyway();
        flyway.setDataSource(URL, USER, PASSWORD);
        try {
            logger.trace("Trying make migrations.");
            flyway.migrate();
            logger.trace("Success migrations.");
        } catch (Exception e) {
            logger.error("Migrations failed. Get now base.");
            flyway.baseline();
        }

        logger.trace("Work this data base complited.");
        Validator validator = new Validator();
        UserInput userInput = new UserInput();
        AAAService service = new AAAService();
        Connection dbConnection = null;
        Statement statement;

        ArrayList<User> listUsers = new ArrayList<>();
        ArrayList<Resource> listRes = new ArrayList<>();

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM USER");
            while (result.next()) {
                listUsers.add(new User(result.getString("NAME"),result.getString("PASS"),
                        result.getString("SALT"),result.getInt("ID")));
            }
            result = statement.executeQuery("SELECT * FROM RESOURCE");
            while (result.next()) {
                listRes.add(new Resource(result.getString("PATH"), new int[]{result.getInt("USER_ID")},
                        Roles.valueOf(result.getString("ROLE"))));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                dbConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        validator.getUserInput(userInput, args);
        User reqUser;
        Resource reqRes = new Resource();
        ArrayList<Accounting> accountings = new ArrayList<>();
        boolean accessToRes = false;

        //Найти юзера по логину
        reqUser = service.findUserByLogin(userInput.getLogin(), listUsers);
        if (reqUser == null) {
            System.exit(1);
        }

        //проверить пароль
        if (!service.checkPasswordByUser(userInput.getPassword(), reqUser)) {
            System.exit(2);
        }

        System.out.println("Authentication: success");

        if (userInput.isAuthorisation()) {
            try {
                reqRes = service.getResource(userInput.getResource(), listRes, Roles.valueOf(userInput.getRole()));
            } catch (Exception e) {
                System.exit(3);
            }

            //вылавливаем неизвестные ресурсы
            if (reqRes == null) {
                System.exit(4);
            }

            //проверка доступа
            for (int userId : reqRes.getUsersId()) {
                if (reqUser.getId() == userId) {
                    accessToRes = true;
                    break;
                }
            }

            if (!accessToRes) {
                System.exit(4);
            }

            System.out.println("Resource " + reqRes.getPath() + " - ok");

            System.out.println("Authorisation: success");
            if (userInput.isAccounting()) {
                //ловим ошибку 5
                service.isDateValid(userInput.getDateEnd(), userInput.getDateStart());
                service.isVolumeValid(userInput.getVolume());

                LocalDate dateE = service.tryGetDate(userInput.getDateEnd());
                LocalDate dateS = service.tryGetDate(userInput.getDateStart());
                int volume = service.tryGetVolume(userInput.getVolume());

                accountings.add(new Accounting(dateS, dateE, volume, reqRes, reqUser));
                System.out.println(accountings.get(0).toString());
            }
        }
    }

    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(URL, USER, PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
}



