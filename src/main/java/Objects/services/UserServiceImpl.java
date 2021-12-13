package Objects.services;

import Objects.daos.UserDAO;
import Objects.model.User;
import Objects.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDAO userDAO;

    @Autowired
    Utils utils;

    @Override
    public boolean checkLogin(String username, String password) {
        User u = userDAO.getUser(username);
        if (u == null) return false;
        return utils.ConvertToSHA256(password).equals(u.getPassword());
    }

    @Override
    public void register(User user) {
        String password = utils.ConvertToSHA256(user.getPassword());
        user.setPassword(password);
        userDAO.newUser(user);
    }
}
