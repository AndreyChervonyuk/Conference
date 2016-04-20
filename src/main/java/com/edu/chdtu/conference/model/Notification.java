package com.edu.chdtu.conference.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue()
    @Column(name = "id")
    private Integer id;

    @Column(name = "header", nullable = false)
    private String header;

    @Column(name = "text", nullable = false)
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User author;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "notification", cascade = CascadeType.ALL)
    private Set<NotificationDocument> documents = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "notification", cascade = CascadeType.ALL)
    private Set<NotificationComment> comments = new HashSet<>();

    public Notification() {
    }

    public Notification(String headers, String text, User author, Date date, Event event) {
        this.header = headers;
        this.text = text;
        this.author = author;
        this.date = date;
        this.event = event;
    }

    public int getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Set<NotificationDocument> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<NotificationDocument> documents) {
        this.documents = documents;
    }

    public Set<NotificationComment> getComments() {
        return comments;
    }

    public void setComments(Set<NotificationComment> comments) {
        this.comments = comments;
    }
}
