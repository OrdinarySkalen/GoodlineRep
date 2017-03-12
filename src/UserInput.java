public class UserInput {
    private String login;
    private String pass;
    private String res; //соответствует сво-ву Path класса Resource
    private String role;
    private String ds;
    private String de;
    private String vol;

    boolean isAuthorisation() {
        return this.getRes() != null & this.getRole() != null;
    }

    boolean isAccounting() {
        return this.getDe() != null & this.getDs() != null;
    }

    public UserInput() {
    }

    String getLogin() {
        return login;
    }

    void setLogin(String login) {
        this.login = login;
    }

    String getPass() {
        return pass;
    }

    void setPass(String pass) {
        this.pass = pass;
    }

    String getRes() {
        return res;
    }

    void setRes(String res) {
        this.res = res;
    }

    String getRole() {
        return role;
    }

    void setRole(String role) {
        this.role = role;
    }

    String getDs() {
        return ds;
    }

    void setDs(String ds) {
        this.ds = ds;
    }

    String getDe() {
        return de;
    }

    void setDe(String de) {
        this.de = de;
    }

    String getVol() {
        return vol;
    }

    void setVol(String vol) {
        this.vol = vol;
    }
}
