package se.manage.stock;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import se.manage.controller.DataFaker;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StockMonitor extends Thread {
    private Logger logger = Logger.getLogger(StockMonitor.class);
    public final static String baseUrl = "http://localhost:9000";
    private final static String allUrl = baseUrl + "/info/stock/all";
    private ObjectMapper objectMapper;
    private RestTemplate restTemplate;
    private StockWrapper stockMarket;
    private Map<String, VolatileStock> stockInfo;

    @Autowired
    public DataFaker dataFaker;

    public StockMonitor() {
        objectMapper = new ObjectMapper();
        restTemplate = new RestTemplate();
        stockInfo = new HashMap<>();
        this.start();
    }

    @Override
    public void run() {
        try {
            sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (true) {
            synchronized (this) {
                try {
                    String ret = restTemplate.postForObject(allUrl, null, String.class);
                    stockMarket = objectMapper.readValue(ret, StockWrapper.class);
                    stockInfo.clear();
                    stockMarket.getStocks().stream().forEach(e -> stockInfo.put(e.getCode(), e));
                } catch (JsonParseException e) {
                    logger.info(e);
                    e.printStackTrace();
                } catch (JsonMappingException e) {
                    logger.info(e);
                    e.printStackTrace();
                } catch (IOException e) {
                    logger.info(e);
                    e.printStackTrace();
                }
            }
            try {
                sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized List<VolatileStock> getStockMarket() {
        return stockMarket.getStocks();
    }

    public Map<String, VolatileStock> getStockInfo() {
        return stockInfo;
    }
}
