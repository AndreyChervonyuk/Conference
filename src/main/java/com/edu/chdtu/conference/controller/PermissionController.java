package com.edu.chdtu.conference.controller;


import com.edu.chdtu.conference.dto.PermissionDto;
import com.edu.chdtu.conference.dto.conterver.EntityToDto;
import com.edu.chdtu.conference.model.DefaultEventPermission;
import com.edu.chdtu.conference.model.EventPermission;
import com.edu.chdtu.conference.service.EventPermissionService;
import com.edu.chdtu.conference.dto.EventPermissionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    EventPermissionService permissionService;

    @PreAuthorize("hasPermission(#eventId, 'view_permission')")
    @RequestMapping(value = "/{eventId}", method = RequestMethod.GET)
    public EventPermissionDto getEventPermission(@PathVariable("eventId") Integer eventId) {
        List<EventPermission> permissions = permissionService.findAllBy("event.id", eventId);
        return EntityToDto.getEventPermissionDto(permissions);
    }


    @PreAuthorize("hasPermission(#permissionDto.eventId, 'edit_permission')")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void updatePermission(@RequestBody PermissionDto permissionDto) {
        permissionService.update(permissionDto);
    }

    @RequestMapping(value = "/user/{eventId}", method = RequestMethod.GET)
    public EventPermissionDto getEvent(@PathVariable("eventId") Integer eventId) {
        List<EventPermission> permissions = permissionService.getByUser(eventId, SecurityContextHolder.getContext().getAuthentication());
        return EntityToDto.getEventPermissionDto(permissions);
    }

    @RequestMapping(value = "/default", method = RequestMethod.GET)
    public EventPermissionDto getDefault() throws IOException {
        List<DefaultEventPermission> permissions = permissionService.getDefault();
        return EntityToDto.getDefaultEventPermissionDto(permissions);
    }

}
