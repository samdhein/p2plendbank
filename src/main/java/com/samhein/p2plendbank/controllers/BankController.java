package com.samhein.p2plendbank.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.samhein.p2plendbank.services.UserService;

@Controller
public class BankController {

	//services
	@Autowired 
	private UserService userService;
	
	//dashboard
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {
		if(session.getAttribute("userId")==null){
			return "redirect:/";
		} else {
			Long userId = (Long) session.getAttribute("userId");
			model.addAttribute("user", userService.findOne(userId));
			return "dashboard.jsp";			
		}
	}
	
}
