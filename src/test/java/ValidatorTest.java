import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;
import org.junit.Test;
import praclab1.UserInput;
import praclab1.Validator;

import static org.junit.Assert.assertEquals;

/**
 * Created by Artem 2 on 19.05.2017.
 */
public class ValidatorTest {
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
}
