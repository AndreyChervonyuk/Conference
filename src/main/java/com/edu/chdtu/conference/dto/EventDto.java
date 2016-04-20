package com.edu.chdtu.conference.dto;


import com.edu.chdtu.conference.model.Event;

import java.util.Map;

public class EventDto {
    private Event event;
    private Integer poster;
    private Map<String, String> eventPermission;


    public EventDto(Event event, Map<String, String> eventPermission) {
        this.event = event;
        this.eventPermission = eventPermission;
    }

    public EventDto(Event event, Integer poster, Map<String, String> eventPermission) {
        this.event = event;
        this.poster = poster;
        this.eventPermission = eventPermission;
    }

    public EventDto() {
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Map<String, String> getEventPermission() {
        return eventPermission;
    }

    public void setEventPermission(Map<String, String> eventPermission) {
        this.eventPermission = eventPermission;
    }

    public Integer getPoster() {
        return poster;
    }

    public void setPoster(Integer poster) {
        this.poster = poster;
    }
}
