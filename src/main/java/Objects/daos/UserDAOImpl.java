package Objects.daos;

import Objects.model.User;
import jdk.jfr.Registered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User getUser(String username) {
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE username=?",
                new BeanPropertyRowMapper<User>(User.class),
                username);
        if (users.get(0) == null) return null;

        return users.get(0);
    }
}
