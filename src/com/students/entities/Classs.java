package com.students.entities;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Cacheable(true)//设置二级缓存
@Table(name="CLASSS")
@Entity
public class Classs {
	
	private Integer classId;
	private String className;
	private String classroom;
	
	private Teachers teachers;
	
	@GeneratedValue
	@Id
	@Column(name="CLASS_ID")
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	
	@Column(name="CLASS_NAME")
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	public String getClassroom() {
		return classroom;
	}
	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}
	
	@JoinColumn(name="TEACHER_ID", unique=true, foreignKey=@ForeignKey(name="TEACHER_CLASS_ID_FK"))
	@OneToOne
	public Teachers getTeachers() {
		return teachers;
	}
	public void setTeachers(Teachers teachers) {
		this.teachers = teachers;
	}
	public Classs() {
	}
	public Classs(Integer classId, String className) {
		super();
		this.classId = classId;
		this.className = className;
	}
	public Classs(Integer classId, String className, Teachers teachers) {
		super();
		this.classId = classId;
		this.className = className;
		this.teachers = teachers;
	}
	public Classs(Integer classId, String className, String classroom, Teachers teachers) {
		super();
		this.classId = classId;
		this.className = className;
		this.classroom = classroom;
		this.teachers = teachers;
	}
	public Classs(Integer classId) {
		super();
		this.classId = classId;
	}
	
	
}
