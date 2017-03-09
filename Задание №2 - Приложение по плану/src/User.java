import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created by Artem 2 on 08.03.2017.
 */
public class User {
    RandomStringUtils randStr = new RandomStringUtils();
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
        this.salt = randStr.randomAscii(5);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHashPass() {
        return hashPass;
    }

    public void setHashPass(String pass) {
        this.hashPass = DigestUtils.md5Hex(pass+this.getSalt());
    }
}
