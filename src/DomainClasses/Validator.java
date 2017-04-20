import org.apache.commons.cli.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Validator {

    private CommandLineParser parser = new DefaultParser();
    private Options options = new Options();
    private static final Logger logger = LogManager.getLogger(Validator.class);

    /**
     * Преобразовать введенные параметры в объект класса UserInput
     *
     * @param usIn Пустой объект класса UserInput
     * @param args массив аргументов переданных в программу
     */
    void getUserInput(UserInput usIn, String[] args) {
        options.addOption("l", "login", true, "Login");
        options.addOption("pass", "pass", true, "Password");
        options.addOption("res", "resource", true, "Resource");
        options.addOption("r", "role", true, "Role");
        options.addOption("ds", "ds", true, "Date Start");
        options.addOption("de", "de", true, "Date End");
        options.addOption("vol", "volume", true, "Volume");
        options.addOption("h", "help", true, "Help");
        try {
            CommandLine line = parser.parse(options, args);
            usIn.setLogin(line.getOptionValue("login"));
            usIn.setPassword(line.getOptionValue("pass"));
            usIn.setResource(line.getOptionValue("resource"));
            usIn.setRole(line.getOptionValue("role"));
            usIn.setDateStart(line.getOptionValue("ds"));
            usIn.setDateEnd(line.getOptionValue("de"));
            usIn.setVolume(line.getOptionValue("volume"));
            String input = "";
            for (String s : args
                    ) {
                input = String.format("%s %s", input, s);
            }
            logger.debug(String.format("User input:%s", input));
            if (line.hasOption("h") || (usIn.getLogin() == null) || (usIn.getPassword() == null)) {
                HelpFormatter helpFormatter = new HelpFormatter();
                helpFormatter.printHelp("Help", options);
                logger.debug("Unknown parameters");
                System.exit(0);
            }
        } catch (ParseException exp) {
            HelpFormatter helpFormatter = new HelpFormatter();
            helpFormatter.printHelp("Help", options);
            logger.debug("There is not any parameters");
            System.exit(0);
        }
    }
}