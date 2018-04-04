package com.students.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.students.entities.MyUser;
import com.students.service.LoginService;

@SessionAttributes(value={"my_user"})
//@RequestMapping("/stu")
@Controller
public class LoginHandler {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/index")
	public String loginSuccess(){
		
		return "index";
	}
	
	@ResponseBody
	@RequestMapping("/loginValidate")
	public String loginValidate(@RequestParam(value="username",required=true) String username,
			@RequestParam(value="password",required=true) String password,Map<String, Object> map){
		MyUser user = loginService.getMyUserByUsername(username);
		if (user == null) {
			return "2";
		}else if(!(password.trim()).equals(user.getPassword())){
			return "3";
		}else {
			map.put("my_user", user);
			return "1";
		}
	}
	
}
