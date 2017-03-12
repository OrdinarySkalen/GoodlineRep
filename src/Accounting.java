import java.time.LocalDate;

public class Accounting {
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private int volume;
    private Resource resource;
    private User user;

    public Accounting(LocalDate dateS, LocalDate dateE, int vol,
                      Resource res, User user) {
        this.setDateStart(dateS);
        this.setDateEnd(dateE);
        this.setVolume(vol);
        this.setResource(res);
        this.setUser(user);
    }

    private void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    private void setVolume(int volume) {
        this.volume = volume;
    }

    private void setResource(Resource resource) {
        this.resource = resource;
    }

    private void setUser(User user) {
        this.user = user;
    }

    private void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }
}
