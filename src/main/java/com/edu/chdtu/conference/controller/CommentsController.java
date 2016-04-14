package com.edu.chdtu.conference.controller;

import com.edu.chdtu.conference.model.Comment;
import com.edu.chdtu.conference.model.NotificationComment;
import com.edu.chdtu.conference.service.CommentsService;
import com.edu.chdtu.conference.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    CommentsService commentsService;

    @Autowired
    NotificationService notificationService;

    @PreAuthorize("hasPermission(#eventId, 'view_notification_comments')")
    @RequestMapping(value = "notification/{eventId}/{id}", method = RequestMethod.GET)
    public Set<NotificationComment> getNotificationComments(@PathVariable("id") Integer id,
                                                            @PathVariable("eventId") Integer eventId) {
        return notificationService.findById(eventId, id).getComments();
    }

    @PreAuthorize("hasPermission(#eventId, 'create_notification_comments')")
    @RequestMapping(value = "/notification", method = RequestMethod.POST)
    public Comment createNotificationComment(@RequestParam Integer notificationId,
                                             @RequestParam String text,
                                             @RequestParam Integer eventId) {
        return commentsService.create(notificationId, text);
    }

}
