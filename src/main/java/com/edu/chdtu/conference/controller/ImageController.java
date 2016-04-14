package com.edu.chdtu.conference.controller;

import com.edu.chdtu.conference.model.Image;
import com.edu.chdtu.conference.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    ImageService imageService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public List<Image> getUserDocuments(Principal principal) {
        return imageService.findAllBy("user.email", principal.getName());
    }
}
