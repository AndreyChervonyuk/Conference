package com.edu.chdtu.conference.controller;


import com.edu.chdtu.conference.model.dto.PermissionDto;
import com.edu.chdtu.conference.service.EventPermissionService;
import com.edu.chdtu.conference.model.dto.EventPermissionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    EventPermissionService permissionService;

    @PreAuthorize("hasPermission(#eventId, 'view_permission')")
    @RequestMapping(value = "/{eventId}", method = RequestMethod.GET)
    public EventPermissionDto getEventPermission(@PathVariable("eventId") Integer eventId) {
        return permissionService.getEventPermissionDto(eventId);
    }


    @PreAuthorize("hasPermission(#permissionDto.eventId, 'edit_permission')")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void updatePermission(@RequestBody PermissionDto permissionDto) {
        permissionService.updatePermission(permissionDto);
    }

    @RequestMapping(value = "/user/{eventId}", method = RequestMethod.GET)
    public EventPermissionDto getEvent(@PathVariable("eventId") Integer eventId) {
        return permissionService.getUserPermissionDto(eventId, SecurityContextHolder.getContext().getAuthentication());
    }

    //TODO refactor
    @RequestMapping(value = "/default", method = RequestMethod.GET)
    public EventPermissionDto getDefault() throws IOException {
       return permissionService.getEventPermissionDto(18);
    }

}
