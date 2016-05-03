package se.manage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import se.manage.view.ManagerView;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = App.class)
public class ViewTest {
    @Autowired
    ManagerView managerView;
    private MockMvc mockMvc;


    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(this.managerView).build();
    }

    @Test
    public void test() throws Exception {
//        this.mockMvc.perform(get("/")).andExpect(forwardedUrl("login.html")).andDo(print());
//        this.mockMvc.perform(get("/index.html")).andExpect(forwardedUrl("/")).andDo(print());
//        this.mockMvc.perform(get("/register.html")).andExpect(forwardedUrl("register.html")).andDo(print());
    }
}
