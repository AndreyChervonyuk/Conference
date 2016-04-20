package com.edu.chdtu.conference.service.impl;

import com.edu.chdtu.conference.dao.EventDocumentsDao;
import com.edu.chdtu.conference.dao.core.GenericDao;
import com.edu.chdtu.conference.model.EventDocument;
import com.edu.chdtu.conference.service.EventDocumentsService;
import com.edu.chdtu.conference.service.EventPermissionService;
import com.edu.chdtu.conference.service.core.GenericService;
import com.edu.chdtu.conference.service.core.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class EventDocumentsServiceImpl extends GenericServiceImpl<EventDocument, Integer> implements EventDocumentsService {

    private EventDocumentsDao eventDocumentsDao;
    private EventPermissionService eventPermissionService;

    @Autowired
    public EventDocumentsServiceImpl(GenericDao<EventDocument, Integer> genericDao, EventPermissionService eventPermissionService) {
        super(genericDao);
        this.eventDocumentsDao = (EventDocumentsDao) genericDao;
        this.eventPermissionService = eventPermissionService;
    }


    @Override
    public boolean canDownload(Integer documentId, Integer eventId) {
        boolean hasPermission = eventPermissionService.hasPermission(eventId, SecurityContextHolder.getContext().getAuthentication(), "view_documents");

        if(hasPermission) {
            EventDocument document = eventDocumentsDao.findBy("document.id", documentId);
            if(document != null) {
                return document.getEvent().getId().equals(eventId);
            }
        }

        return false;
    }

    @Override
    public boolean canDownload(Integer documentId) {
        EventDocument document = eventDocumentsDao.findBy("document.id", documentId);

        if(document != null) {
            return document.getDocument().getUser().getEmail().equals(SecurityContextHolder.getContext().getAuthentication().getName());
        }

        return false;
    }
}
