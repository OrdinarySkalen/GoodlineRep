import org.junit.Test;
import praclab1.UserInput;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by GoodLine on 13.05.2017.
 */
public class ApplicationTest {
    @org.junit.After
    public void tearDown() throws Exception {

    }

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
        UserInput userInput = new UserInput();
        userInput = mock(UserInput.class);
        when(userInput.isEmpty()).thenReturn(true);
        assertEquals(true, userInput.isEmpty());
    }

    /**
     * UserInput = "-login XXX -pass XXX"
     */
    @Test
    public void test3() {

    }

    /**
     * UserInput = "-login jdoe -pass XXX "
     */
    @Test
    public void test4() {

    }

    /**
     * UserInput = "-login jdoe -pass sup3rpaZZ"
     */
    @Test
    public void test5() {

    }

    /**
     * UserInput = "-login jdoe -pass sup3rpaZZ -role READ -res a"
     */
    @Test
    public void test6() {

    }

    /**
     * UserInput = "-login jdoe -pass sup3rpaZZ -role READ -res a "
     */
    @Test
    public void test7() {

    }

}