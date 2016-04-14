package com.edu.chdtu.conference.service;


import com.edu.chdtu.conference.model.dto.NotificationDto;
import com.edu.chdtu.conference.service.core.GenericService;
import com.edu.chdtu.conference.model.Notification;

import java.util.List;

public interface NotificationService extends GenericService<Notification, Integer> {
    Notification create(NotificationDto notificationDto);
    Notification findById(Integer eventId, Integer id);
}
