package com.students.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.students.entities.City;
import com.students.entities.Province;

@Repository
public class ProvinceDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Province> getProvinceList() {
		String jpql = "SELECT new Province(p.provinceId, p.provinceName) FROM Province p";
		@SuppressWarnings("unchecked")
		List<Province> provinceList = entityManager.createQuery(jpql).getResultList();
//		System.out.println(provinceList.get(0));
		return provinceList;
	}

	public List<City> getCityByProvinceId(Integer provinceId) {
		String jpql = "SELECT new City(c.cityId, c.cityName) FROM City c WHERE c.province.id = :provinceId";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("provinceId", provinceId);
		@SuppressWarnings("unchecked")
		List<City> city = query.getResultList();
		
//		System.out.println(city.get(0));
		return city;
	}
	
}
