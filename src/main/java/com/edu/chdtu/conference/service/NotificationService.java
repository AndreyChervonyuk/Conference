package com.edu.chdtu.conference.service;


import com.edu.chdtu.conference.dto.NotificationDto;
import com.edu.chdtu.conference.service.core.GenericService;
import com.edu.chdtu.conference.model.Notification;

public interface NotificationService extends GenericService<Notification, Integer> {
    Notification create(NotificationDto notificationDto);
    Notification findById(Integer eventId, Integer id);
}
