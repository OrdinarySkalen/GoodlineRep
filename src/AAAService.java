import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDate;
import java.util.ArrayList;

class AAAService {

    /**
     * Поиск пользователя по логину
     *
     * @param login логин пользователя
     * @param users лист пользователей
     * @return искомый пользователь
     */
    User findUserByLogin(String login, ArrayList<User> users) {
        for (User user : users
                ) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Поиск родительского ресурса
     *
     * @param path      путь ресурса, родителя которого требуется найти
     * @param resources лист ресурсов-родителей
     * @param role      роль ресурса, родителя которого требуется найти
     * @return ресурс-родитель
     */
    private Resource findParentResource(String path, ArrayList<Resource> resources,
                                        Roles role) {
        for (Resource res : resources
                ) {
            if (path.startsWith(res.getPath() + ".") && res.getRole().equals(role)) {
                return res;
            }
        }
        return new Resource();
    }

    /**
     * Получить искомый ресурс
     *
     * @param path      путь искомого ресурса
     * @param resources лист ресурсов-родителей
     * @param role      роль искомого ресурса
     * @return искомый ресурс
     */
    Resource getResource(String path, ArrayList<Resource> resources, Roles role) {
        Resource parentRes = this.findParentResource(path + ".", resources, role);
        if (parentRes.getPath() != null) {
            return new Resource(path, parentRes.getUsersId(), role);
        } else {
            return null;
        }
    }

    /**
     * Проверить пароль пользователя
     *
     * @param pass введенный пароль
     * @param user пользователь подвергающийся проверке
     * @return результат проверки
     */
    boolean checkPasswordByUser(String pass, User user) {
        return DigestUtils.md5Hex(DigestUtils.md2Hex(pass) +
                user.getSalt()).equals(user.getHashPassword());
    }

    /**
     * Проверка валидности дат
     *
     * @param dateS дата начала
     * @param dateE дата конца
     */
    void isDateValid(String dateS, String dateE) {
        try {
            LocalDate.parse(dateS);
            LocalDate.parse(dateE);
        } catch (Exception e) {
            System.exit(5);
        }
    }

    /**
     * Проверка валидности объема
     *
     * @param volume объем
     */
    void isVolumeValid(String volume) {
        try {
            Integer.valueOf(volume);
        } catch (Exception e) {
            System.exit(5);
        }
    }

    /**
     * Получить дату в формате LocalDate
     *
     * @param date дата в текстовом формате
     * @return дата в формате LocalDate
     */
    LocalDate tryGetDate(String date) {
        return LocalDate.parse(date);
    }

    /**
     * Получить объем в числовом формате
     *
     * @param volume объем в текстовом формате
     * @return объем в числовом формате
     */
    int tryGetVolume(String volume) {
        return Integer.valueOf(volume);
    }
}
