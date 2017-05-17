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
    /*@org.junit.After
    public void tearDown() throws Exception {

    }*/

    private static final String URL = "jdbc:h2:./res/db/applicationDB";
    private static final String USER = "artem";
    private static final String PASSWORD = "123";

    @Test
    /**
     * UserInput = " "
     */
    public void test1() {
        UserInput userInput = new UserInput();
        assertEquals(true, userInput.isEmpty());
    }

    @Test
    /**
     * UserInput = "-h"
     */
    public void test2() {
        Validator validator = new Validator();
        CommandLineParser parser = new DefaultParser();
        String[] args = new String[] {"-h"};
        try {
            validator.line = parser.parse(validator.options, args);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertEquals(true, validator.isHelp());
    }

    /**
     * UserInput = "-login XXX -pass XXX"
     */
    @Test
    public void test3() {
        UserInput userInput = new UserInput();
        userInput.setLogin("XXX");
        userInput.setPassword("XXX");
        Connector connector = mock(Connector.class);
        when(connector.getUserFromDataBase(userInput,null)).thenReturn(null);
        User reqUser = connector.getUserFromDataBase(userInput, null);
        assertEquals(null, reqUser);
    }

    /**
     * UserInput = "-login jdoe -pass XXX "
     */
    @Test
    public void test4() {
        UserInput userInput = new UserInput();
        userInput.setLogin("jdoe");
        userInput.setPassword("XXX");
        User reqUser = new User();
        AAAService service = new AAAService();
        reqUser.setLogin("jdoe");
        reqUser.setPassword("595cae237d58197a0ce09f8a2b5498df");
        reqUser.setSalt2("]cGlR");
        assertFalse(service.checkPasswordByUser(userInput.getPassword(),reqUser));
    }

    /**
     * UserInput = "-login jdoe -pass sup3rpaZZ"
     */
    @Test
    public void test5() {
        UserInput userInput = new UserInput();
        userInput.setLogin("jdoe");
        userInput.setPassword("sup3rpaZZ");
        User reqUser = new User();
        AAAService service = new AAAService();
        reqUser.setPassword("595cae237d58197a0ce09f8a2b5498df");
        reqUser.setLogin("jdoe");
        reqUser.setSalt2("]cGlR");
        assertTrue(service.checkPasswordByUser(userInput.getPassword(),reqUser));
    }

    /**
     * UserInput = "-login jdoe -pass sup3rpaZZ -role READ -res a"
     */
    @Test
    public void test6() {
        UserInput userInput = new UserInput();
        userInput.setRole("READ");
        userInput.setResource("a");
        Resource resource = new Resource(userInput.getResource(),1,
                Roles.valueOf(userInput.getRole()),1);

        Connector connector = mock(Connector.class);
        when(connector.getResourceFromDataBase(userInput,null)).thenReturn(resource);
        assertEquals(resource,connector.getResourceFromDataBase(userInput,null));
    }

    /**
     * UserInput = "-login jdoe -pass sup3rpaZZ -role READ -res a.b"
     */
    @Test
    public void test7() {
        UserInput userInput = new UserInput();
        userInput.setRole("READ");
        userInput.setResource("a.b");
        Resource resource = new Resource(userInput.getResource(),1,
                Roles.valueOf(userInput.getRole()),1);

        Connector connector = mock(Connector.class);
        when(connector.getResourceFromDataBase(userInput,null)).thenReturn(resource);
        assertEquals(resource,connector.getResourceFromDataBase(userInput,null));
    }

    /**
     * UserInput = "-login jdoe -pass sup3rpaZZ -role XXX -res a.b"
     */
    @Test
    public void test8() {
        UserInput userInput = new UserInput();
        userInput.setRole("XXX");
        AAAService service = new AAAService();
        try {

        }
        assertTrue(Roles.valueOf(userInput.getRole())!=null);
    }

    /**
     * UserInput = "-login jdoe -pass sup3rpaZZ -role READ -res XXX"
     */
    @Test
    public void test9() {
    }

    /**
     * UserInput = "-login jdoe -pass sup3rpaZZ -role WRITE -res a"
     */
    @Test
    public void test10() {
    }

    /**
     * UserInput = "-login jdoe -pass sup3rpaZZ -role WRITE -res a.bc"
     */
    @Test
    public void test11() {
    }

    /**
     * UserInput = "-login jdoe -pass sup3rpaZZ -role READ -res a.b -ds 2015-01-01 -de 2015-12-31 -vol 100"
     */
    @Test
    public void test12() {
    }

    /**
     * UserInput = "-login jdoe -pass sup3rpaZZ -role READ -res a.b -ds 01-01-2015 -de 2015-12-31 -vol 100"
     */
    @Test
    public void test13() {
    }

    /**
     * UserInput = "-login jdoe -pass sup3rpaZZ -role READ -res a.b -ds 2015-01-01 -de 2015-12-31 -vol XXX"
     */
    @Test
    public void test14() {
    }

    /**
     * UserInput = "-login X -pass X -role READ -res X -ds 2015-01-01 -de 2015-12-31 -vol XXX"
     */
    @Test
    public void test15() {
    }

    /**
     * UserInput = "-login X -pass X -role READ -res X"
     */
    @Test
    public void test16() {
    }

}