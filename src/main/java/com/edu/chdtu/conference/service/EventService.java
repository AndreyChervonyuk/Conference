package com.edu.chdtu.conference.service;

import com.edu.chdtu.conference.service.core.GenericService;
import com.edu.chdtu.conference.model.Event;
import com.edu.chdtu.conference.model.dto.EventDto;
import org.springframework.web.multipart.MultipartFile;


public interface EventService extends GenericService<Event, Integer> {
    Integer create(EventDto eventDto);
}
