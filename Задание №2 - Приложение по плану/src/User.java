/**
 * Created by Artem 2 on 08.03.2017.
 */
public class User {
    private String login;
    private String pass;
    private int id;
    private String salt;

    public User() {
    }

    public User(String login, String pass, int id, String salt) {
        this.setLogin(login);
        this.setPass(pass);
        this.setId(id);
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
