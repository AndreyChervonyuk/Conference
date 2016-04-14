package com.edu.chdtu.conference.controller;

import com.edu.chdtu.conference.model.Event;
import com.edu.chdtu.conference.model.dto.EventDto;
import com.edu.chdtu.conference.model.enums.Status;
import com.edu.chdtu.conference.service.EventService;
import com.edu.chdtu.conference.service.MemberService;
import com.edu.chdtu.conference.service.UserGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/events")
public class EventController {

    final static Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    EventService eventService;

    @Autowired
    MemberService memberService;

    @Autowired
    UserGroupService userGroupService;

    @RequestMapping(value = "/available", method = RequestMethod.GET)
    public ModelAndView getAvailableEvents(ModelAndView model) {
        model.addObject("events", eventService.findAllBy("status", Status.AVAILABLE));
        model.setViewName("main");
        return model;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView getEventPage(@PathVariable("id") Integer id,
                                 ModelAndView model) {
        model.addObject("currentEventId", id);
        model.addObject("event", eventService.findById(id));
        model.addObject("userGroup", userGroupService.getUserGroup(id, SecurityContextHolder.getContext().getAuthentication()));
        model.setViewName("event");
        return model;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String eventCreationPage() {
        return "eventCreation";
    }


    @PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public void createEvent(@RequestBody EventDto eventDto) {
        eventService.create(eventDto);
    }

    @RequestMapping(value = "event/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Event getEvent(@PathVariable("id") Integer id) {
        return eventService.findById(id);
    }
}
