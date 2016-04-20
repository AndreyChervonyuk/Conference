package com.edu.chdtu.conference.service;


import com.edu.chdtu.conference.model.DefaultEventPermission;
import com.edu.chdtu.conference.model.Event;
import com.edu.chdtu.conference.dto.PermissionDto;
import com.edu.chdtu.conference.service.core.GenericService;
import com.edu.chdtu.conference.model.EventPermission;
import com.edu.chdtu.conference.dto.EventPermissionDto;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface EventPermissionService extends GenericService<EventPermission, Integer> {
    List<EventPermission> getByUser(Integer eventId, Authentication authentication);
    List<DefaultEventPermission> getDefault();
    Set<EventPermission> buildPermission(Event event, Map<String, String> permissions);
    void update(PermissionDto permissionDto);
    boolean hasPermission(Integer eventId, Authentication authentication, String permission);
}
