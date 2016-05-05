package se.manage.view;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.context.WebContext;
import se.manage.ManageStatus;
import se.manage.Sessionable;
import se.manage.order.Order;
import se.manage.order.OrderRetriver;
import se.manage.stock.StockMonitor;
import se.manage.stock.VolatileStock;
import se.manage.user.User;

import java.io.IOException;
import java.util.*;

@Controller
public class OrderView implements Sessionable {

    @Autowired
    StockMonitor stockMonitor;

    private Logger logger = Logger.getLogger(OrderView.class);

    @RequestMapping(value = {"order/{code}.html"}, method = {RequestMethod.GET})
    public String indexPage(@ModelAttribute(Sessionable.User) User user, @ModelAttribute(Sessionable.Status) ManageStatus status,
                            @PathVariable String code, Model model) {
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
            Collections.sort(orders, (o1, o2) -> {
                int ret = 0;
                switch (status.orderAtr) {
                    case ManageStatus.atr_amount:
                        ret = o1.getAmount() - o2.getAmount();
                        break;
                    case ManageStatus.atr_price:
                        ret = (o1.getPrice() - o1.getPrice() > 0) ? 1 : 0;
                        break;
                    case ManageStatus.atr_time:
                        ret = o1.timestamp.compareTo(o2.timestamp);
                        break;
                }
                switch (status.orderOrd) {
                    case ManageStatus.ord_ascend:
                        break;
                    case ManageStatus.ord_desced:
                        ret = -ret;
                        break;
                }
                return ret;
            });
            model.addAttribute("orders", orders);
        } catch (IOException e) {
            model.addAttribute("orders", new ArrayList<Order>());
            e.printStackTrace();
        }

        return ManagerView.orderPage;
    }
}
