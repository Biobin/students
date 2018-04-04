package com.students.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.students.dao.MenuDao;
import com.students.vo.MenuVO;

@Service
public class MenuService {
	
	@Autowired
	private MenuDao menuDao;
	
	public List<MenuVO> getMenus(Integer myRole, Integer pid) {
		return menuDao.getMenus(myRole, pid);
	}
	
}
