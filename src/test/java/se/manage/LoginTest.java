package se.manage;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import se.manage.user.User;
import se.manage.user.UserRepository;
import se.manage.user.UserService;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LoginTest extends ManageTest {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    public LoginTest() {
    }

    @Before
    public void before() {
        User user = new User("user", "123", "123");
        this.userService.register(user);
    }

    @Test
    public void test() {
        User user = new User("user", "123", "123");
        assertThat(this.userService.login(user), is(true));
        user.setPassword("124");
        assertThat(this.userService.login(user), is(false));
        user = new User("user1", "123", "123");
        assertThat(this.userService.login(user), is(false));
    }
}
