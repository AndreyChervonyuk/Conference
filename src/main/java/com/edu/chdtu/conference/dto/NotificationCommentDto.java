package com.edu.chdtu.conference.dto;

public class NotificationCommentDto {

    Integer notificationId;
    String text;
    Integer eventId;
    Integer reply;

    public NotificationCommentDto() {
    }

    public NotificationCommentDto(Integer notificationId, String text, Integer eventId) {
        this.notificationId = notificationId;
        this.text = text;
        this.eventId = eventId;
    }

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getReply() {
        return reply;
    }

    public void setReply(Integer reply) {
        this.reply = reply;
    }
}
