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

    public UserInput() {
    }

    String getLogin() {
        return login;
    }

    void setLogin(String login) {
        this.login = login;
    }

    String getPassword() {
        return password;
    }

    void setPassword(String password) {
        this.password = password;
    }

    String getResource() {
        return resource;
    }

    void setResource(String resource) {
        this.resource = resource;
    }

    String getRole() {
        return role;
    }

    void setRole(String role) {
        this.role = role;
    }

    String getDateStart() {
        return dateStart;
    }

    void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    String getDateEnd() {
        return dateEnd;
    }

    void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    String getVolume() {
        return volume;
    }

    void setVolume(String volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        if (this.isAccounting()) {
            return String.format("Входные параметры: login - %s, pass - %s, res - %s, role - %s, dateStart - %s, dateEnd - %s, volume - %s",
                    this.login, this.password, this.resource, this.role, this.dateStart, this.dateEnd, this.volume);
        } else if (this.isAuthorisation()) {
            return String.format("Входные параметры: login - %s, pass - %s, res - %s, role - %s",
                    this.login, this.password, this.resource, this.role);
        } else {
            return String.format("Входные параметры: login - %s, pass - %s",
                    this.login, this.password);
        }
    }
}
