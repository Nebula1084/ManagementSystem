package se.manage.user;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import se.manage.user.User;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByAccount(String var1);
}
