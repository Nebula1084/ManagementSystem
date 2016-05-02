package se.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import se.manage.view.ManagerView;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class ViewTest {
    @Autowired
    ManagerView managerView;
    private MockMvc mockMvc;


    public void setup() {
        this.mockMvc = standaloneSetup(this.managerView).build();
    }

    public void test() throws Exception {
        this.mockMvc.perform(get("/")).andExpect(forwardedUrl("login.html")).andDo(print());
        this.mockMvc.perform(get("/index")).andExpect(forwardedUrl("index.html")).andDo(print());
        this.mockMvc.perform(get("/register")).andExpect(forwardedUrl("register.html")).andDo(print());
    }
}
