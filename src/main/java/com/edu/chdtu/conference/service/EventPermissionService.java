package com.edu.chdtu.conference.service;


import com.edu.chdtu.conference.model.Event;
import com.edu.chdtu.conference.model.dto.PermissionDto;
import com.edu.chdtu.conference.service.core.GenericService;
import com.edu.chdtu.conference.model.EventPermission;
import com.edu.chdtu.conference.model.dto.EventPermissionDto;
import org.springframework.security.core.Authentication;

import java.util.Map;
import java.util.Set;


public interface EventPermissionService extends GenericService<EventPermission, Integer> {
    EventPermissionDto getUserPermissionDto(Integer eventId, Authentication authentication);
    EventPermissionDto getEventPermissionDto(Integer eventId);
    Set<EventPermission> buildPermission(Event event, Map<String, String> permissions);
    void updatePermission(PermissionDto permissionDto);
    boolean hasPermission(Integer eventId, Authentication authentication, String permission);
}
