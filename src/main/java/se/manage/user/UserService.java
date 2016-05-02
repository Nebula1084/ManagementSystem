package se.manage.user;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.manage.user.User;
import se.manage.user.UserRepository;

@Service("userService")
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserService() {
    }

    @Transactional
    public boolean register(String account, String password, String nickName) {
        User user = new User(account, password, nickName);
        return this.register(user);
    }

    @Transactional
    public boolean register(User user) {
        List users = this.userRepository.findByAccount(user.getAccount());
        if(users.isEmpty()) {
            this.userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public boolean login(User user) {
        List<User> users = this.userRepository.findByAccount(user.getAccount());
        return !users.isEmpty() && Objects.equals(user.getPassword(), users.get(0).getPassword());
    }
}
