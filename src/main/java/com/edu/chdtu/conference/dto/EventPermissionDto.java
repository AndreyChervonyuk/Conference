package com.edu.chdtu.conference.dto;


import com.edu.chdtu.conference.model.EventPermission;

import java.util.List;
import java.util.Map;

public class EventPermissionDto {

    private Map<String, List<EventPermission>> permission;

    public EventPermissionDto(Map<String, List<EventPermission>> permission) {
        this.permission = permission;
    }


    public Map<String, List<EventPermission>> getPermission() {
        return permission;
    }

    public void setPermission(Map<String, List<EventPermission>> permission) {
        this.permission = permission;
    }
}
