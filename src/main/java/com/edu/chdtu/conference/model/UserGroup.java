package com.edu.chdtu.conference.model;


import org.springframework.security.access.hierarchicalroles.RoleHierarchy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "user_group")
public class UserGroup {

    @Id
    @GeneratedValue()
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userGroup")
    private Set<EventPermission> eventPermissions = new HashSet<>();

    public UserGroup() {
    }

    public UserGroup(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<EventPermission> getEventPermissions() {
        return eventPermissions;
    }
}
