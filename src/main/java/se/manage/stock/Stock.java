package se.manage.stock;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Stock {
    @Id
    private String code = "code";

    public Stock() {

    }

    public Stock(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    @Override
    public boolean equals(Object obj) {
        Stock t = (Stock) obj;
        return Objects.equals(t.code, this.code);
    }

    @Override
    public String toString(){
        return String.format("{code : %s}", code);
    }

}
