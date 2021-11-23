package Objects.controller;

import Objects.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

public class RegisterController {

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String login() {
        return "register";
    }
}
