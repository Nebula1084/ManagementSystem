package se.manage.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import se.manage.Sessionable;

@Controller
public class ManagerView implements Sessionable {
    public static final String errorPage = "error.html";
    public static final String indexPage = "index.html";
    public static final String loginPage = "login.html";
    public static final String registerPage = "register.html";
    public static final String addStockPage = "add_stock.html";
    public static final String profilePage = "profile.html";
    public static final String orderPage = "order.html";

    @RequestMapping(value = {"/error"}, method = {RequestMethod.GET})
    public String error() {
        return errorPage;
    }
}
