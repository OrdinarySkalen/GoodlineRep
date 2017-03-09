/**
 * Created by Artem 2 on 08.03.2017.
 */
public class User {
    private String login;
    private String pass;
    private int ID;
    private String salt;

    public User() {
    }

    public User(String login, String pass, int Id, String salt) {
        this.setLogin(login);
        this.setPass(pass);
        this.setID(Id);
        this.setSalt(salt);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
