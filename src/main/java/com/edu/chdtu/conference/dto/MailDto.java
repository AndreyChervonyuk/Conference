package com.edu.chdtu.conference.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class MailDto {

    private String subject;
    private String text;
    private Integer eventId;

    @JsonProperty("setTo[]")
    private String[] setTo;

    @JsonProperty("fileAttachment[]")
    private Integer[] fileAttachment;

    public MailDto() {
    }

    public MailDto(String subject, String text, Integer eventId, String[] setTo, Integer[] fileAttachment) {
        this.subject = subject;
        this.text = text;
        this.eventId = eventId;
        this.setTo = setTo;
        this.fileAttachment = fileAttachment;
    }

    public MailDto(String subject, String text, Integer eventId, String[] setTo) {
        this.subject = subject;
        this.text = text;
        this.eventId = eventId;
        this.setTo = setTo;
    }

    public MailDto(String subject, String text, Integer eventId, Integer[] fileAttachment) {
        this.subject = subject;
        this.text = text;
        this.eventId = eventId;
        this.fileAttachment = fileAttachment;
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

    public String[] getSetTo() {
        return setTo;
    }

    public void setSetTo(String[] setTo) {
        this.setTo = setTo;
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
}
