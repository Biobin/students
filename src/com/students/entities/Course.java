package com.students.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name="tb_COURSE")
@Entity
public class Course {
	
	@JsonProperty(value="courseId")
	private Integer id;
	private String courseName;
	
	public Course() {
	}
	
	public Course(Integer id, String courseName) {
		super();
		this.id = id;
		this.courseName = courseName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_COURSE")
	@SequenceGenerator(name = "seq_COURSE", sequenceName = "seq_COURSE", allocationSize = 1, initialValue = 100)
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
}
