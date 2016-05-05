package se.manage.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.manage.order.Order;
import se.manage.order.OrderWrapper;
import se.manage.stock.StockWrapper;
import se.manage.stock.VolatileStock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class DataFaker {
    List<VolatileStock> stocks;
    Map<String, VolatileStock> stockMap;

    public DataFaker() {
        stocks = new ArrayList<>();
        stockMap = new HashMap<>();
        for (Integer i = 0; i < 15; i++) {
            VolatileStock temp = new VolatileStock();
            temp.setCode(i.toString());
            temp.setName(temp.getName() + i);
            temp.setState(i % 2 == 0 ? VolatileStock.NORMAL : VolatileStock.PAUSE);
            stocks.add(temp);
            stockMap.put(temp.getCode(), temp);
        }
    }

    @RequestMapping(value = "info/stock/all", method = RequestMethod.POST)
    public StockWrapper stockAll() {
        return new StockWrapper(stocks);
    }

    @RequestMapping(value = "info/order/code", method = RequestMethod.POST)
    public OrderWrapper orderByCode(@RequestParam(value = "code") String code) {
        List<Order> orders = new ArrayList<>();
        for (Integer i = 0; i < 5; i++) {
            Order temp = new Order();
            temp.setCode(code);
            temp.setId(i);
            temp.setType(i % 2 == 0 ? Order.BUY : Order.SELL);
            temp.setAmount(12 + i);
            temp.setPrice(1.59f * (10 - i));
            temp.timestamp.setTime(i * 100000000);
            orders.add(temp);
        }
        return new OrderWrapper(orders);
    }

    @RequestMapping(value = "center/pause", method = RequestMethod.POST)
    public Object puase(@RequestParam(value = "code") String code) {
        stockMap.get(code).setState(VolatileStock.PAUSE);
        return new Success();
    }

    @RequestMapping(value = "center/restart", method = RequestMethod.POST)
    public Object restart(@RequestParam(value = "code") String code) {
        stockMap.get(code).setState(VolatileStock.NORMAL);
        return new Success();
    }

    @RequestMapping(value = "center/limit/surging", method = RequestMethod.POST)
    public Object limitSurging(@RequestParam(value = "code") String code, @RequestParam(value = "limit") String limit) {
        stockMap.get(code).setSurging_range_set(Float.valueOf(limit));
        return new Success();
    }

    @RequestMapping(value = "center/limit/decline", method = RequestMethod.POST)
    public Object limitDecline(@RequestParam(value = "code") String code, @RequestParam(value = "limit") String limit) {
        stockMap.get(code).setDecline_range_set(Float.valueOf(limit));
        return new Success();
    }
}
