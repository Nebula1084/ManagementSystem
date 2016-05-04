package se.manage.order;

import se.manage.order.Order;

import java.util.List;

public class OrderWrapper {
    private String state;
    private List<Order> orders;

    public OrderWrapper() {

    }

    public OrderWrapper(List<Order> orders) {
        this.state = "ok";
        this.orders = orders;
    }

    public String getState() {
        return state;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
