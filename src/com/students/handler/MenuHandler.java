package com.students.handler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.students.entities.MyUser;
import com.students.service.MenuService;
import com.students.vo.MenuVO;

@Controller
public class MenuHandler {
	
	@Autowired
	private MenuService menuService;
	
	@ResponseBody
	@RequestMapping("/getMenus")
	public List<MenuVO> getMenusByPid(HttpServletRequest request, HttpSession httpSession) {
		String pid = request.getParameter("menuId");
		MyUser myUser = (MyUser) httpSession.getAttribute("my_user");
		Integer roleId = myUser.getMyRole();
		List<MenuVO> menuVOs = new ArrayList<>();
		if (pid != null && !pid.equals("")) {
			menuVOs = menuService.getMenus(roleId, Integer.parseInt(pid));
		}else{
			//在数据库中设置根节点的id为-1了。
			menuVOs = menuService.getMenus(roleId,-1);
		}
		return menuVOs;
	}
	
}
