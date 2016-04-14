package com.edu.chdtu.conference.model;

import com.edu.chdtu.conference.model.enums.Status;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue()
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "information")
    private String information;

    @Column(name = "create_date")
    private Date creteDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", referencedColumnName = "email")
    private User creator;

    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "finish_date")
    @Temporal(TemporalType.DATE)
    private Date finishDate;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    @Enumerated(value = EnumType.ORDINAL)
    private Status status;

    @Column(name = "poster_path")
    private String posterPath;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "event", cascade = CascadeType.ALL)
    private Set<Member> members = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
    private Set<EventDocument> documents = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "event", cascade = CascadeType.ALL)
    private Set<EventPermission> permissions = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "event", cascade = CascadeType.ALL)
    private Set<Notification> notifications = new HashSet<>();

    public Event(String name, String description, Date creteDate, User creator, Date startDate, Date finishDate) {
        this.name = name;
        this.description = description;
        this.creteDate = creteDate;
        this.creator = creator;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public Event(String name, String description, String information, Date startDate, Date finishDate, String address) {
        this.name = name;
        this.description = description;
        this.information = information;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.address = address;
    }

    public Event() {
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Date getCreteDate() {
        return creteDate;
    }

    public void setCreteDate(Date creteDate) {
        this.creteDate = creteDate;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<EventPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<EventPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<EventDocument> getDocuments() {
        return documents;
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }

    public void setDocuments(Set<EventDocument> documents) {
        this.documents = documents;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

}
