package praclab1;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import praclab1.dao.Connector;
import praclab1.domain.Resource;
import praclab1.domain.Roles;
import praclab1.domain.User;

import java.time.LocalDate;
import java.util.ArrayList;

public class AAAService {
    private static final Logger logger = LogManager.getLogger(Connector.class);

    /**
     * Проверить пароль пользователя
     *
     * @param pass введенный пароль
     * @param user пользователь подвергающийся проверке
     * @return результат проверки
     */
    public boolean checkPasswordByUser(String pass, User user) {
        return DigestUtils.md5Hex(DigestUtils.md2Hex(pass) +
                user.getSalt()).equals(user.getHashPassword());
    }

    public boolean isRoleValid(String role) {
        try {
            Roles.valueOf(role);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Проверка валидности дат
     *
     * @param dateS дата начала
     * @param dateE дата конца
     */
    public boolean isDateValid(String dateS, String dateE) {
        try {
            LocalDate.parse(dateS);
            LocalDate.parse(dateE);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Проверка валидности объема
     *
     * @param volume объем
     */
    public boolean isVolumeValid(String volume) {
        try {
            Integer.valueOf(volume);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
