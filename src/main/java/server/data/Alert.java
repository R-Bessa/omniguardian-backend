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

    @Column(name = DOMAIN_COL)
    private String domain;

    @Column(name = IMAGE_BYTES_COL)
    private byte[] imageBytes;

    @Column(name = FALSE_ALARM_COL)
    private boolean isFalseAlarm;


    @JsonCreator
    public Alert(String timestamp, String domain, byte[] imageBytes) {
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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
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
