package com.edu.chdtu.conference.controller;



import com.edu.chdtu.conference.model.Document;
import com.edu.chdtu.conference.model.Member;
import com.edu.chdtu.conference.model.User;
import com.edu.chdtu.conference.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;


@Controller
@RequestMapping("/user")
public class UserController {

	final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
	UserService userService;

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ResponseBody
	public ResponseEntity registration(@RequestBody User user) {
        try {
            userService.create(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
	}

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String getAccountPage() {
        return "account";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/members", method = RequestMethod.GET)
    @ResponseBody
    public Set<Member> getMyEvent(Principal principal) {
        return userService.findById(principal.getName()).getMember();
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/documents", method = RequestMethod.GET)
    @ResponseBody
    public Set<Document> getMyDocuments(Principal principal) {
        return userService.findById(principal.getName()).getDocuments();
    }

}