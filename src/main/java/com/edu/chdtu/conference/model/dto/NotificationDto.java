package com.edu.chdtu.conference.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class NotificationDto {

    private String subject;
    private String text;
    private Integer eventId;

    @JsonProperty("fileAttachment[]")
    private Integer[] fileAttachment;

    private String sendMail;

    public NotificationDto() {
    }

    public NotificationDto(String subject, String text, Integer eventId) {
        this.subject = subject;
        this.text = text;
        this.eventId = eventId;
    }

    public NotificationDto(String subject, String text, Integer[] fileAttachment, Integer eventId) {
        this.subject = subject;
        this.text = text;
        this.fileAttachment = fileAttachment;
        this.eventId = eventId;
    }


    public NotificationDto(String subject, String text, Integer[] fileAttachment, Integer eventId, String sendMail) {
        this.subject = subject;
        this.text = text;
        this.fileAttachment = fileAttachment;
        this.eventId = eventId;
        this.sendMail = sendMail;
    }

    public NotificationDto(String subject, String text, Integer eventId, String sendMail) {
        this.subject = subject;
        this.text = text;
        this.eventId = eventId;
        this.sendMail = sendMail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer[] getFileAttachment() {
        return fileAttachment;
    }

    public void setFileAttachment(Integer[] fileAttachment) {
        this.fileAttachment = fileAttachment;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getSendMail() {
        return sendMail;
    }

    public void setSendMail(String sendMail) {
        this.sendMail = sendMail;
    }
}
