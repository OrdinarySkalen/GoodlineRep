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
                System.exit(0);
            }
        }
    }
}



