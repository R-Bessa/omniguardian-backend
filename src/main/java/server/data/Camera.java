package server.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @ManyToOne
    @JoinColumn(name = DOMAIN_COL)
    @JsonBackReference
    private Domain domain;

    @Column(name = IS_ON_COL)
    private boolean isOn;


    @JsonCreator
    public Camera(String id, Domain domain) {
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

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }
}
