/**
 * Created by Artem 2 on 06.03.2017.
 */
public class Application {
    public static void main(String[] args) {
        User fPerson = new User("grom", "123qwe", 1, "12");
        User nPerson = new User("groza", "ewq321", 2, "13");
        User[] Users = {fPerson, nPerson};
        int[] Id_users1 = {1};
        int[] Id_users2 = {1, 2};
        Resourse Res1 = new Resourse("A.B", Id_users1, Roles.READ);
        Resourse Res2 = new Resourse("A.B.C", Id_users2, Roles.READ);
        Resourse[] massRes = {Res1, Res2};

        int error = 0;
        if (args.length == 0)
            System.exit(-1);
        for (int i = 0; i < Users.length; i++) {
            if (args[1].equals(Users[i])) {
                if (args[3].equals(Users[i])) {
                    error = 0;
                    break;
                } else {
                    error = 2;
                    break;
                }

            } else {
                error = 1;
            }
        }
        System.exit(error);
    }
}


