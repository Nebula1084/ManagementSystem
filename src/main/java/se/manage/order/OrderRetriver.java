package se.manage.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;
import se.manage.controller.PostTemplate;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class OrderRetriver {
    private String code;
    private final static String ordUrl = "https://se.clarkok.com/center/order/code";
    private ObjectMapper objectMapper;

    public OrderRetriver(String code) {
        this.code = code;
        this.objectMapper = new ObjectMapper();
    }

    public List<Order> getOrders() throws IOException {
        PostTemplate postTemplate = new PostTemplate();
        postTemplate.add("code", code);
        System.out.println(code);
        RestTemplate restTemplate = new RestTemplate();
        NewOrderWrapper wrapper;
        wrapper = objectMapper.readValue(restTemplate.getForObject(ordUrl + "?code=" + code, String.class), NewOrderWrapper.class);
        List<Order> orders= new LinkedList<>();
        wrapper.getOrders().forEach(e->{
            Order  order=new Order();
            order.setId(Integer.valueOf(e.get("id")));
            order.setCode(code);
            order.setType(e.get("is_buying").equals("0") ? "sell" : "buy");
            order.setPrice(Float.valueOf(e.get("price")));
            order.setAmount(Integer.valueOf(e.get("amount")));
            orders.add(order);
        });
        return orders;
    }
}
