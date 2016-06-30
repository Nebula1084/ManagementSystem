package se.manage.order;

import java.util.List;
import java.util.Map;

public class NewOrderWrapper {
    String state;
    List<Map<String, String>> orders;

    public NewOrderWrapper() {

    }

    public String getState() {
        return state;
    }

    public List<Map<String, String>> getOrders() {
        return orders;
    }
}