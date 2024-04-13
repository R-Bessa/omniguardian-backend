package server.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;

@Entity
@Table(name = Alert.TABLE)
public class Alert {
    public static final String TABLE = "alerts";
    private static final String IMAGE_BYTES_COL = "image_bytes";
    private static final String FALSE_ALARM_COL = "is_false_alarm";
    private static final String DOMAIN_COL = "domain";

    @Id
    private String timestamp;

    @ManyToOne
    @JoinColumn(name = DOMAIN_COL)
    private Domain domain;

    @Column(name = IMAGE_BYTES_COL)
    private byte[] imageBytes;

    @Column(name = FALSE_ALARM_COL)
    private boolean isFalseAlarm;


    @JsonCreator
    public Alert(String timestamp, Domain domain, byte[] imageBytes) {
        this.timestamp = timestamp;
        this.domain = domain;
        this.imageBytes = imageBytes;
        this.isFalseAlarm = false;
    }

    public Alert() { }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }

    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }

    public boolean isFalseAlarm() {
        return isFalseAlarm;
    }

    public void setFalseAlarm(boolean falseAlarm) {
        isFalseAlarm = falseAlarm;
    }
}
