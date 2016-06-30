package se.manage.stock;

import java.util.List;
import java.util.Map;

public class NewStockWrapper {
    String state;
    List<Map<String, String>> stocks;

    public NewStockWrapper() {

    }

    public String getState() {
        return state;
    }

    public List<Map<String, String>> getStocks() {
        return stocks;
    }
}