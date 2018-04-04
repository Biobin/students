package com.students.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.students.dao.ScoreDao;
import com.students.entities.Course;
import com.students.vo.StuScoreVO;

@Service
public class ScoreService {

	@Autowired
	private ScoreDao scoreDao;
	
	@Transactional(readOnly=true)
	public List<Course> getCourseList() {
		List<Course> courseList = scoreDao.getCourseList();
		return courseList;
	}
	
	@Transactional(readOnly=true)
	public List<StuScoreVO> getStuScoreView(Integer id) {
		List<StuScoreVO> stuScoreView = scoreDao.getStuScoreView(id);
		return stuScoreView;
	}
	
//	@Transactional(readOnly=true)
//	public StuScoreVO getStuScoreVO(Integer scoreId) {
//		StuScoreVO stuScoreVO = scoreDao.getstuScoreVO(scoreId);
//		return stuScoreVO;
//	}
	
	@Transactional
	public void addStuScore(Map<String,String> map) {
		scoreDao.addStuScore(map);
	}
	
	@Transactional
	public void updateStuScore(Map<String, String> map,Integer scoreId) {
		scoreDao.updateStuScore(map, scoreId);
	}
	
	@Transactional
	public void deleteStuScore(Integer scoreId) {
		scoreDao.deleteStuScore(scoreId);
	}
	
}
