package Objects.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {
    @Autowired
    HttpSession session;

    @GetMapping("/private/objects")
    public String mainObjects(Model m){
        String user = (String) session.getAttribute("username");
        session.setAttribute("user",user);
        return "objects";
    }
}
