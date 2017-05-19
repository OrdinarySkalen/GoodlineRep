import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;
import org.junit.Test;
import praclab1.UserInput;
import praclab1.Validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidatorTest {
    @Test
    /*
     * Проверяем ввод пустой строки, позитивное тестирование
     */
    public void testEmptyInput() {
        Validator validator = new Validator();

        UserInput userInput = new UserInput();

        assertTrue(validator.isEmpty(userInput));
    }

    @Test
    /*
     * Проверяем ввод пустой строки, негативное тестирование
     */
    public void testNotEmptyInput() {
        Validator validator = new Validator();

        UserInput userInput = new UserInput();
        userInput.setLogin("test");
        userInput.setPassword("test");

        assertFalse(validator.isEmpty(userInput));
    }

    @Test
    /*
     * Проверяем ввод параметра "-h"
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

        assertTrue(validator.isHelp());
    }
}
