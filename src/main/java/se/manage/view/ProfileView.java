package se.manage.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import se.manage.Sessionable;
import se.manage.user.User;

@Controller
public class ProfileView implements Sessionable {

    @RequestMapping(value = {ManagerView.profilePage}, method = {RequestMethod.GET})
    public String indexPage(@ModelAttribute(Sessionable.User) User user) {
        return ManagerView.profilePage;
    }
}
