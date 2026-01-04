package com.pas162.learning.model;

public class TaskDTO {
	private Long id;
	private String name;
	private String status;

	// Constructors
	public TaskDTO() {
	}

	public TaskDTO(Long id, String name, String status) {
		this.id = id;
		this.name = name;
		this.status = status;
	}

	// Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}