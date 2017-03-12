import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

public class User {
    private String login;
    private String hashPass;
    private int id;
    private String salt;

    public User() {
    }

    public User(String login, String pass, int id) {
        this.setLogin(login);
        this.setId(id);
        this.setSalt();
        this.setHashPass(pass);
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

    int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    String getHashPass() {
        return hashPass;
    }

    private void setHashPass(String pass) {
        this.hashPass = DigestUtils.md5Hex(DigestUtils.md2Hex(pass) + this.getSalt());
    }
}
