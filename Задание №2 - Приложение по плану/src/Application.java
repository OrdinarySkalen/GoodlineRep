/**
 * Created by Artem 2 on 06.03.2017.
 */
public class Application {
    public static void main(String[] args) {
        System.out.print("Hello World!");
        boolean x = false;
        for (int i = 0; i < 10; i++) {
            System.out.print("Hi!"+i+"\n");
        }
        if (x)
        {System.exit(3);}
        else {System.exit(2);}
    }
}
