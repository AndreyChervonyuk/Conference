package com.edu.chdtu.conference.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    final static Logger logger = LoggerFactory.getLogger(UserController.class);


    @RequestMapping("/")
    public String getMainPage() {
        return "redirect:/events/available";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationPage(){
        return "registration";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              HttpServletRequest request,
                              ModelAndView model)	{

        if (error != null) {

            Exception exception = (Exception) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
            String errorMsg;

            if (exception instanceof BadCredentialsException) {
                errorMsg = "Invalid username and password!";
            } else if (exception instanceof LockedException) {
                errorMsg = exception.getMessage();
            } else {
                errorMsg = "Invalid username and password!";
            }

            model.addObject("error", errorMsg);

        }

        model.setViewName("login");
        return model;
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accessDenied(ModelAndView model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("user", userDetail.getUsername());
        }

        model.setViewName("403");
        return model;
    }

}
