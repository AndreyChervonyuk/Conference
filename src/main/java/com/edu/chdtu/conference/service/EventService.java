package com.edu.chdtu.conference.service;

import com.edu.chdtu.conference.service.core.GenericService;
import com.edu.chdtu.conference.model.Event;
import com.edu.chdtu.conference.dto.EventDto;


public interface EventService extends GenericService<Event, Integer> {
    Integer create(EventDto eventDto);
}
