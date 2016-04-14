package com.edu.chdtu.conference.dao;

import com.edu.chdtu.conference.dao.core.GenericDao;
import com.edu.chdtu.conference.model.EventPermission;
import com.edu.chdtu.conference.model.dto.PermissionDto;

import java.util.List;
import java.util.Set;

public interface EventPermissionDao extends GenericDao<EventPermission, Integer> {
    List<EventPermission> getPermissionByUserGroups(Integer eventId, Set<String> groups);
    EventPermission getByEventId(Integer eventId, Integer permissionId);
    EventPermission getEventPermission(Integer eventId, String permission, Set<String> userGroups);
}
