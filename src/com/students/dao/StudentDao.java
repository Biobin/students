package com.students.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.students.entities.City;
import com.students.entities.Classs;
import com.students.entities.Parents;
import com.students.entities.Province;
import com.students.entities.Students;
import com.students.entities.Teachers;
import com.students.utils.DateUtils;
import com.students.utils.StringUtils;
import com.students.vo.StudentVO;

@Repository
public class StudentDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	//新增学生信息
	public void addStudents(Map<String,String> map) {
		Students students = new Students();
		Classs classs = entityManager.find(Classs.class, Integer.parseInt(map.get("class_id")));
		Teachers teachers = entityManager.find(Teachers.class, Integer.parseInt(map.get("class_id")));//班级id与老师id相同
		Province province = entityManager.find(Province.class, Integer.parseInt(map.get("provinceId")));
		City city = entityManager.find(City.class, Integer.parseInt(map.get("cityId")));
		Parents parents = new Parents();
		parents.setFather(map.get("father"));
		parents.setfPhone(map.get("fPhone"));
		parents.setMother(map.get("mother"));
		parents.setmPhone(map.get("mPhone"));
		students.setClasss(classs);
		students.setBirth(DateUtils.getDate("yyyy-MM-dd", map.get("birth")));
		students.setId(Integer.parseInt(map.get("id")));
		students.setsName(map.get("s_name"));
		students.setsPhone(map.get("sPhone"));
		students.setsMail(map.get("sMail"));
		students.setAddress(map.get("address"));
		classs.setTeachers(teachers);
		students.setCity(city);
		city.setProvince(province);
		parents.setStudents(students);
		
		entityManager.persist(parents);
		entityManager.persist(students);
	}
	
	//删除学生信息
	public void removeStudents(Integer sid) {
		Students students = entityManager.find(Students.class, sid);
//		String sql = "select p.id from parents p where p.students_id = :sid ";
//		Query query = entityManager.createNativeQuery(sql);
//		int id = 0;
//		query.setParameter("sid", sid);
//		if(query.getResultList().size()!=0) {
//			id = ((Number)query.getSingleResult()).intValue();
//		}
//		if(id!=0) {
//			Parents parents = entityManager.find(Parents.class, id);
//			if(parents!=null) {
//				entityManager.remove(parents);
//			}
//		}
		if(students != null) {
			entityManager.remove(students);
		}
	}
	
	//修改学生信息
	public void updateStudents(Map<String,String> map,Integer sid) {
		Students students = entityManager.find(Students.class, sid);
		Classs classs = entityManager.find(Classs.class, Integer.parseInt(map.get("class_id")));
		Teachers teachers = entityManager.find(Teachers.class, Integer.parseInt(map.get("class_id")));//班级id与老师id相同
		Province province = entityManager.find(Province.class, Integer.parseInt(map.get("provinceId")));
		City city = entityManager.find(City.class, Integer.parseInt(map.get("cityId")));
		Parents parents  = null;
		String sql = "select p.id from parents p where p.students_id = :sid ";
		Query query = entityManager.createNativeQuery(sql);
		int id = 0;
		query.setParameter("sid", sid);
		if(query.getResultList().size()!=0) {
			id = ((Number)query.getSingleResult()).intValue();
		}
		if(id!=0) {
			parents = entityManager.find(Parents.class, id);
		}else {
			parents = new Parents();
		}
		parents.setFather(map.get("father"));
		parents.setfPhone(map.get("fPhone"));
		parents.setMother(map.get("mother"));
		parents.setmPhone(map.get("mPhone"));
		students.setClasss(classs);
		students.setBirth(DateUtils.getDate("yyyy-MM-dd", map.get("birth")));
		students.setId(sid);
		students.setsName(map.get("s_name"));
		students.setsPhone(map.get("sPhone"));
		students.setsMail(map.get("sMail"));
		students.setAddress(map.get("address"));
		classs.setTeachers(teachers);
		students.setCity(city);
		city.setProvince(province);
		parents.setStudents(students);
		
		entityManager.merge(parents);
		entityManager.merge(students);
	}
	
	//查询：根据id返回studentVO
	public StudentVO getStudentVO(Integer sid) {
//		String jpql = "select s.id, s.s_name, s.classs.classroom, s.classs.class_name, s.classs.teatchers.t_name,s.birth "
//				+ "FROM Students s LEFT JOIN s.classs LEFT JOIN s.classs.teachers "
//				+ "WHERE s.id = :sid ";		
		String sql = "select s.id,s.s_name,c.classroom,c.class_name,t.t_name,s.birth ,c.class_id, t.teacher_id "
				+ "from students s left outer join classs c on s.class_id=c.class_id "
				+ "left outer join teachers t on c.teacher_id=t.teacher_id "
				+ "WHERE s.id = :sid ";
//		Query query = entityManager.createQuery(jpql);
		Query query = entityManager.createNativeQuery(sql);
		
		Object[] object = (Object[]) query.setParameter("sid", sid).getSingleResult();
		StudentVO studentVO = new StudentVO();
		studentVO.setId(Integer.parseInt(StringUtils.getString(object[0])));
		studentVO.setsName(StringUtils.getString(object[1]));
		studentVO.setClassroom(StringUtils.getString(object[2]));
		studentVO.setClassName(StringUtils.getString(object[3]));
		studentVO.setTeachername(StringUtils.getString(object[4]));
		studentVO.setBirth(DateUtils.getDate("yyyy-MM-dd", StringUtils.getString(object[5])));
		studentVO.setClassId(Integer.parseInt(StringUtils.getString(object[6])));
		studentVO.setTeacherId(Integer.parseInt(StringUtils.getString(object[7])));
		return studentVO;
	}
	
	public List<StudentVO> getStudentVOList(Integer pageNo, Integer pageSize, String id, String sName, String classId, String provinceId, String cityId) {
		String jpql = "select s.id, s.sName, s.classs.classroom, s.classs.className, s.classs.teachers.tName, s.birth, "
				+ "s.classs.classId, s.classs.teachers.teacherId, s.city.province.provinceId, s.city.province.provinceName, "
				+ "s.city.cityId, s.city.cityName, s.sPhone, s.sMail, s.address, s.parents.father, s.parents.fPhone, s.parents.mother, s.parents.mPhone "
				+ "FROM Students s LEFT JOIN s.classs LEFT JOIN s.parents "
				+ "LEFT JOIN s.classs.teachers LEFT JOIN s.city LEFT JOIN s.city.province "
				+ "WHERE 1 = 1 ";
//				+ "ORDER BY s.id asc";
		Query query = null;
		if(id!=null&&!id.equals("")) {
			jpql += "and (s.id = :id or :id is null)";
			query = entityManager.createQuery(jpql);
			query.setParameter("id", Integer.parseInt(id));
		}else if(classId!=null&&!classId.equals("")) {
			jpql += "and (s.classs.classId = :classId or :classId is null)";
			if(sName!=null&&!sName.equals("")) {
				jpql += "and (s.sName like :sName or :sName is null)";
				if(provinceId!=null&&!provinceId.equals("")) {
					jpql += "and (s.city.province.provinceId = :provinceId or :provinceId is null)";
					if(cityId!=null&&!cityId.equals("")) {
						jpql += "and (s.city.cityId = :cityId or :cityId is null)";
						query = entityManager.createQuery(jpql);
						query.setParameter("cityId", Integer.parseInt(cityId));
					}else {	
						query = entityManager.createQuery(jpql);
					}
					query.setParameter("provinceId", Integer.parseInt(provinceId));
				}else {
					query = entityManager.createQuery(jpql);
				}
				query.setParameter("classId", Integer.parseInt(classId));
				query.setParameter("sName", "%"+sName+"%");
			}else if(provinceId!=null&&!provinceId.equals("")) {
				jpql += "and (s.city.province.provinceId = :provinceId or :provinceId is null)";
				if(cityId!=null&&!cityId.equals("")) {
					jpql += "and (s.city.cityId = :cityId or :cityId is null)";
					query = entityManager.createQuery(jpql);
					query.setParameter("cityId", Integer.parseInt(cityId));
				}else {	
					query = entityManager.createQuery(jpql);
				}
				query.setParameter("provinceId", Integer.parseInt(provinceId));
			}else {
				query = entityManager.createQuery(jpql);
			}
			query.setParameter("classId", Integer.parseInt(classId));
		}else if(sName!=null&&!sName.equals("")) {
			jpql += "and (s.sName like :sName or :sName is null)";
			if(provinceId!=null&&!provinceId.equals("")) {
				jpql += "and (s.city.province.provinceId = :provinceId or :provinceId is null)";
				if(cityId!=null&&!cityId.equals("")) {
					jpql += "and (s.city.cityId = :cityId or :cityId is null)";
					query = entityManager.createQuery(jpql);
					query.setParameter("cityId", Integer.parseInt(cityId));
				}else {	
					query = entityManager.createQuery(jpql);
				}
				query.setParameter("provinceId", Integer.parseInt(provinceId));
			}else {
				query = entityManager.createQuery(jpql);
			}
			query.setParameter("sName", "%"+sName+"%");
		}else if(provinceId!=null&&!provinceId.equals("")) {
			jpql += "and (s.city.province.provinceId = :provinceId or :provinceId is null)";
			if(cityId!=null&&!cityId.equals("")) {
				jpql += "and (s.city.cityId = :cityId or :cityId is null)";
				query = entityManager.createQuery(jpql);
				query.setParameter("cityId", Integer.parseInt(cityId));
			}else {	
				query = entityManager.createQuery(jpql);
			}
			query.setParameter("provinceId", Integer.parseInt(provinceId));
		}else {
			jpql += "ORDER BY s.id asc";
			query = entityManager.createQuery(jpql);
		}
		@SuppressWarnings("unchecked")
		List<Object[]> objects = query.setFirstResult((pageNo - 1)*pageSize).setMaxResults(pageSize).getResultList();
		List<StudentVO> studentVOList = new ArrayList<>();
		StudentVO studentVO = null;
		for(Object[] object : objects) {
			studentVO = new StudentVO();
			studentVO.setId(Integer.parseInt(StringUtils.getString(object[0])));
			studentVO.setsName(StringUtils.getString(object[1]));
			studentVO.setClassroom(StringUtils.getString(object[2]));
			studentVO.setClassName(StringUtils.getString(object[3]));
			studentVO.setTeachername(StringUtils.getString(object[4]));
			studentVO.setBirth(DateUtils.getDate("yyyy-MM-dd", StringUtils.getString(object[5])));
			studentVO.setClassId(Integer.parseInt(StringUtils.getString(object[6])));
			studentVO.setTeacherId(Integer.parseInt(StringUtils.getString(object[7])));
			if(object[8]!=null) {
				studentVO.setProvinceId(Integer.parseInt(StringUtils.getString(object[8])));
				studentVO.setProvinceName(StringUtils.getString(object[9]));
			}
			if(object[10]!=null) {
				studentVO.setCityId(Integer.parseInt(StringUtils.getString(object[10])));
				studentVO.setCityName(StringUtils.getString(object[11]));
			}
			if(object[12]!=null) {
				studentVO.setsPhone(StringUtils.getString(object[12]));
			}
			if(object[13]!=null) {
				studentVO.setsMail(StringUtils.getString(object[13]));
			}
			if(object[14]!=null) {
				studentVO.setAddress(StringUtils.getString(object[14]));
			}
			if(object[15]!=null) {
				studentVO.setFather(StringUtils.getString(object[15]));
			}
			if(object[16]!=null) {
				studentVO.setfPhone(StringUtils.getString(object[16]));
			}
			if(object[17]!=null) {
				studentVO.setMother(StringUtils.getString(object[17]));
			}
			if(object[18]!=null) {
				studentVO.setmPhone(StringUtils.getString(object[18]));
			}
			
			studentVOList.add(studentVO);
		}
		return studentVOList;
	}
	
	//返回总条数
	public Integer getTotalRowNum(String id, String sName, String classId, String cityId) {
		String jpql = "select count(*) from Students s "
				+ "LEFT JOIN s.classs LEFT JOIN s.city "
				+ "where 1=1 ";
		if(id!=null&&!id.equals("")) {
			jpql += "and s.id = " + id + " ";
		}
		if(sName!=null&&!sName.equals("")) {
			jpql += "and s.sName like :sName ";
		}
		if(classId!=null&&!classId.equals("")) {
			jpql += "and s.classs.classId = " + classId + " ";
		}
		if(cityId!=null&&!cityId.equals("")) {
			jpql +="and s.city.cityId = " + cityId + " ";
		}
		Query query = entityManager.createQuery(jpql);
		if(sName!=null&&!sName.equals("")) {
			query.setParameter("sName", "%"+sName+"%");
		}
		
		int totalRowNum = ((Number)query.getSingleResult()).intValue();
		return totalRowNum;
	}
	
	public Integer getTotalRowNum() {
		String sql = "select count(s.id) from students s ";
		Query query = entityManager.createNativeQuery(sql);
		int totalRowNum = ((Number)query.getSingleResult()).intValue();
		return totalRowNum;
	}

	public String getCLassIdByTName(String tName) {
		String sql = "select class_id from classs c where c.teacher_id = "
				+ "(select teacher_id from teachers t where t.t_name = :tName )";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("tName", tName);
		String classId = query.getSingleResult().toString();
		return classId;
	}

	public String getIdBySName(String sName) {
		String sql = "select id from students s where s.s_name = :sName";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("sName", sName);
		String id = query.getSingleResult().toString();
		return id;
	}

//	public Students getStudentById(Integer id) {
//		Students student = entityManager.find(Students.class, id);
//		return student;
//	}
	
	public StudentVO getStudentVOById(Integer id) {
		String jpql = "select s.id, s.sName, s.classs.classroom, s.classs.className, s.classs.teachers.tName, s.birth, "
				+ "s.classs.classId, s.classs.teachers.teacherId, s.city.province.provinceId, s.city.province.provinceName, "
				+ "s.city.cityId, s.city.cityName, s.sPhone, s.sMail, s.address, s.parents.father, s.parents.fPhone, s.parents.mother, s.parents.mPhone "
				+ "FROM Students s LEFT JOIN s.classs LEFT JOIN s.parents "
				+ "LEFT JOIN s.classs.teachers LEFT JOIN s.city LEFT JOIN s.city.province "
				+ "WHERE s.id = :id ";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<Object[]> objects = query.getResultList();
		List<StudentVO> studentVOList = new ArrayList<>();
		StudentVO studentVO = null;
		for(Object[] object : objects) {
			studentVO = new StudentVO();
			studentVO.setId(Integer.parseInt(StringUtils.getString(object[0])));
			studentVO.setsName(StringUtils.getString(object[1]));
			studentVO.setClassroom(StringUtils.getString(object[2]));
			studentVO.setClassName(StringUtils.getString(object[3]));
			studentVO.setTeachername(StringUtils.getString(object[4]));
			studentVO.setBirth(DateUtils.getDate("yyyy-MM-dd", StringUtils.getString(object[5])));
			studentVO.setClassId(Integer.parseInt(StringUtils.getString(object[6])));
			studentVO.setTeacherId(Integer.parseInt(StringUtils.getString(object[7])));
			if(object[8]!=null) {
				studentVO.setProvinceId(Integer.parseInt(StringUtils.getString(object[8])));
				studentVO.setProvinceName(StringUtils.getString(object[9]));
			}
			if(object[10]!=null) {
				studentVO.setCityId(Integer.parseInt(StringUtils.getString(object[10])));
				studentVO.setCityName(StringUtils.getString(object[11]));
			}
			if(object[12]!=null) {
				studentVO.setsPhone(StringUtils.getString(object[12]));
			}
			if(object[13]!=null) {
				studentVO.setsMail(StringUtils.getString(object[13]));
			}
			if(object[14]!=null) {
				studentVO.setAddress(StringUtils.getString(object[14]));
			}
			if(object[15]!=null) {
				studentVO.setFather(StringUtils.getString(object[15]));
			}
			if(object[16]!=null) {
				studentVO.setfPhone(StringUtils.getString(object[16]));
			}
			if(object[17]!=null) {
				studentVO.setMother(StringUtils.getString(object[17]));
			}
			if(object[18]!=null) {
				studentVO.setmPhone(StringUtils.getString(object[18]));
			}
			
			studentVOList.add(studentVO);
		}
		
		return studentVOList.get(0);
	}
}
