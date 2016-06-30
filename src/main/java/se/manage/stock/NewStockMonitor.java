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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class NewStockMonitor extends Thread {
    private Logger logger = Logger.getLogger(NewStockMonitor.class);
    private final static String allUrl = "https://se.clarkok.com/center/stock/all";
    private ObjectMapper objectMapper;
    private RestTemplate restTemplate;
    private NewStockWrapper stockMarket;
    private Map<String, VolatileStock> stockInfo;
    private List<VolatileStock> volatileStocks;

    @Autowired
    public DataFaker dataFaker;

    public NewStockMonitor() {
        System.setProperty("javax.net.ssl.trustStore", this.getClass().getResource("/jssecacerts").getPath());
        System.out.println(this.getClass().getResource("/jssecacerts").getPath());
        System.out.println(System.getProperty("user.dir"));
        objectMapper = new ObjectMapper();
        restTemplate = new RestTemplate();
        stockInfo = new HashMap<>();
        volatileStocks = new LinkedList<>();
        this.start();
    }

    @Override
    public void run() {
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (true) {
            synchronized (this) {
                try {
                    String ret = restTemplate.postForObject(allUrl, null, String.class);
                    stockMarket = objectMapper.readValue(ret, NewStockWrapper.class);
                    stockInfo.clear();
                    volatileStocks.clear();
                    stockMarket.getStocks().stream().forEach(e -> {
                        VolatileStock volatileStock = new VolatileStock();
                        volatileStock.setCode(e.get("code"));
                        Float openning_prce, surging_price, decline_price;
                        openning_prce = Float.valueOf(e.get("opening_price"));
                        surging_price = Float.valueOf(e.get("surging_range"));
                        decline_price = Float.valueOf(e.get("decline_range"));
                        volatileStock.setSurging_range((surging_price - openning_prce)/openning_prce);
                        volatileStock.setDecline_range((openning_prce - decline_price)/openning_prce);
                        volatileStock.setHighest_price(Float.valueOf(e.get("highest_price")));
                        volatileStock.setLeast_price(Float.valueOf(e.get("lowest_price")));
                        volatileStock.setPrice(Float.valueOf(e.get("price")));
                        volatileStock.setState(e.get("pause").equals("false") ? "normal" : "pause");
                        volatileStocks.add(volatileStock);
                        stockInfo.put(e.get("code"), volatileStock);
                    });
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
        System.out.println(volatileStocks);
        return volatileStocks;
    }

    public Map<String, VolatileStock> getStockInfo() {
        return stockInfo;
    }
}
