package com.project.todorestfulwebservices.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Todo {
	
	@Id
	@GeneratedValue
	private Integer id; 
	
	
	private String username;
	private String description;
	private LocalDate targetDate; 
	private boolean isDone;
	
	public Todo () {}

	public Todo(Integer id, String username, String description, LocalDate targetDate, boolean isDone) {
		super();
		this.id = id;
		this.username = username;
		this.description = description;
		this.targetDate = targetDate;
		this.isDone = isDone;
	}

	public Integer getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getDescription() {
		return description;
	}

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public boolean isDone() {
		return isDone;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}
	
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
}