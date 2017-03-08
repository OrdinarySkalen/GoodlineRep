import java.sql.Time;

/**
 * Created by Artem 2 on 08.03.2017.
 */
public class Validator {
    // В дальнейщем заменить строковый массив на объект класса Options
    public void GetUserInput(String[] args, UserInput userInput) {
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals("-login")) {
                userInput.setLogin(args[i + 1]);
            }
            if (args[i].equals("-pass")) {
                userInput.setPass(args[i + 1]);
            }
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
    }
}
