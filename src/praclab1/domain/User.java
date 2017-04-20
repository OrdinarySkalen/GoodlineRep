package praclab1.domain;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

public class User {
    private String login;
    private String hashPassword;
    private int id;
    private String salt;

    public User() {
    }

    public User(String login, String pass, int id) {
        this.setLogin(login);
        this.setId(id);
        this.setSalt();
        this.setHashPassword(pass);
    }

    public User(String login, String pass, String salt, int id) {
        this.setLogin(login);
        this.setId(id);
        this.setSalt2(salt);
        this.setPassword(pass);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt() {
        this.salt = RandomStringUtils.randomAscii(5);
    }

    public void setSalt2(String salt) {
        this.salt = salt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String pass) {
        this.hashPassword = DigestUtils.md5Hex(DigestUtils.md2Hex(pass) + this.getSalt());
    }

    public void setPassword(String pass) {
        this.hashPassword = pass;
    }
}
