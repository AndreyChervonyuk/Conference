package com.edu.chdtu.conference.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.BatchSize;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@BatchSize(size = 1000)
@Entity
@Table(name = "user")
public class User {

	@Id
	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "surname", nullable = false)
	private String surname;

	@Column(name = "birth_day")
	@Temporal(TemporalType.DATE)
	private Date birthDay;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "enabled")
	private boolean enabled;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private Set<UserRole> userRole = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Document> documents = new HashSet<>();

	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Member> member = new HashSet<>();

	public User() {
	}

	public User(String email, String name, String surname, Date birthDay, String password) {
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.birthDay = birthDay;
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<UserRole> getUserRole() {
		return this.userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public Set<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}

	public Set<Member> getMember() {
		return member;
	}

	public void setMember(Set<Member> member) {
		this.member = member;
	}

}
