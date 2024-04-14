package server.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;

@Entity
@Table(name = Camera.TABLE)
public class Camera {
    public static final String TABLE = "cameras";
    private static final String IS_ON_COL = "is_on";
    private static final String DOMAIN_COL = "domain";

    @Id
    private String id;

    @Column(name = DOMAIN_COL)
    private String domain;

    @Column(name = IS_ON_COL)
    private boolean isOn;


    @JsonCreator
    public Camera(String id, String domain) {
        this.id = id;
        isOn = false;
        this.domain = domain;
    }

    public Camera() { }

    public String getId() {
        return id;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
