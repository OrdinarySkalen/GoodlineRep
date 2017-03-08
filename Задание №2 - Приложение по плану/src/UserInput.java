import java.sql.Time;

/**
 * Created by Artem 2 on 08.03.2017.
 */
public class UserInput {
    private String login;
    private String pass;
    private String res;
    private Roles role;
    private Time de;
    private Time ds;
    private int val;

    public UserInput(String login, String pass) {
        this.setLogin(login);
        this.setPass(pass);
    }

    public UserInput(String login, String pass, String res,
                     Roles role) {
        this.setLogin(login);
        this.setPass(pass);
        this.setRes(res);
        this.setRole(role);
    }

    public UserInput(String login, String pass, String res,
                     Roles role, Time de, Time ds, int val) {
        this.setLogin(login);
        this.setPass(pass);
        this.setRes(res);
        this.setRole(role);
        this.setDs(ds);
        this.setDe(de);
        this.setVal(val);
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

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public Time getDe() {
        return de;
    }

    public void setDe(Time de) {
        this.de = de;
    }

    public Time getDs() {
        return ds;
    }

    public void setDs(Time ds) {
        this.ds = ds;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
}
