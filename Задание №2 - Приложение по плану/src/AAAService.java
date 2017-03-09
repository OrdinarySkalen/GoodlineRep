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
        pass = pass + user.getSalt();
        //Хешируем pass
        if (pass.equals(user.getPass()))
            return true;
        else return false;
    }
}
