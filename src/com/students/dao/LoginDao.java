package com.students.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.students.entities.MyUser;


@Repository
public class LoginDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public MyUser getMyUserByUsername(String username){
		try {
			String jpql = "From MyUser user where user.username = :username";
			MyUser myUser = (MyUser) entityManager.createQuery(jpql).setParameter("username", username).getSingleResult();
			return myUser;
		} catch (Exception e) {
			return null;
		}
	}
}
