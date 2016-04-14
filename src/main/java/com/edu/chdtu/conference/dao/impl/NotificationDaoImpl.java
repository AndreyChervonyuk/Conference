package com.edu.chdtu.conference.dao.impl;

import com.edu.chdtu.conference.dao.NotificationDao;
import com.edu.chdtu.conference.dao.core.GenericDaoImpl;
import com.edu.chdtu.conference.model.Notification;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotificationDaoImpl extends GenericDaoImpl<Notification, Integer> implements NotificationDao {

    public NotificationDaoImpl() {
        super(Notification.class);
    }

    @Override
    public Notification findById(Integer eventId, Integer id) {
        return (Notification) createCriteria()
                .createAlias("event", "e")
                .add(Restrictions.eq("id", id))
                .add(Restrictions.eq("e.id", eventId))
                .uniqueResult();
    }
}
