package Objects.controller;

import Objects.model.User;
import Objects.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String user, @RequestParam String password,
                           @RequestParam String passwordConfirmation, @RequestParam String firstname,
                           @RequestParam String lastname, Model m) {

        if (!password.equals(passwordConfirmation)) {
            m.addAttribute("message", "Passwords does not match");
            return "register";
        } else {
            User u = new User();
            u.setUsername(user);
            u.setPassword(password);
            u.setFirstname(firstname);
            u.setLastname(lastname);
            userService.register(u);
            m.addAttribute("message", "Registration successful, you can login");
            return "login";
        }

    }
}
