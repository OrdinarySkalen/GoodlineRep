import javax.management.relation.Role;
import java.util.List;

/**
 * Created by Artem 2 on 08.03.2017.
 */
public class Resourse {
    private String Path;
    private int[] Users_ID;
    private Roles Role;

    public Resourse(String path, int[] mas_id, Roles role) {
        this.setPath(path);
        this.setUsers_ID(mas_id);
        this.setRole(role);
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    public int[] getUsers_ID() {
        return Users_ID;
    }

    public void setUsers_ID(int[] users_ID) {
        Users_ID = users_ID;
    }

    public Roles getRole() {
        return Role;
    }

    public void setRole(Roles role) {
        Role = role;
    }
}
