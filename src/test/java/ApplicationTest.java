import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;
import org.junit.Test;
import praclab1.UserInput;
import praclab1.Validator;
import praclab1.AAAService;
import praclab1.dao.Connector;
import praclab1.domain.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ApplicationTest {
    @Test
    /*
     * Проверяем поведение приложения при вводе пустой строки
     * UserInput = " "
     */
    public void testEmptyInput() {
        UserInput userInput = new UserInput();
        assertEquals(true, userInput.isEmpty());
    }

    @Test
    /*
     * Проверяем поведение приложения при вводе параметра "-h"
     * UserInput = "-h"
     */
    public void testHelpInput() {
        Validator validator = new Validator();
        CommandLineParser parser = new DefaultParser();
        String[] args = new String[]{"-h"};
        try {
            validator.line = parser.parse(validator.options, args);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertEquals(true, validator.isHelp());
    }

    /**
     * Проверяем поведение приложения при вводе неизвестного логина
     * UserInput = "-login XXX -pass XXX"
     */
    @Test
    public void testUnknownLogin() {
        UserInput userInput = new UserInput();
        userInput.setLogin("XXX");
        userInput.setPassword("XXX");
        Connector connector = mock(Connector.class);
        when(connector.getUserFromDataBase(userInput, null)).thenReturn(null);
        User reqUser = connector.getUserFromDataBase(userInput, null);
        assertEquals(null, reqUser);
    }

    /**
     * Проверяем поведение приложения при вводе неверного пароля к логину
     * UserInput = "-login jdoe -pass XXX "
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
     * Проверяем поведение приложения при вводе верного логина и пароля
     * (успешная аутентификация)
     * UserInput = "-login jdoe -pass sup3rpaZZ"
     */
    @Test
    public void testSuccessfulAuthentication() {
        UserInput userInput = new UserInput();
        userInput.setLogin("jdoe");
        userInput.setPassword("sup3rpaZZ");

        AAAService service = new AAAService();

        User reqUser = new User();
        reqUser.setPassword("595cae237d58197a0ce09f8a2b5498df");
        reqUser.setLogin("jdoe");
        reqUser.setSalt2("]cGlR");

        Connector connector = mock(Connector.class);
        when(connector.getUserFromDataBase(userInput, null)).thenReturn(reqUser);

        assertTrue(service.checkPasswordByUser(userInput.getPassword(), connector.getUserFromDataBase(userInput, null)));
    }

    /**
     * Проверяем поведение приложения при вводе верного логина, пароля и ресурса
     * (успешная авторизация)
     * UserInput = "-login jdoe -pass sup3rpaZZ -role READ -res a"
     */
    @Test
    public void testSuccessfulAuthorisation() {
        UserInput userInput = new UserInput();
        userInput.setLogin("jdoe");
        userInput.setPassword("sup3rpaZZ");
        userInput.setRole("READ");
        userInput.setResource("a");

        Resource resource = new Resource(userInput.getResource(), 1,
                Roles.valueOf(userInput.getRole()), 1);

        Connector connector = mock(Connector.class);
        when(connector.getResourceFromDataBase(userInput, null)).thenReturn(resource);
        assertEquals(resource, connector.getResourceFromDataBase(userInput, null));
    }

    /**
     * Проверяем поведение приложения при вводе верного логина, пароля и
     * ресурса, являющегося, дочерним по отношению к доступному поьзователю
     * (успешная авторизация 2)
     * UserInput = "-login jdoe -pass sup3rpaZZ -role READ -res a.b"
     */
    @Test
    public void testSuccessfulAuthorisation2() {
        UserInput userInput = new UserInput();
        userInput.setLogin("jdoe");
        userInput.setPassword("sup3rpaZZ");
        userInput.setRole("READ");
        userInput.setResource("a.b");
        Resource resource = new Resource(userInput.getResource(), 1,
                Roles.valueOf(userInput.getRole()), 1);

        Connector connector = mock(Connector.class);
        when(connector.getResourceFromDataBase(userInput, null)).thenReturn(resource);
        assertEquals(resource, connector.getResourceFromDataBase(userInput, null));
    }

    /**
     * Проверяем поведение приложения при вводе неизвестной роли для ресурса
     * UserInput = "-login jdoe -pass sup3rpaZZ -role XXX -res a.b"
     */
    @Test
    public void testUnknownRole() {
        UserInput userInput = new UserInput();
        userInput.setLogin("jdoe");
        userInput.setPassword("sup3rpaZZ");
        userInput.setRole("XXX");
        userInput.setResource("a.b");

        AAAService service = new AAAService();

        assertFalse(service.isRoleValid(userInput.getRole()));
    }

    /**
     * Проверяем поведение приложения при получении достпа к неизвестному ресурсу
     * UserInput = "-login jdoe -pass sup3rpaZZ -role READ -res XXX"
     */
    @Test
    public void testUnknownResource() {
        UserInput userInput = new UserInput();
        userInput.setLogin("jdoe");
        userInput.setPassword("sup3rpaZZ");
        userInput.setRole("READ");
        userInput.setResource("XXX");

        Connector connector = mock(Connector.class);
        when(connector.getResourceFromDataBase(userInput, null)).thenReturn(null);
        assertEquals(null, connector.getResourceFromDataBase(userInput, null));
    }

    /**
     * Проверяем поведение приложения при вводе неверной роли для ресурса
     * ресурс доступен пользоватлю с ролью READ
     * UserInput = "-login jdoe -pass sup3rpaZZ -role WRITE -res a"
     */
    @Test
    public void testWrongRoleForResource() {
        UserInput userInput = new UserInput();
        userInput.setLogin("jdoe");
        userInput.setPassword("sup3rpaZZ");
        userInput.setRole("WRITE");
        userInput.setResource("a");

        Connector connector = mock(Connector.class);
        when(connector.getResourceFromDataBase(userInput, null)).thenReturn(null);
        assertEquals(null, connector.getResourceFromDataBase(userInput, null));
    }

    /**
     * Проверяем поведение приложения при вводе неверной роли для дочернего ресурса
     * родительский ресурс доступен пользоватлю с ролью READ
     * UserInput = "-login jdoe -pass sup3rpaZZ -role WRITE -res a.bc"
     */
    @Test
    public void testWrongRoleForResource2() {
        UserInput userInput = new UserInput();
        userInput.setLogin("jdoe");
        userInput.setPassword("sup3rpaZZ");
        userInput.setRole("WRITE");
        userInput.setResource("a.bc");

        Connector connector = mock(Connector.class);
        when(connector.getResourceFromDataBase(userInput, null)).thenReturn(null);
        assertEquals(null, connector.getResourceFromDataBase(userInput, null));
    }

    /**
     * Проверяем поведение приложения при вводе валидных данных для аккаутинга
     * UserInput = "-login jdoe -pass sup3rpaZZ -role READ -res a.b -ds 2015-01-01 -de 2015-12-31 -vol 100"
     */
    @Test
    public void testSuccessfulAccounting() {
        UserInput userInput = new UserInput();
        userInput.setLogin("jdoe");
        userInput.setPassword("sup3rpaZZ");
        userInput.setRole("READ");
        userInput.setResource("a.b");
        userInput.setDateStart("2015-01-01");
        userInput.setDateEnd("2015-12-31");
        userInput.setVolume("100");
        AAAService service = new AAAService();

        assertTrue(service.isVolumeValid(userInput.getVolume()));
        assertTrue(service.isDateValid(userInput.getDateStart(), userInput.getDateEnd()));
    }

    /**
     * Проверяем поведение приложения при вводе невалидной даты для аккаутинга
     * UserInput = "-login jdoe -pass sup3rpaZZ -role READ -res a.b -ds 01-01-2015 -de 2015-12-31 -vol 100"
     */
    @Test
    public void testNotValidDate() {
        UserInput userInput = new UserInput();
        userInput.setLogin("jdoe");
        userInput.setPassword("sup3rpaZZ");
        userInput.setRole("READ");
        userInput.setResource("a.b");
        userInput.setDateStart("01-01-2015");
        userInput.setDateEnd("2015-12-31");
        userInput.setVolume("100");
        AAAService service = new AAAService();

        assertTrue(service.isVolumeValid(userInput.getVolume()));
        assertFalse(service.isDateValid(userInput.getDateStart(), userInput.getDateEnd()));
    }

    /**
     * Проверяем поведение приложения при вводе невалидного объема для аккаутинга
     * UserInput = "-login jdoe -pass sup3rpaZZ -role READ -res a.b -ds 2015-01-01 -de 2015-12-31 -vol XXX"
     */
    @Test
    public void testNotValidVolume() {
        UserInput userInput = new UserInput();
        userInput.setLogin("jdoe");
        userInput.setPassword("sup3rpaZZ");
        userInput.setRole("READ");
        userInput.setResource("a.b");
        userInput.setDateStart("2015-01-01");
        userInput.setDateEnd("2015-12-31");
        userInput.setVolume("XXX");
        AAAService service = new AAAService();

        assertFalse(service.isVolumeValid(userInput.getVolume()));
        assertTrue(service.isDateValid(userInput.getDateStart(), userInput.getDateEnd()));
    }

    /**
     * Проверяем невозможность аккаунитинга без аутентификации и авторизации
     * UserInput = "-login X -pass X -role READ -res X -ds 2015-01-01 -de 2015-12-31 -vol XXX"
     */
    @Test
    public void testAccountingWithoutAuthentication() {
        UserInput userInput = new UserInput();
        userInput.setLogin("X");
        userInput.setPassword("X");
        userInput.setRole("READ");
        userInput.setResource("X");
        userInput.setDateStart("2015-01-01");
        userInput.setDateEnd("2015-12-31");
        userInput.setVolume("XXX");
        Connector connector = mock(Connector.class);
        when(connector.getUserFromDataBase(userInput, null)).thenReturn(null);
        User reqUser = connector.getUserFromDataBase(userInput, null);
        assertEquals(null, reqUser);
    }

    /**
     * Проверяем невозможность авторизации без аутентификации
     * UserInput = "-login X -pass X -role READ -res X"
     */
    @Test
    public void testAuthorisationWithoutAuthentication() {
        UserInput userInput = new UserInput();
        userInput.setLogin("X");
        userInput.setPassword("X");
        userInput.setRole("READ");
        userInput.setResource("X");

        Connector connector = mock(Connector.class);
        when(connector.getUserFromDataBase(userInput, null)).thenReturn(null);
        User reqUser = connector.getUserFromDataBase(userInput, null);
        assertEquals(null, reqUser);
    }

}