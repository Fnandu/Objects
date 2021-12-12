package Objects.controller;

import Objects.model.Bucket;
import Objects.model.Objects;
import Objects.services.ObjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
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
        objectsService.CreateBucket(bucket);

        return new RedirectView("/private/objects");
    }

    @GetMapping("/private/objects/{bucket}")
    public String uri(Model m, @PathVariable String bucket){
        m.addAttribute("bucket", bucket);
        return "viewObject";
    }

    @PostMapping("/private/objects/{bucket}")
    public RedirectView uri(@RequestParam String bucket_path, @RequestParam MultipartFile file, @RequestParam String actual_path){
        String fileName = file.getOriginalFilename();
        String user = (String) session.getAttribute("username");
        LocalDate localDate = LocalDate.now();

        Objects objects = new Objects();
        try {
            objects.setFileId(0);
            objects.setFileName(fileName);
            objects.setFileType(file.getContentType());
            objects.setFileData(file.getBytes());
            objects.setFileSize(file.getSize());
            objects.setFileDate(String.valueOf(localDate));
            objects.setFileUri(actual_path);
            objects.setFileUsername(user);

            objectsService.CreateObject(objects);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return new RedirectView("/private/objects/" + actual_path);
    }

    @GetMapping("/private/objects/{bucket}/{object}")
    public String object(Model m, @PathVariable String bucket, @PathVariable String object){

        return "objectVersion";
    }
}
