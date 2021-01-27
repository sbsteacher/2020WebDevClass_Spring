package com.koreait.sboard.board;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.sboard.common.SecurityUtils;
import com.koreait.sboard.model.BoardDTO;
import com.koreait.sboard.model.BoardEntity;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@GetMapping("/home")
	public void home() {}
	
	@GetMapping("/list")
	public void list(Model model, BoardDTO p) {
		model.addAttribute("list", service.selBoardList(p));
	}
	
	@GetMapping("/reg")
	public String reg() {
		return "board/regmod";
	}
	
	@PostMapping("/reg")
	public String reg(BoardEntity p, HttpSession hs) {
		p.setI_user(SecurityUtils.getLoingUserPk(hs));
		service.insBoard(p);
		return "redirect:/board/detail?i_board=" + p.getI_board();
	}
	
	@GetMapping("/detail")
	public void detail(BoardDTO p, HttpSession hs, Model model) {
		p.setI_user(SecurityUtils.getLoingUserPk(hs));
		model.addAttribute("data", service.selBoard(p));
	}
		
	@ResponseBody
	@GetMapping("/del/{i_board}")	
	public Map<String, Object> del(BoardDTO p, HttpSession hs) {		
		System.out.println("i_board : " + p.getI_board());
		p.setI_user(SecurityUtils.getLoingUserPk(hs));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", service.delBoard(p));
		return map;
	}
}











