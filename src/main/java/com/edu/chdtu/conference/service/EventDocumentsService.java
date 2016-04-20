package com.edu.chdtu.conference.service;


import com.edu.chdtu.conference.model.EventDocument;
import com.edu.chdtu.conference.service.core.GenericService;

public interface EventDocumentsService extends GenericService<EventDocument, Integer> {
    boolean canDownload(Integer documentId, Integer eventId);
    boolean canDownload(Integer documentId);
}
