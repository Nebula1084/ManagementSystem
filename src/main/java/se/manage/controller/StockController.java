package se.manage.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import se.manage.Sessionable;
import se.manage.stock.Stock;
import se.manage.user.User;
import se.manage.user.UserService;
import se.manage.view.AddStockView;
import se.manage.view.ManagerView;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

@Controller
public class StockController implements Sessionable {
    private Logger logger = Logger.getLogger(StockController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = "/stock/edit.do", method = RequestMethod.POST)
    public String editStock(HttpServletRequest httpRequest, @ModelAttribute(Sessionable.User) User user) {
        Enumeration<String> parameters;
        parameters = httpRequest.getParameterNames();
        Set<Stock> stocks = new HashSet<>();
        while (parameters.hasMoreElements()) {
            stocks.add(new Stock(parameters.nextElement()));
        }
        user.setStocks(stocks);
        userService.save(user);
        return "redirect:/" + ManagerView.addStockPage;
    }

}
