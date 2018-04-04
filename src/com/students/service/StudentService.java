package com.students.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.students.dao.StudentDao;
import com.students.vo.StudentVO;

@Service
public class StudentService {
	
	@Autowired
	private StudentDao studentDao;
	
	@Transactional
	public void addStudents(Map<String, String> map) {
		studentDao.addStudents(map);
	}
	
	@Transactional
	public void removeStudents(Integer sid) {
		studentDao.removeStudents(sid);
	}

	@Transactional
	public void updateStudents(Map<String, String> map, Integer sid) {
		studentDao.updateStudents(map, sid);
	}
	
	
	@Transactional(readOnly=true)
	public StudentVO getStudentVO(Integer sid) {
		StudentVO studentVO = studentDao.getStudentVO(sid);
		return studentVO;
	}
	
	@Transactional(readOnly=true)
	public List<StudentVO> getStudentVOList(Integer pageNo,Integer pageSize, String id, String sName, String classId, String provinceId, String cityId) {
		List<StudentVO> studentVOList = studentDao.getStudentVOList(pageNo, pageSize,id,sName,classId,provinceId,cityId);
		return studentVOList;
	}
	
	@Transactional(readOnly=true)
	public Integer getTotalRowNum(String id, String sName, String classId, String cityId) {
		return studentDao.getTotalRowNum(id,sName,classId,cityId);
	}

	@Transactional(readOnly=true)
	public Integer getTotalRowNum() {
		return studentDao.getTotalRowNum();
	}

	public String getClassIdByTName(String tName) {
		return studentDao.getCLassIdByTName(tName);
	}

	public String getIdBySName(String sName) {
		return studentDao.getIdBySName(sName);
	}
	
//	@Transactional
//	public Students getStudentById(Integer id) {
//		Students student = studentDao.getStudentById(id);
//		return student;
//	}
	
	public StudentVO getStudentVOById(Integer id) {
		StudentVO studentVO = studentDao.getStudentVOById(id);
		return studentVO;
	}
	
}
