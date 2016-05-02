package se.manage;

import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes({"curStatus", "curUser"})
public interface Sessionable {
    String Status = "curStatus";
    String User = "curUser";
}
