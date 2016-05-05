package se.manage.view;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.context.WebContext;
import se.manage.Sessionable;
import se.manage.controller.UserController;
import se.manage.stock.Stock;
import se.manage.stock.StockMonitor;
import se.manage.stock.VolatileStock;
import se.manage.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Controller
public class AddStockView implements Sessionable {

    @Autowired
    StockMonitor stockMonitor;

    private Logger logger = Logger.getLogger(AddStockView.class);

    @RequestMapping(value = {ManagerView.addStockPage}, method = {RequestMethod.GET})
    public String page(@ModelAttribute(Sessionable.User) User user, Model model) {
        List<VolatileStock> market = this.stockMonitor.getStockMarket();
        List<VolatileStock> temp = new ArrayList<>();
        Set<Stock> userStocks = user.getStocks();
        market.stream().forEach(e -> {
            VolatileStock stock = e.clone();
            if (userStocks != null) {
                for (Stock g : userStocks) {
                    if (Objects.equals(g.getCode(), stock.getCode())) {
                        stock.setChecked(true);
                        break;
                    }
                }
            }
            temp.add(stock);
        });
        model.addAttribute("market", temp);
        return ManagerView.addStockPage;
    }

    @ModelAttribute("market")
    public List<VolatileStock> getMarket() {
        List<VolatileStock> market = this.stockMonitor.getStockMarket();

        if (false) {
            WebContext context = new org.thymeleaf.context.WebContext(null, null, null);
            context.setVariable("market", market);
        }
        return market;
    }
}
