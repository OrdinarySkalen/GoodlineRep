package praclab1;

public class UserInput {
    private String login;
    private String password;
    private String resource; //соответствует сво-ву Path класса Resource
    private String role;
    private String dateStart;
    private String dateEnd;
    private String volume;

    boolean isAuthorisation() {
        return this.getResource() != null && this.getRole() != null;
    }

    boolean isAccounting() {
        return this.getDateEnd() != null && this.getDateStart() != null;
    }

    public boolean isEmpty() {
        return (this.getLogin() == null) || (this.getPassword() == null);
    }

    public UserInput() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }
}
