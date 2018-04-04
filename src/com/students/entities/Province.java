package com.students.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name="PROVINCE")
@Entity
public class Province {
	
	private Integer provinceId;
	private String provinceName;
	
	private Set<City> city = new HashSet<City>();
	
	@GeneratedValue
	@Id
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	@OneToMany(mappedBy="province",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	public Set<City> getCity() {
		return city;
	}
	public void setCity(Set<City> city) {
		this.city = city;
	}
	
	public Province() {
	}
	
	public Province(Integer provinceId, String provinceName) {
		super();
		this.provinceId = provinceId;
		this.provinceName = provinceName;
	}
	
}
