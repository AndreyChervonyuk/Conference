package com.edu.chdtu.conference.model;


import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "email")
public class Email {

    @Id
    @GeneratedValue()
    @Column(name = "id")
    private Integer id;

    @Column(name = "subject")
    private String subject;

    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User author;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "email", cascade = CascadeType.ALL)
    private Set<SendTo> sendTo = new HashSet<>();

    public Email() {
    }

    public Email(String subject, String text, Date date) {
        this.subject = subject;
        this.text = text;
        this.date = date;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Set<SendTo> getSendTo() {
        return sendTo;
    }

    public void setSendTo(Set<SendTo> sendTo) {
        this.sendTo = sendTo;
    }
}
