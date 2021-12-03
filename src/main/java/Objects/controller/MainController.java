package Objects.controller;

import Objects.model.Bucket;
import Objects.services.ObjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class MainController {
    @Autowired
    HttpSession session;

    @Autowired
    ObjectsService objectsService;

    @GetMapping("/private/objects")
    public String mainObjects(Model m){
        String user = (String) session.getAttribute("username");
        session.setAttribute("user",user);

        m.addAttribute("bucket",objectsService.BucketList(user));

        return "objects";
    }

    @PostMapping("/private/objects")
    public RedirectView mainObjects(@RequestParam String bucket_name){
        String user = (String) session.getAttribute("username");
        LocalDate localDate = LocalDate.now();

        Bucket bucket = new Bucket();
        bucket.setUri(bucket_name);
        bucket.setUser(user);
        bucket.setDate(String.valueOf(localDate));
        System.out.println(bucket.getUri() + " " + bucket.getUser()+ " " + bucket.getDate());
        objectsService.CreateBucket(bucket);

        return new RedirectView("/private/objects");
    }
}
