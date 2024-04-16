package server.data;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Storage {
    private User user;
    private Alert alert;

    @JsonCreator
    public Storage(User user, Alert alert) {
        this.user = user;
        this.alert = alert;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Alert getAlert() {
        return alert;
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
    }
}
