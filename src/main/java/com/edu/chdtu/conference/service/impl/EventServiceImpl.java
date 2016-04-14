package com.edu.chdtu.conference.service.impl;

import com.edu.chdtu.conference.dao.EventDao;
import com.edu.chdtu.conference.dao.UserDao;
import com.edu.chdtu.conference.dao.UserGroupsDao;
import com.edu.chdtu.conference.model.*;
import com.edu.chdtu.conference.model.enums.Group;
import com.edu.chdtu.conference.model.enums.Status;
import com.edu.chdtu.conference.service.EventPermissionService;
import com.edu.chdtu.conference.service.EventService;
import com.edu.chdtu.conference.dao.core.GenericDao;
import com.edu.chdtu.conference.model.dto.EventDto;
import com.edu.chdtu.conference.service.ImageService;
import com.edu.chdtu.conference.service.core.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class EventServiceImpl extends GenericServiceImpl<Event, Integer> implements EventService {

    private EventDao eventDao;
    private UserGroupsDao userGroupsDao;
    private UserDao userDao;
    private EventPermissionService permissionService;


    @Autowired
    ImageService imageService;

    @Autowired
    public EventServiceImpl(GenericDao<Event, Integer> genericDao,
                            UserGroupsDao userGroupsDao,
                            UserDao userDao,
                            EventPermissionService permissionService) {
        super(genericDao);
        this.eventDao = (EventDao) genericDao;
        this.userGroupsDao = userGroupsDao;
        this.userDao = userDao;
        this.permissionService = permissionService;
    }

    @Override
    public Integer create(EventDto eventDto) {
        Event event = eventDto.getEvent();
        User creator = userDao.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Set<Member> members = new HashSet<>();

        Member eventMembers = new Member(
                creator,
                event,
                userGroupsDao.findBy("name", Group.GROUP_ADMINS.name())
        );

        members.add(eventMembers);

        if(eventDto.getPoster() != null) {
            Image poster = imageService.findById(eventDto.getPoster());
            event.setPosterPath(poster.getPath());
        }

        event.setCreteDate(new Date());
        event.setCreator(creator);
        event.setStatus(Status.AVAILABLE);
        event.setPermissions(permissionService.buildPermission(event, eventDto.getEventPermission()));
        event.setMembers(members);
        return eventDao.create(event);
    }

}
