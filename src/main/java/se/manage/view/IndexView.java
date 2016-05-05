package se.manage.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.context.WebContext;
import se.manage.Sessionable;
import se.manage.stock.Stock;
import se.manage.stock.StockMonitor;
import se.manage.stock.StockService;
import se.manage.stock.VolatileStock;
import se.manage.user.User;

@Controller
public class IndexView implements Sessionable {
    @Autowired
    StockMonitor stockMonitor;

    @RequestMapping(value = {ManagerView.indexPage}, method = {RequestMethod.GET})
    public String indexPage(@ModelAttribute(Sessionable.User) User user, Model model) {
        List<VolatileStock> stocks = stockMonitor.getStockMarket();
        if (false) {
            WebContext context = new org.thymeleaf.context.WebContext(null, null, null);
            context.setVariable("stocks", stocks);
        }
        List<VolatileStock> temp;
        temp = new ArrayList<>();
        Set<Stock> userStocks = user.getStocks();
        stocks.stream().forEach(e -> {
            VolatileStock stock = e.clone();
            if (userStocks != null) {
                for (Stock g : userStocks) {
                    if (Objects.equals(g.getCode(), stock.getCode())) {
                        temp.add(stock);
                        break;
                    }
                }
            }
        });
        model.addAttribute("stocks", temp);
        return ManagerView.indexPage;
    }

}
