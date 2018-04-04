package com.students.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name="STUDENTS")
@Entity
public class Students {
	
	private Integer id;
	@JsonProperty(value="s_name")
	private String sName;
	
	private Classs classs;
	
	private City city;
	
	private String sPhone;
	private String sMail;
	private String address;
	private Parents parents;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birth;
	
	private Set<Score> scores = new HashSet<Score>();
	
	public Students() {
		
	}
	
	public Students(Integer id, String sName) {
		super();
		this.id = id;
		this.sName = sName;
	}


	public Students(Integer id, String sName, Classs classs, Date birth) {
		super();
		this.id = id;
		this.sName = sName;
		this.classs = classs;
		this.birth = birth;
	}

	@Id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="s_name")
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	@JoinColumn(name="CLASS_ID", foreignKey=@ForeignKey(name="STUDENT_CLASS_ID_FK"))
    @ManyToOne(fetch=FetchType.LAZY)
	public Classs getClasss() {
		return classs;
	}
	public void setClasss(Classs classs) {
		this.classs = classs;
	}

	@JoinColumn(name="CITYID", foreignKey=@ForeignKey(name="STUDENT_CITYID_FK"))
	@ManyToOne
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getsPhone() {
		return sPhone;
	}

	public void setsPhone(String sPhone) {
		this.sPhone = sPhone;
	}

	public String getsMail() {
		return sMail;
	}

	public void setsMail(String sMail) {
		this.sMail = sMail;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@OneToOne(cascade={CascadeType.REMOVE},mappedBy="students")
	public Parents getParents() {
		return parents;
	}

	public void setParents(Parents parents) {
		this.parents = parents;
	}

	@OneToMany(cascade={CascadeType.REMOVE},mappedBy="students")
	public Set<Score> getScores() {
		return scores;
	}

	public void setScores(Set<Score> scores) {
		this.scores = scores;
	}
	
}
