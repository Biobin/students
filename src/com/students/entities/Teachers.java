package com.students.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name="TEACHERS")
@Entity
public class Teachers {
	
	private Integer teacherId;
	private String tName;
	private double salary;
	
	private Classs classs;
	
	@GeneratedValue
	@Id
	@Column(name="TEACHER_ID")
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	
	@Column(name="T_NAME")
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
	}
	
	@Column(name="SALARY")
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	
	@OneToOne(mappedBy="teachers")
	public Classs getClasss() {
		return classs;
	}
	public void setClasss(Classs classs) {
		this.classs = classs;
	}
	
	
}
