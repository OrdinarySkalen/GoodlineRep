/**
 * Created by Artem 2 on 06.03.2017.
 */
public class Application {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.print(args[i] + "\n");

            if (args.length == 2) {
                System.exit(0);
            } else {
                System.exit(1);
            }

        }
    }
}
