package com.koreait.sboard.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koreait.sboard.model.UserEntity;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;

	@GetMapping("/login")
	public void login(Model model) {	
		List<UserEntity> list = service.selUser();
		System.out.println(list);
	}
	
	@RequestMapping("/join")
	public void join(Model model) {	
			
	}
}





