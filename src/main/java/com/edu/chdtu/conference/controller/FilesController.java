package com.edu.chdtu.conference.controller;

import com.edu.chdtu.conference.model.*;
import com.edu.chdtu.conference.service.*;
import com.edu.chdtu.conference.util.FileUtil;
import com.edu.chdtu.conference.util.ImageValidator;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/files")
public class FilesController {

    private static String ROOT_DIRECTORY = "C:\\temp";
    private static String USER_DIRECTORY = "\\user";

    @Autowired
    DocumentsService documentsService;

    @Autowired
    EventService eventService;

    @Autowired
    UserService userService;

    @Autowired
    NotificationService notificationService;

    @Autowired
    ImageValidator imageValidator;

    @Autowired
    ImageService imageService;

    @Autowired
    FileUtil fileUtil;

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String uploadPage() {
        return "upload";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public List<Document> getUserDocuments(Principal principal) {
        return documentsService.findAllBy("user.email", principal.getName());
    }

    @RequestMapping(value = "/event/{eventId}", method = RequestMethod.GET)
    @ResponseBody
    public Set<Document> getEventDocuments(@PathVariable("eventId") Integer eventId) {
        List<Notification> notifications = notificationService.findAllBy("event.id", eventId);
        Set<Document> documents = new HashSet<>();

        for (Notification notification : notifications) {
            for (NotificationDocument notificationDocument : notification.getDocuments()) {
                documents.add(notificationDocument.getDocument());
            }
        }
        return documents;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> saveMultipleFile(@RequestParam("file") MultipartFile[] files,
                                                   Principal principal) throws IOException {

        String message = "";
        User user = userService.findByEmail(principal.getName());
        fileUtil.createUserFolder(ROOT_DIRECTORY + USER_DIRECTORY + File.separator + user.getEmail() + File.separator);

        for (MultipartFile file : files) {
            String filePath = USER_DIRECTORY + File.separator + user.getEmail() + File.separator + file.getOriginalFilename();
            String path = ROOT_DIRECTORY + USER_DIRECTORY + File.separator + user.getEmail() + File.separator + file.getOriginalFilename();
            boolean isImage = imageValidator.validate(file.getOriginalFilename());


            try (BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(path))) {
                buffStream.write(file.getBytes());

                if (isImage) {
                    Image image = new Image(file.getOriginalFilename(), filePath, new Date(), user);
                    imageService.create(image);
                } else {
                    Document documents = new Document(file.getOriginalFilename(), filePath, new Date(), user);
                    documentsService.create(documents);
                }


                message += "You have successfully uploaded " + file.getOriginalFilename() + "<br/>";
            } catch (Exception e) {
                message += "You failed to upload " + file.getOriginalFilename() + ": " + e.getMessage() + "<br/>";
                return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> downloadFile(@PathVariable("id") Integer id) throws IOException {
        Document document = documentsService.findById(id);

        if (document == null) {
            throw new FileNotFoundException("File not found in db");
        }


        Path path = Paths.get(ROOT_DIRECTORY + document.getPath());

        if(Files.exists(path)) {

            HttpHeaders headers = new HttpHeaders();
            headers.setContentDispositionFormData(document.getName(), document.getName());

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentLength(Files.size(path))
                    .contentType(MediaType.parseMediaType(Files.probeContentType(path)))
                    .body(IOUtils.toByteArray(Files.newInputStream(path)));
        } else {
            throw new FileNotFoundException("File not found in file system");
        }
    }
}
