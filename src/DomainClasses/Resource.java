package DomainClasses;
public class Resource {
    private int id;
    private String path;
    private int usersId;
    private Roles role;

    public Resource(String path, int userId, Roles role, int id) {
        this.setPath(path);
        this.setUserId(userId);
        this.setRole(role);
        this.setId(id);
    }

    public Resource() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getUserId() {
        return usersId;
    }

    public void setUserId(int usersId) {
        this.usersId = usersId;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
