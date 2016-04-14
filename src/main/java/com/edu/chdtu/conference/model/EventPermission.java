package com.edu.chdtu.conference.model;

import javax.persistence.*;

@Entity
@Table(name = "event_permission")
public class EventPermission {

    @Id
    @GeneratedValue()
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "permission_id", nullable = false)
    private Permission permission;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_group_id", nullable = false)
    private UserGroup userGroup;

    public EventPermission() {
    }

    public EventPermission(Event event, Permission permission, UserGroup userGroup) {
        this.event = event;
        this.permission = permission;
        this.userGroup = userGroup;
    }

    public EventPermission(Event event, Permission permission) {
        this.event = event;
        this.permission = permission;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }
}
