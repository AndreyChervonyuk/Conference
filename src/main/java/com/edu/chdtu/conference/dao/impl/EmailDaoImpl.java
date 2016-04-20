package com.edu.chdtu.conference.dao.impl;

import com.edu.chdtu.conference.dao.EmailDao;
import com.edu.chdtu.conference.dao.core.GenericDaoImpl;
import com.edu.chdtu.conference.model.Email;
import org.springframework.stereotype.Repository;

@Repository
public class EmailDaoImpl extends GenericDaoImpl<Email, Integer> implements EmailDao{

    public EmailDaoImpl() {
        super(Email.class);
    }
}
