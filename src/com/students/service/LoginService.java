package com.students.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.students.dao.LoginDao;
import com.students.entities.MyUser;

@Service
public class LoginService {
	
	@Autowired
	private LoginDao loginDao;

	@Transactional(readOnly=true)
	public MyUser getMyUserByUsername(String username){
		return loginDao.getMyUserByUsername(username);
	}
}
