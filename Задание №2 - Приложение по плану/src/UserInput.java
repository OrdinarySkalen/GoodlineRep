import java.sql.Time;

/**
 * Created by Artem 2 on 08.03.2017.
 */
public class UserInput {
    private String login;
    private String pass;
    private String res; //соответствует сво-ву Path класса Resourse
    private Roles role;
    private Time de;
    private Time ds;
    private Integer vol;

    public boolean isAuthentification() {
        if (this.getPass() != null & this.getLogin() != null)
            return true;
        else return false;
    }

    public boolean isAuthorisation() {
        if (this.getRes() != null & this.getRole() != null)
            return true;
        else return false;
    }

    public boolean isAccounting() {
        if (this.getDe() != null & this.getDs() != null /*& this.getVal()!=null*/)
            return true;
        else return false;
        //придумать как зафиксировать отсутсвие параметра vol
    }

    public UserInput() {
    }

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
                     Roles role, Time de, Time ds, int vol) {
        this.setLogin(login);
        this.setPass(pass);
        this.setRes(res);
        this.setRole(role);
        this.setDs(ds);
        this.setDe(de);
        this.setVol(vol);
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

    public int getVol() {
        return vol;
    }

    public void setVol(int vol) {
        this.vol = vol;
    }
}
