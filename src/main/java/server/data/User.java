package server.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;

@Entity
@Table(name = User.TABLE)
public class User {
    public static final String TABLE = "users";
    private static final String FIRSTNAME_COL = "firstname";
    private static final String LASTNAME_COL = "lastname";
    private static final String IP_COL = "ip";
    private static final String DOMAIN_COL = "domain";
    private static final String PASSWORD_COL = "password";
    private static final String GUEST_CODE_COL = "guest_code";
    private static final String ALARM_CODE_COL = "alarm_code";
    private static final String AUTHORIZATION_TOKEN_COL = "authorization_token";
    private static final String IS_ADMIN_COL = "is_admin";

    @Id
    private String email;

    @Column(name = PASSWORD_COL)
    private String password;

    @Column(name = DOMAIN_COL)
    private String domain;


    @Column(name = FIRSTNAME_COL)
    private String firstname;

    @Column(name = LASTNAME_COL)
    private String lastname;

    @Column(name = IP_COL)
    private String ip;

    @Column(name = GUEST_CODE_COL)
    private String guestCode;

    @Column(name = ALARM_CODE_COL)
    private String alarmCode;

    @Column(name = AUTHORIZATION_TOKEN_COL)
    private String authorizationToken;

    @Column(name = IS_ADMIN_COL)
    private boolean isAdmin;



    @JsonCreator
    public User(String firstname, String lastname, String email, String ip, String domain,
                String guestCode, String alarmCode, boolean isAdmin, String authorizationToken, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.ip = ip;
        this.domain = domain;
        this.guestCode = guestCode;
        this.alarmCode = alarmCode;
        this.isAdmin = isAdmin;
        this.authorizationToken = authorizationToken;
        this.password = password;
    }

    public User() { }



    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
