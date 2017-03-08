/**
 * Created by Artem 2 on 06.03.2017.
 */
public class Application {
    public static void main(String[] args) {
        User fPerson = new User("grom", "123qwe");
        User nPerson = new User("groza", "ewq321");
        User[] Users = {fPerson, nPerson};
        int error = 0;
        for (int i = 0; i < Users.length; i++) {
            if (args[1].compareTo(Users[i].getLogin())==0) {
                if (args[3].compareTo(Users[i].getPass())==0) {
                    error=0;
                    break;
                } else {
                    error=2;
                    break;
                }

            } else {
                error=1;
            }
        }
        System.exit(error);
    }
}


