package com.students.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.students.entities.Course;
import com.students.entities.Score;
import com.students.entities.Students;
import com.students.utils.StringUtils;
import com.students.vo.StuScoreVO;

@Repository
public class ScoreDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	//获取科目下拉列表
	public List<Course> getCourseList() {
		String jpql = "SELECT new Course(c.id, c.courseName) from Course c ";
		@SuppressWarnings("unchecked")
		List<Course> courseList = entityManager.createQuery(jpql).getResultList();
		return courseList;
	}
	
	//根据学生id查询学生成绩
	public List<StuScoreVO> getStuScoreView(Integer id){
		String jpql = "SELECT s.id, s.scores, s.course.id, s.course.courseName, s.students.id from Score s "
				+ "LEFT JOIN s.students LEFT JOIN s.course "
				+ "where s.students.id = :id ";
		Query query = entityManager.createQuery(jpql);
		@SuppressWarnings("unchecked")
		List<Object[]> objects = query.setParameter("id", id).getResultList();
		List<StuScoreVO> stuScoreView = new ArrayList<>();
		if(objects.size()>0) {
			StuScoreVO stuScoreVO = null;
			for(Object[] object : objects) {
				stuScoreVO = new StuScoreVO();
				stuScoreVO.setScoreId(Integer.parseInt(StringUtils.getString(object[0])));
				stuScoreVO.setScores(Double.parseDouble(StringUtils.getString(object[1])));
				stuScoreVO.setCourseId(Integer.parseInt(StringUtils.getString(object[2])));
				stuScoreVO.setCourseName(StringUtils.getString(object[3]));
				stuScoreVO.setStudentId(Integer.parseInt(StringUtils.getString(object[4])));
				
				stuScoreView.add(stuScoreVO);
			}
		}
		return stuScoreView;
	}
	
//	//根据scoreId返回stuScoreVO
//	public StuScoreVO getstuScoreVO(Integer scoreId) {
//		String jpql = "SELECT s.course.id, s.course.courseName, s.scores, s.students.id, s.id from Score s "
//				+ "LEFT JOIN s.course LEFT JOIN s.students "
//				+ "where s.id = :scoreId ";
//		Query query = entityManager.createQuery(jpql);
//		Object[] objects = (Object[]) query.setParameter("scoreId", scoreId).getSingleResult();
//		StuScoreVO stuScoreVO = new StuScoreVO();
//		stuScoreVO.setCourseId(Integer.parseInt(StringUtils.getString(objects[0])));
//		stuScoreVO.setCourseName(StringUtils.getString(objects[1]));
//		stuScoreVO.setScores(Double.parseDouble(StringUtils.getString(objects[2])));
//		stuScoreVO.setStudentId(Integer.parseInt(StringUtils.getString(objects[0])));
//		stuScoreVO.setScoreId(Integer.parseInt(StringUtils.getString(objects[4])));
//		return stuScoreVO;
//	}
	
	//新增学生成绩
	public void addStuScore(Map<String,String> map) {
		Score score = new Score();
		Course course = entityManager.find(Course.class, Integer.parseInt(map.get("courseId")));
		Students students = entityManager.find(Students.class, Integer.parseInt(map.get("studentId")));
		score.setScores(Double.parseDouble(map.get("scores")));
		score.setCourse(course);
		score.setStudents(students);
		
		entityManager.persist(score);
	}
	
	//修改学生成绩
	public void updateStuScore(Map<String,String> map,Integer scoreId) {
		Score score = entityManager.find(Score.class, scoreId);
		Course course = entityManager.find(Course.class, Integer.parseInt(map.get("courseId")));
		score.setScores(Double.parseDouble(map.get("scores")));
		score.setCourse(course);
		
		entityManager.merge(score);
	}
	
	//删除学生成绩
	public void deleteStuScore(Integer scoreId) {
		Score score = entityManager.find(Score.class, scoreId);
		if(scoreId !=null) {
			entityManager.remove(score);
		}
	}
	
}
