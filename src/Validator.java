import org.apache.commons.cli.*;

public class Validator {

    private CommandLineParser parser = new DefaultParser();
    private Options options = new Options();

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
            if (line.hasOption("h") || (usIn.getLogin() == null) || (usIn.getPassword() == null)) {
                HelpFormatter helpFormatter = new HelpFormatter();
                helpFormatter.printHelp("Help", options);
                System.exit(0);
            }
        } catch (ParseException exp) {
            HelpFormatter helpFormatter = new HelpFormatter();
            helpFormatter.printHelp("Help", options);
            System.exit(0);
        }
    }
}
