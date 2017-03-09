import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * Created by Artem 2 on 06.03.2017.
 */
public class Application {


    public static void main(String[] args) {
        Validator validator = new Validator();
        UserInput userInput = new UserInput();
        AAAService serv = new AAAService();
        /*CommandLineParser parser = new DefaultParser();
        Options options = new Options();*/

        User fPerson = new User("grom", "123qwe", 1, "12");
        User nPerson = new User("groza", "ewq321", 2, "13");
        User[] Users = {fPerson, nPerson};
        int[] Id_users1 = {1};
        int[] Id_users2 = {1, 2};
        Resourse Res1 = new Resourse("A.B", Id_users1, Roles.READ);
        Resourse Res2 = new Resourse("A.B.C", Id_users2, Roles.READ);
        Resourse[] massRes = {Res1, Res2};

        validator.SuperGetUserInput(userInput, args);

        User reqUser = new User();
        Resourse reqRes = new Resourse();
        int error = 0;

        reqUser = serv.FindUserByLogin(userInput.getLogin(), Users);//Найти юзера по логину
        if (reqUser.getLogin() == null) {
            System.exit(1);
        }
        if (!serv.CheckPasswordByUser(reqUser, userInput.getPass())) ; //Проверить пароль
        {
            System.exit(2);
        }
        System.out.print("Authentification: success");
        //validator.GetResurseInput(args, userInput); // тут вылезет ошибка 3, если она есть
        if (userInput.isAuthorisation() & error == 0) {
            //исправить метод класса validator так, что бы было возможно получить
            //ошибки 3,4 в этом фрагменте кода
            error = 4;
            //reqRes = serv.FindResourse(userInput.getRes(), userInput.getRole(), massRes); //найти ресурс по пути и роли
            for (int i = 0; i < reqRes.getUsers_ID().length; i++) {
                if (reqUser.getID() == reqRes.getUsers_ID()[i]) //проверка доступа
                {
                    error = 0;
                    break;
                }
            }
            if (error == 4) {
                System.exit(4);
            }
            System.out.print("Resourse " + reqRes.getPath() + " - ok");//разрешить доступ к ресурсу
            //разрешить доступ к дочерним ресурсам с той же ролью
            System.out.print("Authorisation: success");
            //validator.GetSessionInput(args, userInput);
            if (userInput.isAccounting() & error == 0) {
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



