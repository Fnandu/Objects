package Objects.controller;

import Objects.model.Bucket;
import Objects.model.Objects_Versions;
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
        String user = (String) session.getAttribute("username");
        m.addAttribute("bucket", bucket);
        m.addAttribute("object_list",objectsService.ObjectList(bucket,user));
        return "viewObject";
    }

    @PostMapping("/private/objects/{bucket}")
    public RedirectView uri(@RequestParam String bucket_path, @RequestParam MultipartFile file, @RequestParam String actual_path){
        String fileName = file.getOriginalFilename();
        String user = (String) session.getAttribute("username");
        LocalDate localDate = LocalDate.now();

        Objects_Versions objectsVersions = new Objects_Versions();
        try {
            objectsVersions.setFileId(0);
            objectsVersions.setFileName(fileName);
            objectsVersions.setFileType(file.getContentType());
            objectsVersions.setFileData(file.getBytes());
            objectsVersions.setFileSize(file.getSize());
            objectsVersions.setFileDate(String.valueOf(localDate));
            objectsVersions.setFileUri(actual_path);
            objectsVersions.setFileUsername(user);

            objectsService.CreateObject(objectsVersions);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return new RedirectView("/private/objects/" + actual_path);
    }

    @GetMapping("/private/objects/{bucket}/{object}")
    public String object(Model m, @PathVariable String bucket, @PathVariable String object){
        m.addAttribute("bucket", bucket);
        m.addAttribute("object", object);

        return "viewObjectVersion";
    }
}
