package se.manage.user;

import se.manage.stock.Stock;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {
    @Id
    private String account = "";
    private String password = "";
    private String fullName = "";

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Set<Stock> stocks;

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

    public void setStocks(Set<Stock> stocks) {
        this.stocks = stocks;
    }

    public Set<Stock> getStocks() {
        return stocks;
    }

    public String toString() {
        return String.format("{account:%s, password:%s, fullName:%s}", this.account, this.password, this.fullName);
    }
}