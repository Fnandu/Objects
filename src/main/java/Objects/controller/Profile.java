package Objects.controller;

import Objects.model.User;
import Objects.services.ObjectsService;
import Objects.services.UserService;
import Objects.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class Profile {

    @Autowired
    HttpSession session;

    @Autowired
    UserService userService;

    @Autowired
    Utils utils;

    @GetMapping("/profile")
    public String profile(Model m){
        String user = (String) session.getAttribute("username");
        session.setAttribute("user",user);
        session.setAttribute("info",userService.getInfoUser(user));
        return "profile";
    }

    @PostMapping("/profile")
    public String profile(){
        return "profile";
    }
}
