import org.apache.commons.cli.*;

public class Validator {

    private CommandLineParser parser = new DefaultParser();
    private Options options = new Options();

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
            usIn.setPass(line.getOptionValue("pass"));
            usIn.setRes(line.getOptionValue("resource"));
            usIn.setRole(line.getOptionValue("role"));
            usIn.setDs(line.getOptionValue("ds"));
            usIn.setDe(line.getOptionValue("de"));
            usIn.setVol(line.getOptionValue("volume"));
            if (line.hasOption("h") | (usIn.getLogin() == null) | (usIn.getPass() == null)) {
                HelpFormatter hf = new HelpFormatter();
                hf.printHelp("Help", options);
                System.exit(0);
            }
        } catch (ParseException exp) {
            HelpFormatter hf = new HelpFormatter();
            hf.printHelp("Help", options);
            System.exit(0);
        }
    }
}
