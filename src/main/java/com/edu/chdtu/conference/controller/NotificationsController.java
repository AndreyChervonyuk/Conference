package com.edu.chdtu.conference.controller;

import com.edu.chdtu.conference.model.Notification;
import com.edu.chdtu.conference.dto.NotificationDto;
import com.edu.chdtu.conference.service.EventDocumentsService;
import com.edu.chdtu.conference.service.EventPermissionService;
import com.edu.chdtu.conference.service.EventService;
import com.edu.chdtu.conference.service.NotificationService;
import com.edu.chdtu.conference.service.impl.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationsController {

    @Autowired
    NotificationService notificationService;

    @Autowired
    EventService eventService;

    @Autowired
    MailSenderService mailSenderService;

    @Autowired
    EventPermissionService eventPermissionService;

    @PreAuthorize("hasPermission(#eventId, 'view_notifications')")
    @RequestMapping(value ="event/{eventId}", method = RequestMethod.GET)
    public List<Notification> getNotifications(@PathVariable("eventId") Integer eventId) {
        return notificationService.findAllBy("event.id", eventId);
    }

    @RequestMapping(value ="/{id}", method = RequestMethod.GET)
    public Notification getNotification(@PathVariable("id") Integer id) {
        return notificationService.findBy("id", id);
    }

    @PreAuthorize("hasPermission(#notificationDto.eventId, 'create_notification')")
    @RequestMapping(method = RequestMethod.POST)
    public Notification create(@RequestBody NotificationDto notificationDto) {
        Notification notification = notificationService.create(notificationDto);

        if (notificationDto.getSendMail() != null ) {
            mailSenderService.sendNotification(notificationDto);
        }

        return notification;
    }

}
