import java.util.ArrayList;

/**
 * Created by Artem 2 on 06.03.2017.
 */
public class Application {
    public static void main(String[] args) {
        User fPerson = new User("grom", "123qwe");
        User nPerson = new User("groza", "ewq321");
        ArrayList<User> Users = new ArrayList();
        Users.add(fPerson);
        Users.add(nPerson);
        }
    }

