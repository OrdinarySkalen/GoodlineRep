package praclab1.domain;

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

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Override
    public String toString() {
        return String.format("Add record: DateStart - %1$s, DateEnd - %2$s, UserId - %3$s, Resource - %4$s, Volume - %5$s",
                this.dateStart, this.dateEnd, this.user.getId(), this.resource.getPath(), this.volume);
    }
}
