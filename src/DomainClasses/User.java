package DomainClasses;
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

    String getLogin() {
        return login;
    }

    private void setLogin(String login) {
        this.login = login;
    }

    String getSalt() {
        return salt;
    }

    private void setSalt() {
        this.salt = RandomStringUtils.randomAscii(5);
    }

    private void setSalt2(String salt) {
        this.salt = salt;
    }

    int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    String getHashPassword() {
        return hashPassword;
    }

    private void setHashPassword(String pass) {
        this.hashPassword = DigestUtils.md5Hex(DigestUtils.md2Hex(pass) + this.getSalt());
    }

    private void setPassword(String pass) {
        this.hashPassword = pass;
    }
}
