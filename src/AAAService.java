import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDate;
import java.util.ArrayList;

class AAAService {

    User findUserByLogin(String login, ArrayList<User> users) {
        User reqUser = new User();
        for (User user : users
                ) {
            if (user.getLogin().equals(login)) {
                reqUser = user;
            }
        }
        return reqUser;
    }

    /**
     * Поиск родительского ресурса
     * @param path путь ресурса, родителя которого требуется найти
     * @param resources лист содержащий кандидатов в родители
     * @param role роль ресурса, родителя которого требуется найти
     * @return ресурс-родитель
     */
    private Resource findParentResource(String path, ArrayList<Resource> resources,
                                        Roles role) {
        Resource parentResource = new Resource();
        for (Resource res : resources
                ) {
            if (path.startsWith(res.getPath()+".") & res.getRole().equals(role)) {
                parentResource = res;
                break;
            }
        }
        return parentResource;
    }

    Resource findResource(String path, Roles role, ArrayList<Resource> resources) {
        Resource parentRes = this.findParentResource(path+".",resources,role);
        if(parentRes.getPath()!=null) {
            return new Resource(path, parentRes.getUsersId(), role);
        }else {return null;}
    }

    boolean checkPasswordByUser(User user, String pass) {
        pass = DigestUtils.md5Hex(DigestUtils.md2Hex(pass) + user.getSalt());
        return pass.equals(user.getHashPass());
    }

    void isDateValid(String dateS, String dateE) {
        try {
            LocalDate.parse(dateS);
            LocalDate.parse(dateE);
        } catch (Exception e) {
            System.exit(5);
        }
    }

    void isVolValid(String volume) {
        try {
            Integer.valueOf(volume);
        } catch (Exception e) {
            System.exit(5);
        }
    }

    LocalDate tryGetDate(String date) {
        return LocalDate.parse(date);
    }

    int tryGetVol(String volume) {
        return Integer.valueOf(volume);
    }
}
