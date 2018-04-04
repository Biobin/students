package com.students.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.students.entities.Menu;
import com.students.vo.MenuVO;

@Repository
public class MenuDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<MenuVO> getMenus(Integer myRole, Integer pid){
		String jpql = "SELECT new Menu(m.id,m.text,m.state,m.iconCls,m.url) FROM Menu m WHERE m.myRole = :myRole AND m.parent.id = :pid ";
		@SuppressWarnings("unchecked")
		List<Menu> menus = entityManager.createQuery(jpql).setParameter("pid", pid).setParameter("myRole", myRole).getResultList();
		List<MenuVO> menuVOs = null;
		if (menus.size() > 0) {
			menuVOs = new ArrayList<>();
			MenuVO menuVO = null;
			for (Menu menu : menus) {
				menuVO = new MenuVO();
				menuVO.setId(menu.getId());
				menuVO.setText(menu.getText());
				menuVO.setState(menu.getState());
				menuVO.setUrl(menu.getUrl());
				menuVO.setIconCls(menu.getIconCls());
				
				menuVOs.add(menuVO);
			}
		}
		return menuVOs;
	}
	
}
