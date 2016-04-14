package com.edu.chdtu.conference.service.impl;


import com.edu.chdtu.conference.dao.EventPermissionDao;
import com.edu.chdtu.conference.dao.PermissionDao;
import com.edu.chdtu.conference.dao.UserGroupsDao;
import com.edu.chdtu.conference.model.*;
import com.edu.chdtu.conference.model.dto.PermissionDto;
import com.edu.chdtu.conference.service.EventPermissionService;
import com.edu.chdtu.conference.service.UserGroupService;
import com.edu.chdtu.conference.dao.core.GenericDao;
import com.edu.chdtu.conference.model.dto.EventPermissionDto;
import com.edu.chdtu.conference.service.core.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventPermissionServiceImpl extends GenericServiceImpl<EventPermission, Integer> implements EventPermissionService {

    private EventPermissionDao eventPermissionDao;
    private PermissionDao permissionDao;
    private UserGroupsDao userGroupsDao;
    private UserGroupService userGroupService;


    @Autowired
    public EventPermissionServiceImpl(GenericDao<EventPermission, Integer> genericDao,
                                      UserGroupsDao userGroupsDao,
                                      UserGroupService userGroupService,
                                      PermissionDao permissionDao) {
        super(genericDao);
        this.eventPermissionDao = (EventPermissionDao) genericDao;
        this.userGroupsDao = userGroupsDao;
        this.permissionDao = permissionDao;
        this.userGroupService = userGroupService;
    }


    @Override
    public EventPermissionDto getUserPermissionDto(Integer eventId, Authentication authentication) {
        String group = userGroupService.getUserGroup(eventId, authentication);
        List<EventPermission> permissions =  eventPermissionDao.getPermissionByUserGroups(eventId, userGroupService.getHierarchyGroups(group));

        return new EventPermissionDto(
                permissions.stream()
                        .collect(Collectors.groupingBy(permission -> permission.getPermission().getCategory().getName()))
        );
    }

    @Override
    public EventPermissionDto getEventPermissionDto(Integer eventId) {
        List<EventPermission> permissions = eventPermissionDao.findAllBy("event.id", eventId);

        return new EventPermissionDto(
                permissions.stream()
                        .collect(Collectors.groupingBy(permission -> permission.getPermission().getCategory().getName()))
        );
    }

    @Override
    public Set<EventPermission> buildPermission(Event event, Map<String, String> permissions) {
        Set<EventPermission> eventPermissions = new HashSet<>();

        for (Permission permission : permissionDao.findAll()) {
            EventPermission eventPermission = new EventPermission(
                    event,
                    permission,
                    userGroupsDao.findById(Integer.valueOf(permissions.get(permission.getName())))
            );

            eventPermissions.add(eventPermission);
        }

        return eventPermissions;
    }

    @Override
    public void updatePermission(PermissionDto permissionDto) {
        EventPermission permission = eventPermissionDao.getByEventId(permissionDto.getEventId(), permissionDto.getPermissionId());
        permission.setUserGroup(userGroupsDao.findById(permissionDto.getUserGroupId()));
        eventPermissionDao.update(permission);
    }

    @Override
    public boolean hasPermission(Integer eventId, Authentication authentication, String permission) {
        String userGroup = userGroupService.getUserGroup(eventId, authentication);
        EventPermission eventPermission = eventPermissionDao.getEventPermission(
                eventId,
                permission,
                userGroupService.getHierarchyGroups(userGroup));
        return eventPermission != null;
    }
}

