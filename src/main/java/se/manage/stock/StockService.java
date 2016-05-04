package se.manage.stock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.thymeleaf.context.WebContext;
import se.manage.stock.Stock;

@Service
public class StockService {
    public StockService() {
    }



    public List<VolatileStock> getStocks() {
        List<VolatileStock> stocks = new ArrayList<>();

        for (int i = 0; i < 4; ++i) {
            stocks.add(new VolatileStock());
        }
        return stocks;
    }
}
