package com.students.test;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.students.entities.Classs;
import com.students.entities.Province;
import com.students.service.ClasssService;
import com.students.service.ProvinceService;
import com.students.service.ScoreService;
import com.students.service.StudentService;
import com.students.vo.StuScoreVO;
import com.students.vo.StudentVO;

public class Test {
	private ApplicationContext ctx = null;
	private StudentService studentService;
	private ClasssService classsService;
	private ProvinceService provinceService;
	private ScoreService scoreService;
	
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		studentService = ctx.getBean(StudentService.class);
		classsService = ctx.getBean(ClasssService.class);
		scoreService = ctx.getBean(ScoreService.class);
	}
	
	@org.junit.jupiter.api.Test
	public void test() throws SQLException {
		DataSource dataSource = ctx.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
	}
	
	@org.junit.jupiter.api.Test
	public void testAddStudents() {
		Map<String ,String> map = new HashMap<String ,String>();
		map.put("id", "16");
		map.put("s_name", "周浩宇");
		map.put("class_id", "2");
		map.put("teacher_id", "2");
		map.put("birth", "1994-08-12");
		studentService.addStudents(map);
	}
	
	@org.junit.jupiter.api.Test
	public void testRemove() {
		studentService.removeStudents(16);
	}
	
	@org.junit.jupiter.api.Test
	public void testupdate() {
		Integer sid = 29;
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", sid.toString());
		map.put("s_name", "刘德华");
		map.put("class_id", "2");
		map.put("teacher_id", "2");
		map.put("birth", "1995-12-02");
		studentService.updateStudents(map, sid);
	}
	
	@org.junit.jupiter.api.Test
	public void  testgetStudentVO() {
		Integer sid = 1;
		StudentVO studentVO = new StudentVO();
		studentVO = studentService.getStudentVO(sid);
		System.out.println(studentVO.toString());
	}
	
//	@org.junit.jupiter.api.Test
//	public void testgetStudentVOList() {
//		List<StudentVO> studentVOList = studentService.getStudentVOList();
//		System.out.println(studentVOList);
//	}
	
	@org.junit.jupiter.api.Test
	public void testClasssList() {
		List<Classs> classsList = classsService.getClasssLsit();
		System.out.println(classsList);
	}
	
	@org.junit.jupiter.api.Test
	public void testGetStudentVOList() {
//		List<StudentVO> studentVOList = studentService.getStudentVOList(1, 1, "1", null, null);
//		System.out.println(studentVOList);
	}
	
	@org.junit.jupiter.api.Test
	public void testGetProvinceLit() {
		List<Province> provinceList = provinceService.getProvinceList();
		System.out.println(provinceList);
	}
	
	@org.junit.Test
	public void testgetStyScoreView() {
		List<StuScoreVO> stuScoreView = scoreService.getStuScoreView(1);
		System.out.println(stuScoreView);
	}
	
	@org.junit.Test
	public void testDataSource() throws SQLException {
		DataSource dataSource = ctx.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
	}
}
