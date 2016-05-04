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
import java.util.List;


@RequestMapping("info")
@RestController
public class DataFaker {
    List<VolatileStock> stocks;

    public DataFaker() {
        stocks = new ArrayList<>();
        for (Integer i = 0; i < 15; i++) {
            VolatileStock temp = new VolatileStock();
            temp.setCode(i.toString());
            temp.setName(temp.getName() + i);
            temp.setState(i % 2 == 0 ? VolatileStock.NORMAL : VolatileStock.PAUSE);
            stocks.add(temp);
        }
    }

    @RequestMapping(value = "/stock/all", method = RequestMethod.POST)
    public StockWrapper stockAll() {
        return new StockWrapper(stocks);
    }

    @RequestMapping(value = "/order/code", method = RequestMethod.POST)
    public OrderWrapper orderByCode(@RequestParam(value = "code") String code) {
        List<Order> orders = new ArrayList<>();
        for (Integer i = 0; i < 5; i++) {
            Order temp = new Order();
            temp.setCode(code);
            temp.setId(i);
            temp.setType(i % 2 == 0 ? Order.BUY : Order.SELL);
            temp.setAmount(12);
            temp.setPrice(1.59f);
            orders.add(temp);
        }
        return new OrderWrapper(orders);
    }
}
