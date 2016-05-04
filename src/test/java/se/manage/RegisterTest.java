package se.manage;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import se.manage.ManageTest;
import se.manage.user.User;
import se.manage.user.UserRepository;
import se.manage.user.UserService;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class RegisterTest extends ManageTest {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    public RegisterTest() {
    }

    @Before
    public void before() {
        User user = new User("user", "123", "123");
        this.userService.register(user);
    }

    @Test
    public void test() {
        User user = new User("user1", "123", "123");
        assertThat(this.userService.register(user)!=null, is(true));
        user = new User("user1", "123", "123");
        assertThat(this.userService.register(user)!=null, is(false));
    }
}
