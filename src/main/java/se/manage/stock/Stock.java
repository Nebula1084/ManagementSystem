package se.manage.stock;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Stock {
    @Id
    private String code = "code";
    private String name = "name";
    private String surgedLimit = "surged";
    private String declineLimit = "decline";
    private Boolean pause;

    public Stock() {
        this.pause = Boolean.FALSE;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public String getSurgedLimit() {
        return this.surgedLimit;
    }

    public String getDeclineLimit() {
        return this.declineLimit;
    }
}
