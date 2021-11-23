package Objects.services;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public boolean checkLogin(String username, String password);

}
