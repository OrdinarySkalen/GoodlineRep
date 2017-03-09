import java.sql.Time;

/**
 * Created by Artem 2 on 08.03.2017.
 */
public class AAAService {
    public User FindUserByLogin(String login, User[] users) {
        User reqUser = new User();
        for (User user : users
                ) {
            if (user.getLogin().equals(login))
                reqUser = user;
        }
        return reqUser;
    }

    public Resourse FindResourse(String path, Roles role, Resourse[] resourses) {
        Resourse reqRes = new Resourse();
        for (Resourse res : resourses
                ) {
            if (res.getPath().equals(path) & res.getRole().equals(role))
                reqRes = res;
        }
        return reqRes;
    }

    public boolean CheckPasswordByUser(User user, String pass) {
        //pass = pass + user.getSalt();
        //Хешируем pass
        if (pass.equals(user.getPass())) {
            return true;
        } else return false;
    }

    public void isDateValid(String ds, String de) {
        try {
            Time.valueOf(ds);
            Time.valueOf(de);
        } catch (Exception e) {
            System.exit(5);
        }
    }

    public void isVolValid(String volume) {
        try {
            Integer.valueOf(volume);
        } catch (Exception e) {
            System.exit(5);
        }
    }

    public Time tryGetDate(String date) {
        return Time.valueOf(date);
    }

    public int tryGetVol(String volume) {
        return Integer.valueOf(volume);
    }
}
