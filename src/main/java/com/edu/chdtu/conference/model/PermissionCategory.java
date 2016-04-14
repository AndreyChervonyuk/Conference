package com.edu.chdtu.conference.model;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;

@BatchSize(size = 1000)
@Entity
@Table(name = "permission_category")
public class PermissionCategory {

    @Id
    @GeneratedValue()
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    public PermissionCategory() {
    }

    public PermissionCategory(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
