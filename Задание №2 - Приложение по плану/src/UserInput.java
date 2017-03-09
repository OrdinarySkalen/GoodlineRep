import java.sql.Time;

/**
 * Created by Artem 2 on 08.03.2017.
 */
public class UserInput {
    private String login;
    private String pass;
    private String res; //соответствует сво-ву Path класса Resourse
    private String role;
    private Roles trueRole;
    private String ds;
    private Time trueDs;
    private String de;
    private Time trueDe;
    private String vol;
    private Integer trueVol;

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

    public Roles getTrueRole() {
        return trueRole;
    }

    public void setTrueRole(Roles trueRole) {
        this.trueRole = trueRole;
    }

    public String getDs() {
        return ds;
    }

    public void setDs(String ds) {
        this.ds = ds;
    }

    public Time getTrueDs() {
        return trueDs;
    }

    public void setTrueDs(Time trueDs) {
        this.trueDs = trueDs;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public Time getTrueDe() {
        return trueDe;
    }

    public void setTrueDe(Time trueDe) {
        this.trueDe = trueDe;
    }

    public String getVol() {
        return vol;
    }

    public void setVol(String vol) {
        this.vol = vol;
    }

    public Integer getTrueVol() {
        return trueVol;
    }

    public void setTrueVol(Integer trueVol) {
        this.trueVol = trueVol;
    }
}
