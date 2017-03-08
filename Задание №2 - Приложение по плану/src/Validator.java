import java.sql.Time;

/**
 * Created by Artem 2 on 08.03.2017.
 */
public class Validator {
    public void GetUserInput(String[] args, UserInput userInput) {
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].compareTo("-login") == 0) {
                userInput.setLogin(args[i + 1]);
            }
            if (args[i].compareTo("-pass") == 0) {
                userInput.setPass(args[i + 1]);
            }
            if (args[i].compareTo("-res") == 0) {
                userInput.setRes(args[i + 1]);
            }
            if (args[i].compareTo("-role") == 0) {
                if (args[i + 1].compareTo(Roles.READ.toString()) == 0) {
                    userInput.setRole(Roles.READ);
                }
                if (args[i + 1].compareTo(Roles.WRITE.toString()) == 0) {
                    userInput.setRole(Roles.WRITE);
                }
                if (args[i + 1].compareTo(Roles.EXECUTE.toString()) == 0) {
                    userInput.setRole(Roles.EXECUTE);
                }
            }
            if (args[i].compareTo("-de") == 0) {
                userInput.setDe(Time.valueOf(args[i + 1]));
            }
            if (args[i].compareTo("-ds") == 0) {
                userInput.setDs(Time.valueOf(args[i + 1]));
            }
            if (args[i].compareTo("-vol") == 0) {
                userInput.setVal(Integer.valueOf(args[i + 1]));
            }
        }
    }
}
