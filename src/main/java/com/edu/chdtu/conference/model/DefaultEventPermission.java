package com.edu.chdtu.conference.model;

import javax.persistence.*;

@Entity
@Table(name = "default_event_permission")
public class DefaultEventPermission {

    @Id
    @GeneratedValue()
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "permission_id", nullable = false)
    private Permission permission;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_group_id", nullable = false)
    private UserGroup userGroup;

    public DefaultEventPermission() {
    }

    public DefaultEventPermission(Permission permission, UserGroup userGroup) {
        this.permission = permission;
        this.userGroup = userGroup;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
