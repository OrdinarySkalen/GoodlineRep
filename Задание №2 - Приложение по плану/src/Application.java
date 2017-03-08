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
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();

        User fPerson = new User("grom", "123qwe", 1, "12");
        User nPerson = new User("groza", "ewq321", 2, "13");
        User[] Users = {fPerson, nPerson};
        int[] Id_users1 = {1};
        int[] Id_users2 = {1, 2};
        Resourse Res1 = new Resourse("A.B", Id_users1, Roles.READ);
        Resourse Res2 = new Resourse("A.B.C", Id_users2, Roles.READ);
        Resourse[] massRes = {Res1, Res2};

        validator.GetUserInput(args, userInput);

        int error = 0;

        if (userInput.isAuthentification() == false)
            System.exit(-1);
        else {
            //Найти пользователя по логину
            //Проверить пароль
            validator.GetResurseInput(args, userInput);
            if (userInput.isAuthorisation() & error == 0) {
                //исправить метод класса validator так, что бы было возможно получить
                //ошибки 3,4 в этом фрагменте кода
                //проверка доступа
                //разрешить доступ к ресурсу
                //разрешить доступ к дочерним ресурсам с той же ролью
                validator.GetSessionInput(args, userInput);
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
}


