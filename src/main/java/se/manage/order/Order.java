package se.manage.order;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Order {
    private Integer id;
    private String code;
    private String type;
    private Integer amount;
    private Float price;
    private Date timestamp = Calendar.getInstance().getTime();

    final public static String BUY = "buy";
    final public static String SELL = "sell";

    public Order() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getTimestamp() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return dateFormat.format(timestamp);
    }

    @Override
    public String toString() {
        return String.format("{id : %s, code : %s, type : %s, amount : %d, price : %f}", id, code, type, amount, price);
    }
}
