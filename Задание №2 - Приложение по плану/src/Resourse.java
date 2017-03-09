/**
 * Created by Artem 2 on 08.03.2017.
 */
public class Resourse {
    private String path;
    private int[] usersId;
    private Roles role;

    public Resourse(String path, int[] masId, Roles role) {
        this.setPath(path);
        this.setUsersId(masId);
        this.setRole(role);
    }

    public Resourse() {
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
