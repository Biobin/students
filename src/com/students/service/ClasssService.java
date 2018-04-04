package com.students.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.students.dao.ClasssDao;
import com.students.entities.Classs;

@Service
public class ClasssService {
	
	@Autowired
	private ClasssDao classsDao;
	
	@Transactional(readOnly=true)
	public List<Classs> getClasssLsit() {
		List<Classs> classsList = classsDao.getClasssList();
		return classsList;
	}

	public List<Classs> getClasssListByClassId(String classId) {
		List<Classs> classsList = classsDao.getClasssListByClassId(classId);
		return classsList;
	}
	
}
