import java.time.LocalDate;

/**
 * Created by Artem 2 on 10.03.2017.
 */
public class Accounting {
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private int volume;
    private Resource resource;
    private User user;

    public Accounting(LocalDate dateS, LocalDate dateE, int vol, Resource res, User user) {
        this.setDateStart(dateS);
        this.setDateEnd(dateE);
        this.setVolume(vol);
        this.setResource(res);
        this.setUser(user);
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }


    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }
}
