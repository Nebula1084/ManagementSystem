package se.manage.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import se.manage.ManageStatus;
import se.manage.Sessionable;
import se.manage.user.User;
import se.manage.user.UserService;
import se.manage.user.UserValidator;

import javax.validation.Valid;

@Controller
public class UserController implements Sessionable {
    @Autowired
    UserService userService;
    private Logger logger = Logger.getLogger(UserController.class);


    @InitBinder({"user"})
    public void initBinder(DataBinder binder) {
        binder.setValidator(new UserValidator());
    }

    @RequestMapping(value = {"/login.do"}, method = {RequestMethod.POST}
    )
    public String login(@Valid @ModelAttribute("user") User user, BindingResult result,
                        @ModelAttribute(Sessionable.Status) ManageStatus status, Model model) {
        if (result.hasErrors()) {
            this.logger.info(result);
            status.status = 2;
            return "redirect:login.html";
        } else if (this.userService.login(user)) {
            this.logger.info(user + " login sucessed");
            status.status = 0;
            model.addAttribute(Sessionable.User, user);
            return "redirect:index.html";
        } else {
            this.logger.info(user + " login failed");
            status.status = 1;
            return "redirect:login.html";
        }
    }

    @RequestMapping(
            value = {"/register.do"},
            method = {RequestMethod.POST}
    )
    public String register(@Valid @ModelAttribute("user") User user, BindingResult result,
                           @ModelAttribute(Sessionable.Status) ManageStatus status, Model model) {
        if (result.hasErrors()) {
            this.logger.info(result);
            status.status = 2;
            return "redirect:register.html";
        } else if (this.userService.register(user)) {
            this.logger.info(user + " register successes");
            status.status = 0;
            model.addAttribute(Sessionable.User, user);
            return "redirect:index.html";
        } else {
            this.logger.info(user + " register failed");
            status.status = 3;
            return "redirect:register.html";
        }
    }
}
