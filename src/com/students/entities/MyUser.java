package com.students.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name="my_user",uniqueConstraints={@UniqueConstraint(columnNames="username")})
public class MyUser {
	
	private Integer id;
	private String username;
	private String password;
	private String pname;
	private Integer myRole;//教师为1，学生为0,超级管理员为2
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_myuser")
	@SequenceGenerator(name = "seq_myuser", sequenceName = "seq_myuser", allocationSize = 1, initialValue = 1)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}

	public Integer getMyRole() {
		return myRole;
	}
	public void setMyRole(Integer myRole) {
		this.myRole = myRole;
	}
	
}