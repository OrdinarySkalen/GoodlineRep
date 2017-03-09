import org.apache.commons.cli.*;

import java.sql.Time;

/**
 * Created by Artem 2 on 08.03.2017.
 */
public class Validator {

    CommandLineParser parser = new DefaultParser();
    Options options = new Options();

    public UserInput SuperGetUserInput(UserInput ui, String[] args){
        options.addOption("l","login", true, "Login");
        options.addOption("pass","pass", true, "Password");
        options.addOption("res","resource", true, "Resource");
        options.addOption("r","role", true, "Role");
        options.addOption("ds","ds", true, "Date Start");
        options.addOption("de","de", true, "Date Finish");
        options.addOption("vol","volume", true, "Volume");
        options.addOption("h","help", false, "Help");
        try{
            CommandLine line = parser.parse(options,args);
                ui.setLogin(line.getOptionValue("login"));
                ui.setPass(line.getOptionValue("password"));
                ui.setRes(line.getOptionValue("resource"));
                ui.setRole(line.getOptionValue("role"));
                ui.setDs(line.getOptionValue("ds"));
                ui.setDe(line.getOptionValue("de"));
                ui.setVol(line.getOptionValue("volume"));
        }
        catch (ParseException exp)
        {
            HelpFormatter hf = new HelpFormatter();
            hf.printHelp("help", options);
            System.exit(0);
        }
        return ui;
    }

    // В дальнейшем заменить строковый массив на объект класса Options
    /*public void GetUserInput(String[] args, UserInput userInput) {
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals("-login")) {
                userInput.setLogin(args[i + 1]);
            }
            if (args[i].equals("-pass")) {
                userInput.setPass(args[i + 1]);
            }
        }
    }

    public void GetResurseInput(String[] args, UserInput userInput) {
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals("-res")) {
                userInput.setRes(args[i + 1]);
            }
            if (args[i].equals("-role")) {
                try {
                    userInput.setRole(Roles.valueOf(args[i + 1]));
                } catch (Exception e) {
                    System.exit(3);
                }
            }
        }
    }

    public void GetSessionInput(String[] args, UserInput userInput) {
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals("-de")) {
                try {
                    userInput.setDe(Time.valueOf(args[i + 1]));
                } catch (Exception e) {
                    System.exit(5);
                }
            }
            if (args[i].equals("-ds")) {
                try {
                    userInput.setDs(Time.valueOf(args[i + 1]));
                } catch (Exception e) {
                    System.exit(5);
                }
            }
            if (args[i].equals("-vol")) {
                try {
                    userInput.setVol(Integer.valueOf(args[i + 1]));
                } catch (Exception e) {
                    System.exit(5);
                }
            }
        }
    }*/
}
