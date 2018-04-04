package com.students.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.students.dao.ProvinceDao;
import com.students.entities.City;
import com.students.entities.Province;

@Service
public class ProvinceService {
	
	@Autowired
	private ProvinceDao provinceDao;
	
	@Transactional(readOnly=true)
	public List<Province> getProvinceList() {
		List<Province> provinceList = provinceDao.getProvinceList();
		return provinceList;
	}

	@Transactional(readOnly=true)
	public List<City> gerCityByProvinceId(Integer provinceId) {
		List<City> city = provinceDao.getCityByProvinceId(provinceId);
		return city;
	}
	
}
