package com.edu.chdtu.conference.dao;

import com.edu.chdtu.conference.dao.core.GenericDao;
import com.edu.chdtu.conference.model.Notification;

import java.util.List;


public interface NotificationDao extends GenericDao<Notification, Integer> {
    Notification findById(Integer eventId, Integer id);
}
