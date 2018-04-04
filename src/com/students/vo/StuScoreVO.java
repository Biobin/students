package com.students.vo;

public class StuScoreVO {
	
	private Integer scoreId;//成绩id
	private Double scores;//分数
	private Integer courseId;//科目id
	private String courseName;//科目名称
	private Integer studentId;//学生id
	
	public Integer getScoreId() {
		return scoreId;
	}
	public void setScoreId(Integer scoreId) {
		this.scoreId = scoreId;
	}
	
	public Double getScores() {
		return scores;
	}
	public void setScores(Double scores) {
		this.scores = scores;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	
	}
	public StuScoreVO() {
	}
	
	public StuScoreVO(Integer id, Double scores, Integer courseId, String courseName, Integer studentId) {
		super();
		this.scoreId = id;
		this.scores = scores;
		this.courseId = courseId;
		this.courseName = courseName;
		this.studentId = studentId;
	}
	
}
