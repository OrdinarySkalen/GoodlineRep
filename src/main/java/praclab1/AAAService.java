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

    /**
     * Проверка валидности дат
     *
     * @param dateS дата начала
     * @param dateE дата конца
     */
    public void isDateValid(String dateS, String dateE) {
        try {
            LocalDate.parse(dateS);
            LocalDate.parse(dateE);
        } catch (Exception e) {
            System.exit(5);
        }
    }

    public void isRoleValid(String role) {
        try {
            Roles.valueOf(role);
        } catch (Exception e) {
            logger.error("Role {} isn't exist.(Exit-code - 3)", role);
            System.exit(3);
        }
    }

    /**
     * Проверка валидности объема
     *
     * @param volume объем
     */
    public void isVolumeValid(String volume) {
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
    public LocalDate tryGetDate(String date) {
        return LocalDate.parse(date);
    }

    /**
     * Получить объем в числовом формате
     *
     * @param volume объем в текстовом формате
     * @return объем в числовом формате
     */
    public int tryGetVolume(String volume) {
        return Integer.valueOf(volume);
    }
}
