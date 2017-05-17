package praclab1;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Validator {

    private CommandLineParser parser = new DefaultParser();
    private Options options = new Options();
    private static final Logger logger = LogManager.getLogger(Validator.class);
    private HelpFormatter helpFormatter = new HelpFormatter();
    private CommandLine line;

    public Validator() {
        options.addOption("l", "login", true, "Login");
        options.addOption("pass", "pass", true, "Password");
        options.addOption("res", "resource", true, "Resource");
        options.addOption("r", "role", true, "Role");
        options.addOption("ds", "ds", true, "Date Start");
        options.addOption("de", "de", true, "Date End");
        options.addOption("vol", "volume", true, "Volume");
        options.addOption("h", "help", true, "Help");
    }

    /**
     * Преобразовать введенные параметры в объект класса praclab1.UserInput
     *
     * @param args массив аргументов переданных в программу
     */
    UserInput getUserInput(String[] args) {
        UserInput usIn = new UserInput();
        try {
            line = parser.parse(options, args);
            usIn.setLogin(line.getOptionValue("login"));
            usIn.setPassword(line.getOptionValue("pass"));
            usIn.setResource(line.getOptionValue("resource"));
            usIn.setRole(line.getOptionValue("role"));
            usIn.setDateStart(line.getOptionValue("ds"));
            usIn.setDateEnd(line.getOptionValue("de"));
            usIn.setVolume(line.getOptionValue("volume"));

            String input = "";
            for (String s : args) {
                input = String.format("%s %s", input, s);
            }
            logger.debug("User input:{}", input);
            if (line.hasOption("h") || (usIn.isEmpty())) {
                helpFormatter.printHelp("Help", options);
                logger.debug("Unknown parameters");
                System.exit(0);
            }

        } catch (ParseException exp) {
            helpFormatter.printHelp("Help", options);
            logger.debug("There is not any parameters");
            System.exit(0);
        }
        return usIn;
    }
}
