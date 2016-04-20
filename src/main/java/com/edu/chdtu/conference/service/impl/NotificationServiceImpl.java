package com.edu.chdtu.conference.service.impl;

import com.edu.chdtu.conference.dao.*;
import com.edu.chdtu.conference.dao.core.GenericDao;
import com.edu.chdtu.conference.model.*;
import com.edu.chdtu.conference.dto.NotificationDto;
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
    private EventDocumentsDao eventDocumentsDao;

    @Autowired
    public NotificationServiceImpl(GenericDao<Notification, Integer> genericDao,
                                   UserDao userDao,
                                   EventDao eventDao,
                                   DocumentsDao documentsDao,
                                   EventDocumentsDao eventDocumentsDao) {
        super(genericDao);
        this.notificationDao = (NotificationDao) genericDao;
        this.userDao = userDao;
        this.eventDao = eventDao;
        this.documentsDao = documentsDao;
        this.eventDocumentsDao = eventDocumentsDao;
    }

    @Override
    public Notification create(NotificationDto notificationDto) {
        Event event = eventDao.findById(notificationDto.getEventId());

        Notification notification = new Notification(
                notificationDto.getSubject(),
                notificationDto.getText(),
                userDao.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()),
                new Date(),
                event
        );


        if(notificationDto.getFileAttachment() != null) {
            for (Integer documentsId : notificationDto.getFileAttachment()) {
                Document document = documentsDao.findById(documentsId);

                if (document != null) {
                    EventDocument eventDocument = eventDocumentsDao.findBy("document.id", document.getId());

                    if (eventDocument == null) {
                        eventDocument = new EventDocument(document, event);
                    }

                    notification.getDocuments().add(new NotificationDocument(
                            eventDocument,
                            notification
                    ));
                }
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
