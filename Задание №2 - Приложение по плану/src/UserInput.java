import java.sql.Time;

/**
 * Created by Artem 2 on 08.03.2017.
 */
public class UserInput {
    private String login;
    private String pass;
    private String res;
    private String role;
    private Time ds;
    private Time de;
    private int vol;

    public UserInput(String login, String pass, String res, String role,
                     Time ds, Time de, int vol) {
        this.login = login;
        this.pass = pass;
        this.res = res;
        this.role = role;
        this.ds = ds;
        this.de = de;
        this.vol = vol;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Time getDs() {
        return ds;
    }

    public void setDs(Time ds) {
        this.ds = ds;
    }

    public Time getDe() {
        return de;
    }

    public void setDe(Time de) {
        this.de = de;
    }

    public int getVol() {
        return vol;
    }

    public void setVol(int vol) {
        this.vol = vol;
    }
}
