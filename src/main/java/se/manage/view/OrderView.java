package se.manage.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.context.WebContext;
import se.manage.Sessionable;
import se.manage.order.Order;
import se.manage.order.OrderRetriver;
import se.manage.stock.StockMonitor;
import se.manage.stock.VolatileStock;
import se.manage.user.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderView {

    @Autowired
    StockMonitor stockMonitor;

    @RequestMapping(value = {"order/{code}.html"}, method = {RequestMethod.GET})
    public String indexPage(@ModelAttribute(Sessionable.User) User user, @PathVariable String code, Model model) {
        OrderRetriver retriver = new OrderRetriver(code);
        List<Order> orders;
        VolatileStock stock = stockMonitor.getStockInfo().get(code);
        if (false) {
            WebContext context = new org.thymeleaf.context.WebContext(null, null, null);
            context.setVariable("orders", orders);
            context.setVariable("stock", stock);
        }
        model.addAttribute("stock", stock);
        try {
            orders = retriver.getOrders();
            model.addAttribute("orders", orders);
        } catch (IOException e) {
            model.addAttribute("orders", new ArrayList<Order>());
            e.printStackTrace();
        }

        return ManagerView.orderPage;
    }
}
