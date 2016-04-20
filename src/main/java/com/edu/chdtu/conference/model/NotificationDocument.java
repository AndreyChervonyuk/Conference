package com.edu.chdtu.conference.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;

@BatchSize(size = 1000)
@Entity
@Table(name = "notification_document")
public class NotificationDocument {

    @Id
    @GeneratedValue()
    @Column(name = "id")
    private Integer id;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "document_id")
    private EventDocument document;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "notification_id")
    private Notification notification;


    public NotificationDocument() {
    }

    public NotificationDocument(EventDocument document, Notification notification) {
        this.document = document;
        this.notification = notification;
    }

    public NotificationDocument(String description, EventDocument document, Notification notification) {
        this.description = description;
        this.document = document;
        this.notification = notification;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EventDocument getDocument() {
        return document;
    }

    public void setDocument(EventDocument document) {
        this.document = document;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }
}
