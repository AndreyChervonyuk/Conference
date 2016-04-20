package com.edu.chdtu.conference.service.impl;

import com.edu.chdtu.conference.dao.CommentsDao;
import com.edu.chdtu.conference.dao.NotificationDao;
import com.edu.chdtu.conference.dao.UserDao;
import com.edu.chdtu.conference.dao.core.GenericDao;
import com.edu.chdtu.conference.dto.NotificationCommentDto;
import com.edu.chdtu.conference.model.Comment;
import com.edu.chdtu.conference.model.Notification;
import com.edu.chdtu.conference.model.NotificationComment;
import com.edu.chdtu.conference.service.CommentsService;
import com.edu.chdtu.conference.service.core.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class CommentsServiceImpl extends GenericServiceImpl<Comment, Integer> implements CommentsService {

    private CommentsDao commentsDao;
    private NotificationDao notificationDao;
    private UserDao userDao;

    @Autowired
    public CommentsServiceImpl(GenericDao<Comment, Integer> genericDao,
                               NotificationDao notificationDao,
                               UserDao userDao) {
        super(genericDao);
        this.commentsDao = (CommentsDao) genericDao;
        this.notificationDao = notificationDao;
        this.userDao = userDao;
    }

    @Override
    public Comment createNotificationComment(NotificationCommentDto notificationCommentDto) {
        Notification notification = notificationDao.findById(notificationCommentDto.getEventId(), notificationCommentDto.getNotificationId());

        Comment comment = new Comment(
                userDao.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()),
                notificationCommentDto.getText(),
                new Date()
        );

        NotificationComment notificationComment = new NotificationComment(
                comment,
                notification
        );

        Set<NotificationComment> notificationComments = new HashSet<>();
        notificationComments.add(notificationComment);
        notification.setComments(notificationComments);

        notificationDao.update(notification);

        return comment;
    }
}
