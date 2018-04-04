package com.students.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name="menu")
@Entity
public class Menu {

	@JsonProperty(value="menuId")
	private Integer id;
	private String text;
	private String state;
	private String iconCls;
	private String url;
	private Integer myRole;//教师为1，学生为0,超级管理员为2
	private Menu parent;//父节点
	@JsonIgnoreProperties(value={ "children", "parent" })
	private Set<Menu> children = new HashSet<>();//子节点
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_menu")
	@SequenceGenerator(name = "seq_menu", sequenceName = "seq_menu", allocationSize = 1, initialValue = 1)	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@JsonIgnore
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Integer getMyRole() {
		return myRole;
	}
	public void setMyRole(Integer myRole) {
		this.myRole = myRole;
	}
	
	@JsonIgnore
	@ManyToOne // (cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "PID", foreignKey = @ForeignKey(name = "fk_menu"))
	public Menu getParent() {
		return parent;
	}
	public void setParent(Menu parent) {
		this.parent = parent;
	}
	
	@JsonIgnore
	@OneToMany(mappedBy = "parent")
	public Set<Menu> getChildren() {
		return children;
	}
	public void setChildren(Set<Menu> children) {
		this.children = children;
	}

	public Menu() {
	}
	
	public Menu(Integer id){
		this.id=id;
	}
	
	public Menu(Integer id, String text, String state, String iconCls, String url) {
		super();
		this.id = id;
		this.text = text;
		this.state = state;
		this.iconCls = iconCls;
		this.url = url;
	}
	
	public Menu(Integer id, String text, String state, String iconCls, String url, Integer myRole, Menu parent,
			Set<Menu> children) {
		super();
		this.id = id;
		this.text = text;
		this.state = state;
		this.iconCls = iconCls;
		this.url = url;
		this.myRole = myRole;
		this.parent = parent;
		this.children = children;
	}
	
}
