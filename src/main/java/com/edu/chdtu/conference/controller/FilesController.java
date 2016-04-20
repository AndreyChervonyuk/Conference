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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.AccessControlException;
import java.security.Principal;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/files")
public class FilesController {

    @Autowired
    DocumentsService documentsService;

    @Autowired
    UserService userService;

    @Autowired
    ImageService imageService;

    @Autowired
    EventDocumentsService eventDocumentsService;

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
    public List<EventDocument> getEventDocuments(@PathVariable("eventId") Integer eventId) {
        return eventDocumentsService.findAllBy("event.id", eventId);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> saveMultipleFile(@RequestParam("file") MultipartFile[] files,
                                                   Principal principal) throws IOException {
        String message = "";
        User user = userService.findByEmail(principal.getName());
        FileUtil.createUserFolder(user.getEmail());

        for (MultipartFile file : files) {
            String relativePath = FileUtil.getRelativePath(user.getEmail(), file.getOriginalFilename());
            String path = FileUtil.getAbsolutePath(user.getEmail(), file.getOriginalFilename());
            boolean isImage = ImageValidator.validate(file.getOriginalFilename());

            try (BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(path))) {
                buffStream.write(file.getBytes());

                if (isImage) {
                    Image image = new Image(file.getOriginalFilename(), relativePath, new Date(), user);
                    imageService.create(image);
                } else {
                    Document documents = new Document(file.getOriginalFilename(), relativePath, new Date(), user);
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
    public ResponseEntity<byte[]> downloadFile(@PathVariable("id") Integer id,
                                               @RequestParam(required = false) Integer eventId) throws IOException {
        boolean canDownload;

        if(eventId == null) {
            canDownload = eventDocumentsService.canDownload(id);
        } else {
            canDownload = eventDocumentsService.canDownload(id, eventId);
        }

        if(!canDownload) {
            throw new AccessDeniedException("Access denied");
        }

        Document document = documentsService.findById(id);


        if (document == null) {
            throw new FileNotFoundException("File not found in db");
        }

        Path path = FileUtil.relativeToAbsolute(document.getPath());

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
