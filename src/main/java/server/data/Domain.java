package server.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;
import server.utils.TokenGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = Domain.TABLE)
public class Domain {
    public static final String TABLE = "domains";
    private static final int DEFAULT_ALERTS_CAPACITY = 50;
    private static final String ADDRESS_COL = "address";
    private static final String GUEST_CODE_COL = "guest_code";
    private static final String ALARM_CODE_COL = "alarm_code";
    private static final String AUTHORIZATION_TOKEN_COL = "authorization_token";
    private static final String DOMAIN_COL = "domain";

    @Id
    private String domain;

    @OneToMany(mappedBy = DOMAIN_COL, fetch = FetchType.EAGER)
    private List<Camera> cameras;

    @OneToMany(mappedBy = DOMAIN_COL, fetch = FetchType.EAGER)
    private List<User> users;

    @OneToMany(mappedBy = DOMAIN_COL, fetch = FetchType.EAGER)
    private List<Alert> alerts;

    @Column(name = ADDRESS_COL)
    private String address;

    @Column(name = GUEST_CODE_COL)
    private String guestCode;

    @Column(name = ALARM_CODE_COL)
    private String alarmCode;

    @Column(name = AUTHORIZATION_TOKEN_COL)
    private String authorizationToken;

    @JsonCreator
    public Domain(String domain, List<Camera> cameras, List<User> users, String address, String guestCode, String alarmCode) {
        this.domain = domain;
        this.cameras = cameras;
        this.users = users;
        this.alerts = new ArrayList<>(DEFAULT_ALERTS_CAPACITY);
        this.address = address;
        this.guestCode = guestCode;
        this.alarmCode = alarmCode;
    }

    public Domain() { }

    public String getName() {
        return domain;
    }

    public void setName(String domain) {
        this.domain = domain;
    }

    public List<Camera> getCameras() {
        return cameras;
    }

    public void setCameras(List<Camera> cameras) {
        this.cameras = cameras;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsersIPs(List<User> users) {
        this.users = users;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGuestCode() {
        return guestCode;
    }

    public void setGuestCode(String guestCode) {
        this.guestCode = guestCode;
    }

    public String getAlarmCode() {
        return alarmCode;
    }

    public void setAlarmCode(String alarmCode) {
        this.alarmCode = alarmCode;
    }

    public String getAuthorizationToken() {
        return authorizationToken;
    }

    public void setAuthorizationToken(String authorizationToken) {
        this.authorizationToken = authorizationToken;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Alert> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }
}
