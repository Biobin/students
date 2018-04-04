package com.students.entities;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Cacheable(true)//设置二级缓存
@Table(name="CITY")
@Entity
public class City {
	
	private Integer cityId;
	private String cityName;
	
	private Province province;
	
	@Id
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	@JoinColumn(name="PROVINCE_ID", foreignKey=@ForeignKey(name="CITY_PROVINCE_ID_FK"))
    @ManyToOne
	public Province getProvince() {
		return province;
	}
	public void setProvince(Province province) {
		this.province = province;
	}

	public City() {
	}
	
	public City(Integer cityId, String cityName, Province province) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
		this.province = province;
	}
	public City(Integer cityId, String cityName) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
	}
	
}
