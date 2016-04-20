package com.edu.chdtu.conference.dao.impl;

import com.edu.chdtu.conference.dao.EventDocumentsDao;
import com.edu.chdtu.conference.dao.core.GenericDaoImpl;
import com.edu.chdtu.conference.model.EventDocument;
import org.springframework.stereotype.Repository;

@Repository
public class EventDocumentsDaoImpl extends GenericDaoImpl<EventDocument, Integer> implements EventDocumentsDao {

    public EventDocumentsDaoImpl() {
        super(EventDocument.class);
    }
}
