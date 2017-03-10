import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by Artem 2 on 08.03.2017.
 */
public class AAAService {
    public User findUserByLogin(String login, ArrayList<User> users) {
        User reqUser = new User();
        for (User user : users
                ) {
            if (user.getLogin().equals(login)) {
                reqUser = user;
            }
        }
        return reqUser;
    }

    public ArrayList<Resource> findChildResources(Resource resource,
                                                  ArrayList<Resource> resources, Roles role)
    {
        ArrayList<Resource> childRes = new ArrayList<>();
        for (Resource res:resources
             ) {
            if(res.getPath().startsWith(resource.getPath()) & resource.getRole().equals(role))
            {childRes.add(res);}
        }
        return childRes;
    }

    public Resource findResource(String path, Roles role, ArrayList<Resource> resources) {
        Resource reqRes = new Resource();
        for (Resource res : resources
                ) {
            if ((res.getPath().equals(path)) & (res.getRole().equals(role))) {
                reqRes = res;
            }
        }
        return reqRes;
    }

    public boolean checkPasswordByUser(User user, String pass) {
        pass =  DigestUtils.md5Hex(pass + user.getSalt());
        if (pass.equals(user.getHashPass())) {
            return true;
        } else {
            return false;
        }
    }

    public void isDateValid(String dateS, String dateE) {
        try {
            LocalDate.parse(dateS);
            LocalDate.parse(dateE);
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

    public LocalDate tryGetDate(String date) {
        return LocalDate.parse(date);
    }

    public int tryGetVol(String volume) {
        return Integer.valueOf(volume);
    }
}
