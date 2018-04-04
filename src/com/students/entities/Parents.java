package com.students.entities;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name="PARENTS")
@Entity
public class Parents {
	
	@JsonProperty(value="parentsId")
	private Integer id;
	private String father;
	private String fPhone;
	private String mother;
	private String mPhone;
	
	private Students students;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_PARENTS")
	@SequenceGenerator(name = "seq_PARENTS", sequenceName = "seq_PARENTS", allocationSize = 1, initialValue = 1)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getFather() {
		return father;
	}
	public void setFather(String father) {
		this.father = father;
	}
	
	public String getfPhone() {
		return fPhone;
	}
	public void setfPhone(String fPhone) {
		this.fPhone = fPhone;
	}
	
	public String getMother() {
		return mother;
	}
	public void setMother(String mother) {
		this.mother = mother;
	}
	
	public String getmPhone() {
		return mPhone;
	}
	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}
	
	@JoinColumn(name="STUDENTS_ID", unique=true, foreignKey=@ForeignKey(name="STUDENTS_PARENTS_ID_FK"))
	@OneToOne
	public Students getStudents() {
		return students;
	}
	public void setStudents(Students students) {
		this.students = students;
	}
	
}
