import org.apache.commons.cli.*;

import java.sql.Time;

/**
 * Created by Artem 2 on 08.03.2017.
 */
public class Validator {

    CommandLineParser parser = new DefaultParser();
    Options options = new Options();

    public UserInput SuperGetUserInput(UserInput ui, String[] args) {
        options.addOption("l", "login", true, "Login");
        options.addOption("pass", "pass", true, "Password");
        options.addOption("res", "resource", true, "Resource");
        options.addOption("r", "role", true, "Role");
        options.addOption("ds", "ds", true, "Date Start");
        options.addOption("de", "de", true, "Date Finish");
        options.addOption("vol", "volume", true, "Volume");
        options.addOption("h", "help", true, "Help");
        try {
            CommandLine line = parser.parse(options, args);
            ui.setLogin(line.getOptionValue("login"));
            ui.setPass(line.getOptionValue("pass"));
            ui.setRes(line.getOptionValue("resource"));
            ui.setRole(line.getOptionValue("role"));
            ui.setDs(line.getOptionValue("ds"));
            ui.setDe(line.getOptionValue("de"));
            ui.setVol(line.getOptionValue("volume"));
            if (line.hasOption("h") | ui.getLogin() == null | ui.getPass() == null) {
                HelpFormatter hf = new HelpFormatter();
                hf.printHelp("Help", options);
                System.exit(0);
            }
        } catch (ParseException exp) {
            HelpFormatter hf = new HelpFormatter();
            hf.printHelp("Help", options);
            System.exit(0);
        }
        return ui;
    }
}
