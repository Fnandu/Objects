package Objects.controller;

import Objects.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    HttpSession session;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public RedirectView login(Model m, @RequestParam String username, @RequestParam String password) {
        if (userService.checkLogin(username, password)) {
            session.removeAttribute("message");
            session.setAttribute("username", username);
            return new RedirectView("/private/objects");
        } else {
            session.setAttribute("message", "Usuari o password incorrectes");
        }
        return new RedirectView("/login");

    }

    @GetMapping("/logout")
    public RedirectView logout() {
        session.removeAttribute("username");
        session.removeAttribute("auth");
        return new RedirectView("/login");
    }
}
