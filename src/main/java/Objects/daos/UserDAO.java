package Objects.daos;

import Objects.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {
    User getUser(String username);
    void newUser(User user);
}
