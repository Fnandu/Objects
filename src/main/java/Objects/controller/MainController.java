package Objects.controller;

import Objects.model.Bucket;
import Objects.model.Objects;
import Objects.model.Version;
import Objects.services.ObjectsService;
import Objects.utils.Utils;
import jdk.internal.loader.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import java.util.Arrays;

@Controller
public class MainController {
    @Autowired
    HttpSession session;

    @Autowired
    ObjectsService objectsService;

    @Autowired
    Utils utils;

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

        Objects objects = new Objects();
        Version version = new Version();
        try {
            //Comprobamos si no existe antes
            if(objectsService.GetId(actual_path,user,fileName) == null) {
                //Creacion del objeto
                objects.setFileId(0);
                objects.setFileName(fileName);
                objects.setFileType(file.getContentType());
                objects.setFileData(file.getBytes());
                objects.setFileSize(utils.convertToGB(file.getSize()));
                objects.setFileDate(String.valueOf(localDate));
                objects.setFileUri(actual_path);
                objects.setFileUsername(user);

                System.out.println(utils.convertToGB(file.getSize()));

                objectsService.CreateObject(objects);
            }

            //Obtener el valor de id de dicha version
            objects = objectsService.GetId(actual_path,user,fileName);

            //Creacion de su primera version
            version.setVersionId(0);
            version.setVersionName(fileName);
            version.setFileData(file.getBytes());
            version.setVersionSize(utils.convertToGB(file.getSize()));
            version.setVersionDate(String.valueOf(localDate));
            version.setVersionHash(String.valueOf(Arrays.hashCode(file.getBytes())));
            version.setFileid(objects.getFileId());
            objectsService.CreateVersion(version);

            session.setAttribute("message", "");

        } catch (IOException e) {
            e.printStackTrace();
        }


        return new RedirectView("/private/objects/" + actual_path);
    }

    @GetMapping("/private/objects/{bucket}/{object}")
    public String object(Model m, @PathVariable String bucket, @PathVariable String object){
        m.addAttribute("bucket", bucket);
        m.addAttribute("object", object);
        String user = (String) session.getAttribute("username");
        Objects objects = objectsService.GetId(bucket,user,object);

        m.addAttribute("version_list", objectsService.VersionList(objects.getFileId()));

        return "viewObjectVersion";
    }

    @PostMapping("/private/objects/{bucket}/{object}")
    public RedirectView object(@RequestParam MultipartFile file, @RequestParam String actual_path, @RequestParam String actual_object){
        String fileName = file.getOriginalFilename();
        LocalDate localDate = LocalDate.now();
        String user = (String) session.getAttribute("username");

        try {
            Objects objects = objectsService.GetId(actual_path,user,fileName);
            if(objectsService.GetId(actual_path,user,fileName) != null && actual_object.equals(fileName)) {
                Version version = new Version();
                version.setVersionId(0);
                version.setVersionName(fileName);
                version.setVersionDate(String.valueOf(localDate));
                version.setVersionSize(utils.convertToGB(file.getSize()));
                version.setFileid(objects.getFileId());
                version.setFileData(file.getBytes());
                version.setVersionHash(String.valueOf(Arrays.hashCode(file.getBytes())));
                objectsService.CreateVersion(version);
            } else {
                session.setAttribute("message", "No es el mismo archivo");
                return new RedirectView("/private/objects/" + actual_path + "/" + actual_object);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new RedirectView("/private/objects/" + actual_path + "/" + fileName);
    }

    @GetMapping("/download/{id}")
    public String downloadFile(@PathVariable String id){
        return "viewObjectVersion";
    }

    @PostMapping("/delete/{bucket}")
    public RedirectView deleteBucket(@RequestParam String delete_bucket){
        String user = (String) session.getAttribute("username");
        objectsService.DeleteBucket(delete_bucket,user);
        return new RedirectView("/private/objects");
    }

    @PostMapping("/delete/{object}")
    public RedirectView deleteObject(@RequestParam int fileid,@RequestParam String path){
        objectsService.DeleteObject(fileid);
        return new RedirectView("/private/objects/"+path);
    }

}