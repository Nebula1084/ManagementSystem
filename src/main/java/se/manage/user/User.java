package se.manage.user;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private String account = "";
    private String password = "";
    private String fullName = "";

    public User() {
    }

    public User(String account, String password, String fullName) {
        this.account = account;
        this.password = password;
        this.fullName = fullName;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAccount() {
        return this.account;
    }

    public String getPassword() {
        return this.password;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[account:").append(this.account)
                .append(", password:").append(this.password)
                .append(", fullName:").append(this.fullName).append("]");
        return buffer.toString();
    }
}