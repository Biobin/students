package com.students.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.students.entities.Classs;

@Repository
public class ClasssDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Classs> getClasssList() {
		String jpql = "SELECT new Classs(c.classId, c.className) FROM Classs c";
		@SuppressWarnings("unchecked")
		List<Classs> classsList = entityManager.createQuery(jpql).getResultList();
		//System.out.println(classsList.get(0));
		return classsList;
	}

	public List<Classs> getClasssListByClassId(String classId) {
		String jpql = "SELECT new Classs(c.classId, c.className) FROM Classs c where c.classId = :classId";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("classId", Integer.parseInt(classId));
		@SuppressWarnings("unchecked")
		List<Classs> classsList = query.getResultList();
		return classsList;
	}
	
//	public List<Classs> getClasssList1() {
//		String jpql = "SELECT new Classs(c.classId, c.className, c.Teachers.teacherId) "
//				+ "FROM Classs c, Teachers t where ";
//		List<Classs> classsList = entityManager.createQuery(jpql).getResultList();
//		//System.out.println(classsList.get(0));
//		return classsList;
//	}
	
}
