package com.springboottrain.app.controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springboottrain.app.domain.Member;
import com.springboottrain.app.service.MemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@SessionAttributes("member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@PostMapping("/login")
	public String login(Model model, 
			@RequestParam("userId") String id, 
			@RequestParam("pass") String pass, 
			HttpSession session, HttpServletResponse response)
	throws ServletException, IOException {
	// MemberService 클래스를 사용해 로그인 성공여부 확인
	int result = memberService.login(id, pass);
	if(result == -1) { // 회원 아이디가 존재하지 않으면
	response.setContentType("text/html; charset=utf-8");
	PrintWriter out = response.getWriter();
	out.println("<script>");
	out.println(" alert('존재하지 않는 아이디 입니다.');");
	out.println(" history.back();");
	out.println("</script>");
	
	return null;
	}else if(result == 0) { // 비밀번호가 틀리면
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println(" alert('비밀번호가 다릅니다.');");
		out.println(" location.href='loginForm'");
		out.println("</script>");
		return null;
		}
	Member member = memberService.getMember(id);
	session.setAttribute("isLogin", true);
	model.addAttribute("member", member);
	System.out.println("member.name : " + member.getName());
	return "redirect:/boardList";
	}
	
	@GetMapping("/memberLogout")
	public String logout(HttpSession session) {
		log.info("MemberController.logout(HttpSession session");
		session.invalidate();
		return "redirect:/loginForm";
	}
	
	@RequestMapping("/overlapIdCheck")
	public String overlapIdCheck(Model model, @RequestParam("id") String id) {
		
		boolean overlap = memberService.overlapIdCheck(id);
		model.addAttribute("id", id);
		model.addAttribute("oberlap", overlap);
		
		return "member/overlapIdCheck.html";
	}
	
}
