package com.edu.chdtu.conference.dto;


public class PermissionDto {

    Integer permissionId;
    Integer userGroupId;
    Integer eventId;

    public PermissionDto() {
    }

    public PermissionDto(Integer permissionId, Integer userGroupId, Integer eventId) {
        this.permissionId = permissionId;
        this.userGroupId = userGroupId;
        this.eventId = eventId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Integer getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }
}

