package com.edu.chdtu.conference.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue()
    @Column(name = "id")
    private Integer id;

    @Column(name = "path")
    private String path;

    @Column(name = "name")
    private String name;

    @Column(name = "load_date")
    private Date loadDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;


    public Image() {
    }

    public Image(String name, String path, Date loadDate, User user) {
        this.name = name;
        this.path = path;
        this.loadDate = loadDate;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLoadDate() {
        return loadDate;
    }

    public void setLoadDate(Date loadDate) {
        this.loadDate = loadDate;
    }
}
