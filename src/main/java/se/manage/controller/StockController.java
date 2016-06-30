package se.manage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import se.manage.ManageStatus;
import se.manage.Sessionable;
import se.manage.stock.NewStockMonitor;
import se.manage.stock.Stock;
import se.manage.stock.VolatileStock;
import se.manage.user.User;
import se.manage.user.UserService;
import se.manage.view.ManagerView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

@Controller
public class StockController implements Sessionable {
    private Logger logger = Logger.getLogger(StockController.class);
    public final static String baseUrl = "http://localhost:9000";
    private final static String pauseUrl = "https://se.clarkok.com/center/pause";
    private final static String restartUrl = "https://se.clarkok.com/center/restart";
    private final static String surgingUrl = "https://se.clarkok.com/center/limit/surging";
    private final static String declineUrl = "https://se.clarkok.com/center/limit/decline";
    private ObjectMapper objectMapper;

    @Autowired
    UserService userService;

    @Autowired
    NewStockMonitor stockMonitor;

    public StockController() {
        this.objectMapper = new ObjectMapper();
    }

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

    @RequestMapping(value = "/stock/state/{code}.do", method = RequestMethod.GET)
    public String changeState(@PathVariable("code") String code, @ModelAttribute(Sessionable.User) User user) {
        VolatileStock stock = stockMonitor.getStockInfo().get(code);
        switch (stock.getState()) {
            case VolatileStock.NORMAL:
                if (parseReturn(postState(pauseUrl, code)))
                    stock.setState(VolatileStock.PAUSE);
                break;
            case VolatileStock.PAUSE:
                if (parseReturn(postState(restartUrl, code)))
                    stock.setState(VolatileStock.NORMAL);
                break;
        }
        return "redirect:/order/" + code + ".html";
    }

    @RequestMapping(value = "/stock/limit/surging/{code}.do", method = RequestMethod.POST)
    public String limitSurging(@RequestParam(value = "limit") String limit, @PathVariable("code") String code) {
        Float l = Float.valueOf(limit);
        if (parseReturn(postLimit(surgingUrl, code, String.valueOf(l / 100))))
            stockMonitor.getStockInfo().get(code).setSurging_range_set(l / 100);
        return "redirect:/order/" + code + ".html";
    }

    @RequestMapping(value = "/stock/limit/decline/{code}.do", method = RequestMethod.POST)
    public String limitDecline(@RequestParam(value = "limit") String limit, @PathVariable("code") String code) {
        Float l = Float.valueOf(limit);
        if (parseReturn(postLimit(declineUrl, code, String.valueOf(l / 100))))
            stockMonitor.getStockInfo().get(code).setDecline_range_set(l / 100);
        return "redirect:/order/" + code + ".html";
    }

    @RequestMapping(value = "/order/sort/atr/{code}/{atr}")
    public String changeAtr(@PathVariable("code") String code, @PathVariable("atr") int atr, @ModelAttribute(Sessionable.Status) ManageStatus status) {
        status.orderAtr = atr;
        return "redirect:/order/" + code + ".html";
    }

    @RequestMapping(value = "/order/sort/ord/{code}/{ord}")
    public String changeOrd(@PathVariable("code") String code, @PathVariable("ord") int ord, @ModelAttribute(Sessionable.Status) ManageStatus status) {
        status.orderOrd = ord;
        return "redirect:/order/" + code + ".html";
    }

    private String postLimit(String url, String code, String limit) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url + "?code=" + code + "&limit=" + limit, String.class);
    }

    private String postState(String url, String code) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url + "?code=" + code, String.class);
    }

    private boolean parseReturn(String ret) {
        try {
            Success success = objectMapper.readValue(ret, Success.class);
            if (success.getState().equals("ok"))
                return true;
            else
                return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
