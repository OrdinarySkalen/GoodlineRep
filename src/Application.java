import org.flywaydb.core.Flyway;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.*;

public class Application {

    public static final String URL = "jdbc:h2:./db/applicationDB";
    public static final String USER = "artem";
    public static final String PASSWORD = "123";

    public static void main(String[] args) {
        // Create the Flyway instance
        Flyway flyway = new Flyway();

        flyway.setDataSource(URL, USER, PASSWORD);

        flyway.migrate();

        Validator validator = new Validator();
        UserInput userInput = new UserInput();
        AAAService service = new AAAService();
        Connection dbConnection = null;
        Statement statement = null;
        String query;

        User johnDoe = new User("jdoe", "sup3rpaZZ", 1);
        User janeRow = new User("jrow", "Qweqrty12", 2);
        ArrayList<User> listUsers = new ArrayList<>();
        listUsers.add(johnDoe);
        listUsers.add(janeRow);

        Resource res1 = new Resource("a", new int[]{johnDoe.getId()}, Roles.READ);
        Resource res2 = new Resource("a.b", new int[]{johnDoe.getId()}, Roles.WRITE);
        Resource res3 = new Resource("a.b.c", new int[]{janeRow.getId()}, Roles.EXECUTE);
        Resource res4 = new Resource("a.bc", new int[]{johnDoe.getId()}, Roles.EXECUTE);
        ArrayList<Resource> listRes = new ArrayList<>();
        listRes.add(res1);
        listRes.add(res2);
        listRes.add(res3);
        listRes.add(res4);

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            // Очиcтим таблицы
            //statement.execute("DELETE FROM USER ALL");
            //statement.execute("DELETE FROM RESOURCE ALL");
            // Заполняем таблицу USER
            /*for (User user : listUsers
                    ) {
                query = String.format("INSERT INTO USER (NAME,PASS,SALT, ID) VALUES ('%s','%s','%s','%s')",
                        user.getLogin(), user.getHashPassword(), user.getSalt(), user.getId());
                statement.executeUpdate(query);
            }
            // Заполняем таблицу RESOURCE
            for (Resource res: listRes
                 ) {
                query = String.format("INSERT INTO RESOURCE (PATH,USER_ID,ROLE) VALUES ('%s','%s','%s')",
                        res.getPath(), res.getUsersId()[0], res.getRole());
                statement.executeUpdate(query);
            }*/
            ResultSet result = statement.executeQuery("SELECT * FROM USER");
            while (result.next()) {
                System.out.println(result.getString("ID") + " " + result.getString("NAME") + " " + result.getString("SALT"));
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



