package se.manage.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import se.manage.controller.PostTemplate;

import java.io.IOException;
import java.util.List;

import static se.manage.stock.StockMonitor.baseUrl;

public class OrderRetriver {
    private String code;
    private final static String ordUrl = baseUrl + "/info/order/code";
    private ObjectMapper objectMapper;

    public OrderRetriver(String code) {
        this.code = code;
        this.objectMapper = new ObjectMapper();
    }

    public List<Order> getOrders() throws IOException {
        PostTemplate postTemplate = new PostTemplate();
        postTemplate.add("code", code);
        String result = postTemplate.post(ordUrl);
        OrderWrapper wrapper = objectMapper.readValue(result, OrderWrapper.class);
        return wrapper.getOrders();
    }
}
