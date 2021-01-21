package com.koreait.sboard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koreait.sboard.common.Utils;

@Controller
@RequestMapping("/user")
public class UserController {

	@GetMapping("/login")
	public void login(Model model) {	
		
	}
	
	@RequestMapping("/join")
	public void join(Model model) {	
			
	}
}





