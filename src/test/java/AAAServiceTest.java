import org.junit.Test;
import praclab1.UserInput;
import praclab1.AAAService;
import praclab1.domain.*;

import static org.junit.Assert.*;

public class AAAServiceTest {
    /**
     * Проверяем правильность проверки пароля, позитивное тестирование
     */
    @Test
    public void testRightPassword() {
        AAAService service = new AAAService();

        UserInput userInput = new UserInput();
        userInput.setLogin("jdoe");
        userInput.setPassword("sup3rpaZZ");

        User reqUser = new User();
        reqUser.setLogin("jdoe");
        reqUser.setPassword("595cae237d58197a0ce09f8a2b5498df");
        reqUser.setSalt2("]cGlR");

        assertTrue(service.checkPasswordByUser(userInput.getPassword(), reqUser));
    }

    /**
     * Проверяем правильность проверки пароля, негативное тестирование
     */
    @Test
    public void testWrongPassword() {
        AAAService service = new AAAService();

        UserInput userInput = new UserInput();
        userInput.setLogin("jdoe");
        userInput.setPassword("XXX");

        User reqUser = new User();
        reqUser.setLogin("jdoe");
        reqUser.setPassword("595cae237d58197a0ce09f8a2b5498df");
        reqUser.setSalt2("]cGlR");

        assertFalse(service.checkPasswordByUser(userInput.getPassword(), reqUser));
    }

    /**
     * Проверяем правильность проверки роли, позитинное тестирование
     */
    @Test
    public void testValidRole() {
        AAAService service = new AAAService();

        UserInput userInput = new UserInput();
        userInput.setRole("READ");

        assertTrue(service.isRoleValid(userInput.getRole()));
    }

    /**
     * Проверяем правильность проверки роли, негативное тестирование
     */
    @Test
    public void testNotValidRole() {
        AAAService service = new AAAService();

        UserInput userInput = new UserInput();
        userInput.setRole("XXX");

        assertFalse(service.isRoleValid(userInput.getRole()));
    }

    /**
     * Проверяем правильность проверки валидности даты, позитивное тестирование
     */
    @Test
    public void testValidDate() {
        AAAService service = new AAAService();

        UserInput userInput = new UserInput();
        userInput.setDateStart("2015-01-01");
        userInput.setDateEnd("2015-12-31");

        assertTrue(service.isDateValid(userInput.getDateStart(), userInput.getDateEnd()));
    }

    /**
     * Проверяем правильность проверки валидности даты, негативное тестирование
     */
    @Test
    public void testNotValidDate() {
        AAAService service = new AAAService();

        UserInput userInput = new UserInput();
        userInput.setDateStart("01-01-2015");
        userInput.setDateEnd("2015-12-31");

        assertFalse(service.isDateValid(userInput.getDateStart(), userInput.getDateEnd()));
    }

    /**
     * Проверяем правильность проверки валидности объема, позитвное тестирование
     * UserInput = "-login jdoe -pass sup3rpaZZ -role READ -res a.b -ds 2015-01-01 -de 2015-12-31 -vol XXX"
     */
    @Test
    public void testValidVolume() {
        AAAService service = new AAAService();

        UserInput userInput = new UserInput();
        userInput.setVolume("100");

        assertTrue(service.isVolumeValid(userInput.getVolume()));
    }

    /**
     * Проверяем правильность проверки валидности объема, негативное тестирование
     */
    @Test
    public void testNotValidVolume() {
        AAAService service = new AAAService();

        UserInput userInput = new UserInput();
        userInput.setVolume("XXX");

        assertFalse(service.isVolumeValid(userInput.getVolume()));
    }
}