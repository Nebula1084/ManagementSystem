package se.manage.stock;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VolatileStock implements Cloneable {
    private String code = "code";
    private String name = "name";
    private Float price = 12f;
    private Float surging_range = 0.1f;
    private Float surging_range_set = 0.12f;
    private Float decline_range = 0.1f;
    private Float decline_range_set = 0.11f;
    private Float least_price = 10f;
    private Float highest_price = 11f;
    private Date timestamp = Calendar.getInstance().getTime();
    private String state = NORMAL;
    private Boolean checked = Boolean.FALSE;

    final public static String NORMAL = "normal";
    final public static String PAUSE = "pause";

    public VolatileStock() {
    }

    public VolatileStock(Stock stock) {
    }

    public Boolean toggle() {
        return checked;
    }

    public Stock toStock() {
        return new Stock(code);
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Float getPrice() {
        return this.price;
    }

    public Float getSurging_range() {
        return this.surging_range;
    }

    public Float getDecline_range() {
        return this.decline_range;
    }

    public Float getLeast_price() {
        return least_price;
    }

    public Float getHighest_price() {
        return highest_price;
    }

    public void setSurging_range_set(Float surging_range_set) {
        this.surging_range_set = surging_range_set;
    }

    public Float getSurging_range_set() {
        return surging_range_set;
    }

    public void setDecline_range_set(Float decline_range_set) {
        this.decline_range_set = decline_range_set;
    }

    public Float getDecline_range_set() {
        return decline_range_set;
    }

    public String getTimestamp() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return dateFormat.format(timestamp);
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return String.format("{code : %s, name : %s, price : %f}", code, name, price);
    }

    @Override
    public VolatileStock clone() {
        VolatileStock stock = null;
        try {
            stock = (VolatileStock) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return stock;
    }

}
