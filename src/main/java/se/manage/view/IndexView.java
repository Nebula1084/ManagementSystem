package se.manage.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import se.manage.Sessionable;
import se.manage.stock.Stock;
import se.manage.stock.StockService;
import se.manage.user.User;

@Controller
public class IndexView implements Sessionable {
    @Autowired
    StockService stockService;

    @RequestMapping(value = {ManagerView.indexPage}, method = {RequestMethod.GET})
    public String indexPage(Model model) {
        model.addAttribute("user", new User());
        return ManagerView.indexPage;
    }

    @ModelAttribute("stocks")
    public List<Stock> getStocks() {
        List stocks = this.stockService.getStocks();
        return stocks;
    }
}
