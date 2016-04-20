package com.edu.chdtu.conference.service;


import com.edu.chdtu.conference.dto.NotificationCommentDto;
import com.edu.chdtu.conference.service.core.GenericService;
import com.edu.chdtu.conference.model.Comment;

public interface CommentsService extends GenericService<Comment, Integer> {
    Comment createNotificationComment(NotificationCommentDto notificationCommentDto);
}
