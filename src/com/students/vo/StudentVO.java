package com.students.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentVO {

	private Integer id;//学号
	@JsonProperty(value="s_name")
	private String sName;//学生姓名
	private String classroom;//门牌号
	@JsonProperty(value="class_name")
	private String className; // 班级名称
	@JsonProperty(value="t_name")
	private String teachername;//教师姓名
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birth;//出生日期
	@JsonProperty(value="class_id")
	private Integer classId;//班级id
	@JsonProperty(value="teahcer_id")
	private Integer teacherId;//教师id
	private String provinceName;//省份
	private Integer provinceId;//省份id
	private String cityName;//城市
	private Integer cityId;//城市id
	
	private String sPhone;//联系电话
	private String sMail;//邮箱
	private String address;//通讯地址
	private String father;//父亲
	private String fPhone;//父亲电话
	private String mother;//母亲
	private String mPhone;//母亲电话
	
	public StudentVO() {
	}

	public StudentVO(Integer id, String sName, String classroom, String className, String teachername, Date birth,
			Integer classId, Integer teacherId, String provinceName, Integer provinceId, String cityName,
			Integer cityId, String sPhone, String sMail, String address, String father, String fPhone, String mother,
			String mPhone) {
		super();
		this.id = id;
		this.sName = sName;
		this.classroom = classroom;
		this.className = className;
		this.teachername = teachername;
		this.birth = birth;
		this.classId = classId;
		this.teacherId = teacherId;
		this.provinceName = provinceName;
		this.provinceId = provinceId;
		this.cityName = cityName;
		this.cityId = cityId;
		this.sPhone = sPhone;
		this.sMail = sMail;
		this.address = address;
		this.father = father;
		this.fPhone = fPhone;
		this.mother = mother;
		this.mPhone = mPhone;
	}

	public StudentVO(Integer id, String sName, String classroom, String className, String teachername, Date birth,
			Integer classId, Integer teacherId) {
		super();
		this.id = id;
		this.sName = sName;
		this.classroom = classroom;
		this.className = className;
		this.teachername = teachername;
		this.birth = birth;
		this.classId = classId;
		this.teacherId = teacherId;
	}

	public StudentVO(Integer id, String sName, Date birth, Integer classId, Integer teacherId) {
		super();
		this.id = id;
		this.sName = sName;
		this.birth = birth;
		this.classId = classId;
		this.teacherId = teacherId;
	}

	public StudentVO(Integer id, String sName, String classroom, String className, String teachername, Date birth,
			Integer classId, Integer teacherId, String provinceName, Integer provinceId, String cityName,
			Integer cityId) {
		super();
		this.id = id;
		this.sName = sName;
		this.classroom = classroom;
		this.className = className;
		this.teachername = teachername;
		this.birth = birth;
		this.classId = classId;
		this.teacherId = teacherId;
		this.provinceName = provinceName;
		this.provinceId = provinceId;
		this.cityName = cityName;
		this.cityId = cityId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTeachername() {
		return teachername;
	}

	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}

	public String getClassroom() {
		return classroom;
	}

	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}

	@JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getsPhone() {
		return sPhone;
	}

	public void setsPhone(String sPhone) {
		this.sPhone = sPhone;
	}

	public String getsMail() {
		return sMail;
	}

	public void setsMail(String sMail) {
		this.sMail = sMail;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFather() {
		return father;
	}

	public void setFather(String father) {
		this.father = father;
	}

	public String getfPhone() {
		return fPhone;
	}

	public void setfPhone(String fPhone) {
		this.fPhone = fPhone;
	}

	public String getMother() {
		return mother;
	}

	public void setMother(String mother) {
		this.mother = mother;
	}

	public String getmPhone() {
		return mPhone;
	}

	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}

}
