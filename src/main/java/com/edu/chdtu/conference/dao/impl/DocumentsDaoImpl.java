package com.edu.chdtu.conference.dao.impl;


import com.edu.chdtu.conference.dao.DocumentsDao;
import com.edu.chdtu.conference.model.Document;
import com.edu.chdtu.conference.dao.core.GenericDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentsDaoImpl extends GenericDaoImpl<Document, Integer> implements DocumentsDao {

    public DocumentsDaoImpl() {
        super(Document.class);
    }
}
