package se.manage.order;

public class Order {
    private Integer id;
    private String code;
    private String type;
    private Integer amount;
    private Float price;

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

    @Override
    public String toString() {
        return String.format("{id : %s, code : %s, type : %s, amount : %d, price : %f}", id, code, type, amount, price);
    }
}
