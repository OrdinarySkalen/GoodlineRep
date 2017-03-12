import java.time.LocalDate;
import java.util.ArrayList;

public class Application {

    public static void main(String[] args) {
        Validator validator = new Validator();
        UserInput userInput = new UserInput();
        AAAService service = new AAAService();

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

        validator.getUserInput(userInput, args);
        User reqUser;
        Resource reqRes = new Resource();
        ArrayList<Accounting> accountings = new ArrayList<>();
        int error = 4;

        reqUser = service.findUserByLogin(userInput.getLogin(), listUsers);//Найти юзера по логину
        if (reqUser.getLogin() == null) {
            System.exit(1);
        }

        if (!service.checkPasswordByUser(reqUser, userInput.getPassword())) { //проверить пароль
            System.exit(2);
        }

        System.out.println("Authentication: success");

        if (userInput.isAuthorisation()) {
            try {
                reqRes = service.getResource(userInput.getResource(), Roles.valueOf(userInput.getRole()), listRes);
            } catch (Exception e) {
                System.exit(3);
            }

            if (reqRes == null) //вылавливаем неизвестные ресурсы
            {
                System.exit(4);
            }

            for (int userId: reqRes.getUsersId()
                 ) {
                if (reqUser.getId() == userId) //проверка доступа
                {
                    error = 0;
                    break;
                }
            }

            if (error == 4) {
                System.exit(4);
            }

            System.out.println("Resource " + reqRes.getPath() + " - ok");

            System.out.println("Authorisation: success");
            if (userInput.isAccounting()) {
                service.isDateValid(userInput.getDateEnd(), userInput.getDateStart()); //ловим ошибку 5
                service.isVolumeValid(userInput.getVolume()); //ловим ошибку 5
                LocalDate dateE = service.tryGetDate(userInput.getDateEnd());
                LocalDate dateS = service.tryGetDate(userInput.getDateStart());
                int volume = service.tryGetVolume(userInput.getVolume());

                accountings.add(new Accounting(dateS, dateE, volume, reqRes, reqUser));

                //добавляем запись о посещении ресурсе
                String record = String.format("Add record:\n" +
                                "DateStart - %1$s\n" +
                                "DateEnd - %2$s\n" +
                                "UserId - %3$s\n" +
                                "Resource - %4$s\n" +
                                "Volume - %5$s"
                        , dateS, dateE, reqUser.getId(), reqRes.getPath(), volume);
                System.out.println(record+"\nAccounting: success");
                System.exit(0);
            }
        }
    }
}



