package com.edu.chdtu.conference.service.impl;

import com.edu.chdtu.conference.dao.DocumentsDao;
import com.edu.chdtu.conference.dao.EventDao;
import com.edu.chdtu.conference.dao.NotificationDao;
import com.edu.chdtu.conference.dao.UserDao;
import com.edu.chdtu.conference.dao.core.GenericDao;
import com.edu.chdtu.conference.model.Notification;
import com.edu.chdtu.conference.model.NotificationDocument;
import com.edu.chdtu.conference.model.dto.NotificationDto;
import com.edu.chdtu.conference.service.NotificationService;
import com.edu.chdtu.conference.service.core.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class NotificationServiceImpl extends GenericServiceImpl<Notification, Integer> implements NotificationService {

    private NotificationDao notificationDao;
    private UserDao userDao;
    private EventDao eventDao;
    private DocumentsDao documentsDao;

    @Autowired
    public NotificationServiceImpl(GenericDao<Notification, Integer> genericDao,
                                   UserDao userDao,
                                   EventDao eventDao,
                                   DocumentsDao documentsDao) {
        super(genericDao);
        this.notificationDao = (NotificationDao) genericDao;
        this.userDao = userDao;
        this.eventDao = eventDao;
        this.documentsDao = documentsDao;
    }

    //TODO change set documents
    @Override
    public Notification create(NotificationDto notificationDto) {

        Notification notification = new Notification(
                notificationDto.getSubject(),
                notificationDto.getText(),
                userDao.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()),
                new Date(),
                eventDao.findById(notificationDto.getEventId())
        );


        if(notificationDto.getFileAttachment() != null) {
            for (Integer documentsId : notificationDto.getFileAttachment()) {
                notification.getDocuments().add(new NotificationDocument(
                        documentsDao.findById(documentsId),
                        notification
                ));
            }
        }

        notificationDao.create(notification);
        return notification;
    }


    @Override
    public Notification findById(Integer eventId, Integer id) {
        return notificationDao.findById(eventId, id);
    }
}
