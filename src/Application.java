import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by Artem 2 on 06.03.2017.
 */
public class Application {


    public static void main(String[] args) {
        Validator validator = new Validator();
        UserInput userInput = new UserInput();
        AAAService service = new AAAService();

        User JohnDoe = new User("jdoe", "sup3rpaZZ", 1);
        User JaneRow = new User("jrow", "Qweqrty12", 2);
        ArrayList<User> listUsers = new ArrayList<User>();
        listUsers.add(JohnDoe);
        listUsers.add(JaneRow);

        Resource res1 = new Resource("a", new int[]{JohnDoe.getId()}, Roles.READ);
        Resource res2 = new Resource("a.b", new int[]{JohnDoe.getId()}, Roles.WRITE);
        Resource res3 = new Resource("a.b.c", new int[]{JaneRow.getId()}, Roles.EXECUTE);
        Resource res4 = new Resource("a.bc", new int[]{JohnDoe.getId()}, Roles.EXECUTE);
        ArrayList<Resource> listRes = new ArrayList<Resource>();
        ArrayList<Resource> childRes = new ArrayList<>();
        listRes.add(res1);
        listRes.add(res2);
        listRes.add(res3);
        listRes.add(res4);

        validator.getUserInput(userInput, args);

        User reqUser = new User();
        Resource reqRes = new Resource();
        ArrayList<Accounting> accountings = new ArrayList<Accounting>();
        int error = 4;

        reqUser = service.findUserByLogin(userInput.getLogin(), listUsers);//Найти юзера по логину
        if (reqUser.getLogin() == null) {
            System.exit(1);
        }

        if (!service.checkPasswordByUser(reqUser, userInput.getPass())) { //проверить пароль
            System.exit(2);
        }

        System.out.print("Authentication: success\n");

        if (userInput.isAuthorisation()) {
            try {
                reqRes = service.findResource(userInput.getRes(), Roles.valueOf(userInput.getRole()), listRes);
            } catch (Exception e) {
                System.exit(3);
            }

            if (reqRes == null) //вылавливаем неизвестные ресурсы
            {
                System.exit(4);
            }

            for (int i = 0; i < reqRes.getUsersId().length; i++) {
                if (reqUser.getId() == reqRes.getUsersId()[i]) //проверка доступа
                {
                    error = 0;
                    break;
                }
            }
            if (error == 4) {
                System.exit(4); //
            }

            System.out.print("\nResource " + reqRes.getPath() + " - ok");
            //ищем дочерние ресурсы
            childRes = service.findChildResources(reqRes, listRes, Roles.valueOf(userInput.getRole()));
            for (Resource res : childRes
                    ) {
                System.out.print("\nResource " + res.getPath() + " - ok"); //разрешить доступ к ресурсам
            }

            System.out.print("\nAuthorisation: success\n");
            if (userInput.isAccounting()) {
                service.isDateValid(userInput.getDe(), userInput.getDs()); //ловим ошибку 5
                service.isVolValid(userInput.getVol()); //ловим ошибку 5
                LocalDate dateE = service.tryGetDate(userInput.getDe());
                LocalDate dateS = service.tryGetDate(userInput.getDs());
                int volume = service.tryGetVol(userInput.getVol());
                //добавляем запись о посещении ресурса
                accountings.add(new Accounting(dateS, dateE, volume, reqRes, reqUser));
                System.out.print("\nAdd record:\n" +
                        "DateStart - " + dateS + "\n" +
                        "DateEnd - " + dateE + "\n" +
                        "User Id - " + reqUser.getId() + "\n" +
                        "Resource - " + reqRes.getPath() + "\n" +
                        "Volume - " + volume);
                System.out.print("\nAccounting: success");
                System.exit(0);
            }
        }
    }
}



