package com.students.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name="tb_SCORE")
@Entity
public class Score {

	@JsonProperty(value="scoreId")
	private Integer id;
	private Double scores;
	private Course course;
	private Students students;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_SCORE")
	@SequenceGenerator(name = "seq_SCORE", sequenceName = "seq_SCORE", allocationSize = 1, initialValue = 100)
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Double getScores() {
		return scores;
	}
	
	public void setScores(Double scores) {
		this.scores = scores;
	}
	
	@JoinColumn(name="COURSEID", foreignKey=@ForeignKey(name="SCORE_COURSEID_FK"))
    @ManyToOne(fetch=FetchType.LAZY)
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	@JoinColumn(name="studentId", foreignKey=@ForeignKey(name="SCORE_ID_FK"))
    @ManyToOne(fetch=FetchType.LAZY)
	public Students getStudents() {
		return students;
	}
	public void setStudents(Students students) {
		this.students = students;
	}
	
	
}
