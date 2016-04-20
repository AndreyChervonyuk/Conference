package com.edu.chdtu.conference.service.impl;


import com.edu.chdtu.conference.dao.DefaultEventPermissionDao;
import com.edu.chdtu.conference.dao.EventPermissionDao;
import com.edu.chdtu.conference.dao.PermissionDao;
import com.edu.chdtu.conference.dao.UserGroupsDao;
import com.edu.chdtu.conference.model.*;
import com.edu.chdtu.conference.dto.PermissionDto;
import com.edu.chdtu.conference.service.EventPermissionService;
import com.edu.chdtu.conference.service.UserGroupService;
import com.edu.chdtu.conference.dao.core.GenericDao;
import com.edu.chdtu.conference.service.core.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EventPermissionServiceImpl extends GenericServiceImpl<EventPermission, Integer> implements EventPermissionService {

    private EventPermissionDao eventPermissionDao;
    private PermissionDao permissionDao;
    private UserGroupsDao userGroupsDao;
    private UserGroupService userGroupService;
    private DefaultEventPermissionDao defaultEventPermissionDao;


    @Autowired
    public EventPermissionServiceImpl(GenericDao<EventPermission, Integer> genericDao,
                                      UserGroupsDao userGroupsDao,
                                      UserGroupService userGroupService,
                                      PermissionDao permissionDao,
                                      DefaultEventPermissionDao defaultEventPermissionDao) {
        super(genericDao);
        this.eventPermissionDao = (EventPermissionDao) genericDao;
        this.userGroupsDao = userGroupsDao;
        this.permissionDao = permissionDao;
        this.userGroupService = userGroupService;
        this.defaultEventPermissionDao = defaultEventPermissionDao;
    }


    @Override
    public List<EventPermission> getByUser(Integer eventId, Authentication authentication) {
        String group = userGroupService.getUserGroup(eventId, authentication);
        return eventPermissionDao.getPermissionByUserGroups(eventId, userGroupService.getHierarchyGroups(group));
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
    public void update(PermissionDto permissionDto) {
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

    @Override
    public List<DefaultEventPermission> getDefault() {
        return defaultEventPermissionDao.findAll();
    }
}

