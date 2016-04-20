package com.edu.chdtu.conference.dto;


public class MemberDto {

    Integer eventId;
    Integer userGroupId;
    String userEmail;

    public MemberDto() {
    }

    public MemberDto(Integer eventId, Integer userGroupId, String userEmail) {
        this.eventId = eventId;
        this.userGroupId = userGroupId;
        this.userEmail = userEmail;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
