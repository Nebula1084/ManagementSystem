package se.manage.stock;

import java.util.List;

public class StockWrapper {
    String state;
    List<VolatileStock> stocks;

    public StockWrapper(){

    }

    public StockWrapper(List<VolatileStock> stocks) {
        this.state = "ok";
        this.stocks = stocks;

    }

    public String getState() {
        return state;
    }

    public List<VolatileStock> getStocks() {
        return stocks;
    }
}