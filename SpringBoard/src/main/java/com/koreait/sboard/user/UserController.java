package com.koreait.sboard.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koreait.sboard.model.UserEntity;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping("/login")
	public void login(Model model) {
		model.addAttribute("list", service.selUserList());
	}
	
	@PostMapping("/login")
	public void loginProc(UserEntity param, HttpSession hs) {
		
	}
	
	@RequestMapping("/join")
	public void join(Model model) {}
	
	@PostMapping("/join")
	public String join(UserEntity param) {		 
		service.insUser(param);
		return "redirect:/user/login";
	}
}













