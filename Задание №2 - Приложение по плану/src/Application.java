import java.util.ArrayList;

/**
 * Created by Artem 2 on 06.03.2017.
 */
public class Application {


    public static void main(String[] args) {
        Validator validator = new Validator();
        UserInput userInput = new UserInput();
        AAAService servise = new AAAService();

        User fPerson = new User("grom", "123qwe", 1, "12");
        User nPerson = new User("groza", "ewq321", 2, "13");
        ArrayList<User> listUsers = new ArrayList<User>();
        listUsers.add(fPerson);
        listUsers.add(nPerson);

        Resourse res1 = new Resourse("A.B", new int[]{1}, Roles.READ);
        Resourse res2 = new Resourse("A.B.C", new int[]{1, 2}, Roles.READ);
        ArrayList<Resourse> listRes = new ArrayList<Resourse>();
        listRes.add(res1);
        listRes.add(res2);
        validator.getUserInput(userInput, args);

        User reqUser = new User();
        Resourse reqRes = new Resourse();
        int error = 4;

        reqUser = servise.findUserByLogin(userInput.getLogin(), listUsers);//Найти юзера по логину
        if (reqUser.getLogin() == null) {
            System.exit(1);
        }

        if (!servise.checkPasswordByUser(reqUser, userInput.getPass())) { //проверить пароль
            System.exit(2);
        }

        System.out.print("Authentification: success");

        if (userInput.isAuthorisation()) {
            try {
                reqRes = servise.findResourse(userInput.getRes(), Roles.valueOf(userInput.getRole()), listRes);
            } catch (Exception e) {
                System.exit(3);
            }

            if (reqRes.getPath() == null) //вылавливаем неизвестные ресурсы
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
                System.exit(4);
            }
            System.out.print("\nResourse " + reqRes.getPath() + " - ok");//разрешить доступ к ресурсу
            //разрешить доступ к дочерним ресурсам с той же ролью
            System.out.print("\nAuthorisation: success");
            if (userInput.isAccounting()) {
                //isDataValid(ds,de);
                //isVolValid(vol)
                //tryGetDate()
                //tryGetVol()
                //добавить запись о посещении ресурса
                System.exit(0);
            }
        }
    }
}



