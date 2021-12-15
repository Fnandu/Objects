package Objects.services;

import Objects.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public boolean checkLogin(String username, String password);
    public void register(User user);
    public User getInfoUser(String user);
}