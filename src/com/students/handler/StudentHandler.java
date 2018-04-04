package com.students.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.students.entities.City;
import com.students.entities.Classs;
import com.students.entities.Course;
import com.students.entities.MyUser;
import com.students.entities.Province;
import com.students.service.ClasssService;
import com.students.service.ProvinceService;
import com.students.service.ScoreService;
import com.students.service.StudentService;
import com.students.vo.StuScoreVO;
import com.students.vo.StudentVO;


@Controller
@RequestMapping("/stu")
public class StudentHandler {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private ClasssService classsService;
	
	@Autowired
	private ProvinceService provinceService;
	
	@Autowired
	private ScoreService scoreService;
	
	//用于页面跳转
	@RequestMapping("/getTABStu")
	public String getTABStu(){
		return "getTABStu";
	}
	
	//用于页面跳转
	@RequestMapping("/getTABStuInfo")
	public String getTABStuInfo(){
		return "getTABStuInfo";
	}
	
	//获取班级下拉列表
	@ResponseBody
	@RequestMapping("/classsList")
	public List<Classs> getClasssList(Model model) throws JsonProcessingException {
		List<Classs> classsList = classsService.getClasssLsit();
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		String json = "";
		json = mapper.writeValueAsString(classsList);
		model.addAttribute("classsList", json);
//		System.out.println(json);
//		System.out.println(classsList.size());
		return classsList;
	}
	
	//获取省份下拉列表
	@ResponseBody
	@RequestMapping("/provinceList")
	public List<Province> getProvinceList(Model model) throws JsonProcessingException {
		List<Province> provinceList = provinceService.getProvinceList();
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		String json = "";
		json = mapper.writeValueAsString(provinceList);
		model.addAttribute("provinceList", json);
		return provinceList;
	}
	
	//根据省份查询城市
	@ResponseBody
	@RequestMapping("/getCityListByProvinceId")
	public List<City> getCityByProvinceId(@RequestParam(name="provinceId")Integer provinceId) {
		List<City> city = new ArrayList<>();
		if(provinceId !=null ) {
			city = provinceService.gerCityByProvinceId(provinceId);
		}
		return city;
	}
	
	//根据学生id返回成绩
	@ResponseBody
	@RequestMapping("/getStuScoreView")
	public List<StuScoreVO> getStuScoreView(@RequestParam(value="id",required=false)Integer id, HttpSession httpSession) {
		List<StuScoreVO> stuScoreView = new ArrayList<>();
		if(id != null ) {
			stuScoreView = scoreService.getStuScoreView(id);
		}
		MyUser myUser = (MyUser) httpSession.getAttribute("my_user");
		Integer roleId = myUser.getMyRole();
		String sName= null;
		if(roleId==0) {
			//学生角色
			sName = myUser.getPname();
			id = Integer.parseInt(studentService.getIdBySName(sName));
			stuScoreView = scoreService.getStuScoreView(id);
		}	
		return stuScoreView;
	}
	
	//获取科目下拉列表
	@ResponseBody
	@RequestMapping("/courseList")
	public List<Course> getCourseList(Model model) throws JsonProcessingException {
		List<Course> courseList = scoreService.getCourseList();
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		String json = "";
		json = mapper.writeValueAsString(courseList);
		model.addAttribute("courseList", json);
//		System.out.println(json);
//		System.out.println(courseList.size());
		return courseList;
	}
	
	//根据教师名字获取班级列表
	@ResponseBody
	@RequestMapping("/getClassId")
	public List<Classs> getClassIdByTName(HttpSession httpSession, Model model) throws JsonProcessingException {
		MyUser myUser = (MyUser) httpSession.getAttribute("my_user");
		Integer roleId = myUser.getMyRole();
		String classId = null;
		if(roleId==1) {
			//教师角色
			String tName = myUser.getPname();
			classId = studentService.getClassIdByTName(tName);
		}	
		List<Classs> classsList = classsService.getClasssListByClassId(classId);
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		String json = "";
		json = mapper.writeValueAsString(classsList);
		model.addAttribute("classsList", json);
		return classsList;
	}
	
	//新增学生成绩
	@ResponseBody
	@RequestMapping(value="/addStuScore/{id}", method=RequestMethod.POST)
	public String addStuScore(@PathVariable("id") String id, HttpServletRequest request) {
//		String id = request.getParameter("id");
		String courseId = request.getParameter("courseId");
		String scores = request.getParameter("scores");
		Map<String, String> map = new HashMap<String, String>();
		map.put("studentId", id);
		map.put("courseId", courseId);
		map.put("scores", scores);
		scoreService.addStuScore(map);
		return "1";
	}
	
	//修改学生成绩
	@ResponseBody
	@RequestMapping(value="/updateStuScore/{scoreId}", method=RequestMethod.POST)
	public String updateStuScore(@PathVariable(value="scoreId")String scoreId, HttpServletRequest request) {
		String courseId = request.getParameter("courseId");
		String scores = request.getParameter("scores");
		Map<String, String> map = new HashMap<String, String>();
		map.put("courseId", courseId);
		map.put("scores", scores);
		scoreService.updateStuScore(map, Integer.parseInt(scoreId));
		
		return "1";
	}
	
//	//修改成绩时回显
//	@ResponseBody
//	@RequestMapping(value ="/showScore/{scoreId}",method=RequestMethod.GET)
//	public StuScoreVO getStuScoreVO(@PathVariable("scoreId")Integer scoreId,Map<String, Object> map) {
//		StuScoreVO stuScoreVO = null;
//		stuScoreVO = scoreService.getStuScoreVO(scoreId);
//		return stuScoreVO;
//	}
	
	//删除学生成绩
	@ResponseBody
	@RequestMapping(value="/deleteStuScore", method=RequestMethod.DELETE)
	public String deleteStuScore(@RequestParam(value="scoreId")String scoreId) {
		scoreService.deleteStuScore(Integer.parseInt(scoreId));
		return "delete";
		
	}
	
	//获取DataGrid学生信息 （点击查询）
	@ResponseBody
	@RequestMapping("/getStudentVOList")
	public Map<String, Object> getStudentVOList(HttpServletRequest request, HttpSession httpSession) {
		MyUser myUser = (MyUser) httpSession.getAttribute("my_user");
		Integer roleId = myUser.getMyRole();
		String sName= null;
		String classId = null;
		String id = null;
		if(roleId==0) {
			//学生角色
			sName = myUser.getPname();
			id = studentService.getIdBySName(sName);
			classId = request.getParameter("classId");
		}else {
			if(roleId==1) {
				//教师角色
				String tName = myUser.getPname();
				classId = studentService.getClassIdByTName(tName);
			}else if(roleId==2){
				//超级管理员
				classId = request.getParameter("classId");
			}
			sName = request.getParameter("sName");
			id = request.getParameter("id");
		}
		int pageNo = Integer.parseInt(request.getParameter("page"));
		int pageSize=Integer.parseInt(request.getParameter("rows"));
		String provinceId = request.getParameter("provinceId");
		String cityId = request.getParameter("cityId");
		List<StudentVO> studentVOList= studentService.getStudentVOList(pageNo, pageSize,id,sName,classId,provinceId,cityId);
		if(studentVOList==null) {
			studentVOList = new ArrayList<>();
		}
		int totalRowNum = studentService.getTotalRowNum(id,sName,classId,cityId);
		Map<String, Object> map=new HashMap<>();
		map.put("total", totalRowNum);
		map.put("rows", studentVOList);
//		System.out.println("标记"+classId);
		return map;
	}
	
	//新增学生信息
	@ResponseBody
	@RequestMapping(value="/addStu", method=RequestMethod.POST)
	public String add(HttpServletRequest request) {
		String id = request.getParameter("id");
		String sName = request.getParameter("s_name");
		String classId = request.getParameter("class_id");
		//String teacherId = request.getParameter("teacher_id");
		String birth = request.getParameter("birth");
		String provinceId = request.getParameter("provinceId");
		String cityId = request.getParameter("cityId");
		String sPhone = request.getParameter("sPhone");
		String sMail = request.getParameter("sMail");
		String address = request.getParameter("address");
		String father = request.getParameter("father");
		String fPhone = request.getParameter("fPhone");
		String mother = request.getParameter("mother");
		String mPhone = request.getParameter("mPhone");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("s_name", sName);
		map.put("class_id", classId);
		//map.put("teacher_id", teacherId);
		map.put("birth", birth);
		map.put("provinceId", provinceId);
		map.put("cityId", cityId);
		map.put("sPhone", sPhone);
		map.put("sMail", sMail);
		map.put("address", address);
		map.put("father", father);
		map.put("fPhone", fPhone);
		map.put("mother", mother);
		map.put("mPhone", mPhone);
		
		studentService.addStudents(map);
		return "1";
	}
	
	//修改学生信息
	@ResponseBody
	@RequestMapping(value="/updateStu/{id}", method=RequestMethod.POST)
	public String update(HttpServletRequest request){
		String id = request.getParameter("id");
		String sName = request.getParameter("s_name");
		String classId = request.getParameter("class_id");
		String birth = request.getParameter("birth");
		String provinceId = request.getParameter("provinceId");
		String cityId = request.getParameter("cityId");
		String sPhone = request.getParameter("sPhone");
		String sMail = request.getParameter("sMail");
		String address = request.getParameter("address");
		String father = request.getParameter("father");
		String fPhone = request.getParameter("fPhone");
		String mother = request.getParameter("mother");
		String mPhone = request.getParameter("mPhone");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("s_name", sName);
		map.put("class_id", classId);
		map.put("birth", birth);
		map.put("provinceId", provinceId);
		map.put("cityId", cityId);
		map.put("sPhone", sPhone);
		map.put("sMail", sMail);
		map.put("address", address);
		map.put("father", father);
		map.put("fPhone", fPhone);
		map.put("mother", mother);
		map.put("mPhone", mPhone);
		
		studentService.updateStudents(map, Integer.parseInt(id));
		return "1";
	}
	
//	//表单回显
//	@ResponseBody
//	@RequestMapping(value="/show/{id}", method=RequestMethod.GET)
//	public StudentVO getStudentVO(@PathVariable("id")Integer id,Map<String, Object> map) {
//		StudentVO studentVO = null;
//		studentVO = studentService.getStudentVO(id);
//		Students student = studentService.getStudentById(id);
//		if(studentVO.getClassId() != null) {
//			Classs classs = new Classs(student.getClasss().getClassId());
//			studentVO = new StudentVO(student.getId(), student.getsName(),classs.getClassroom(), classs.getClassName(), student.getBirth(),classs.getClassId(), classs.getTeachers().getTeacherId(), );
//		}else {
//			studentVO = new StudentVO(student.getId(), student.getsName(), student.getBirth(),null);
//		}
//		return studentVO;
//	}
	
	@ResponseBody
	@RequestMapping(value="/show", method=RequestMethod.GET)
	public StudentVO getStudentVOById(HttpSession httpSession) {
		MyUser myUser = (MyUser) httpSession.getAttribute("my_user");
		String sName= myUser.getPname();
		Integer id = Integer.parseInt(studentService.getIdBySName(sName));
		StudentVO studentVO = null;
		studentVO = studentService.getStudentVOById(id);
		
		return  studentVO;
	}
	
	//删除学生
	@ResponseBody
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable(value = "id") Integer id) {
		studentService.removeStudents(id);
		return "delete";
	}
	
}
