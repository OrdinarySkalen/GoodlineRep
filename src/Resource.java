public class Resource {
    private String path;
    private int[] usersId;
    private Roles role;

    public Resource(String path, int[] masId, Roles role) {
        this.setPath(path);
        this.setUsersId(masId);
        this.setRole(role);
    }

    public Resource() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int[] getUsersId() {
        return usersId;
    }

    public void setUsersId(int[] usersId) {
        this.usersId = usersId;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
