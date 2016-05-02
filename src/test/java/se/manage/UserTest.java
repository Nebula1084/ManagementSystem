package se.manage;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import se.manage.ManageTest;
import se.manage.MyContextUtil;
import se.manage.user.UserRepository;
import se.manage.user.UserService;

import static org.junit.Assert.assertThat;

public class UserTest extends ManageTest {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    public UserTest() {
    }

    @Test
    public void test() {
        System.out.println("test");
        this.userService.register("sdf", "sdf", "asdf");
        MyContextUtil.getBean("userService");
        assertThat(this.userRepository.findByAccount("sdf").size(), CoreMatchers.is(1));
    }
}
