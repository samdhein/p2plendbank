package com.samhein.p2plendbank.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.samhein.p2plendbank.models.LoginUser;
import com.samhein.p2plendbank.models.User;
import com.samhein.p2plendbank.services.UserService;



@Controller
public class UserController {
 	
	@Autowired
	private UserService userService;
	
	 
	@GetMapping("/")
	public String index(Model model) {
	 
	     // Bind empty User and LoginUser objects to the JSP
	     // to capture the form input
	     model.addAttribute("newUser", new User()); // for register
	     model.addAttribute("newLogin", new LoginUser()); // for login
	     return "index.jsp";
	}
	 
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, 
			BindingResult result, Model model, HttpSession session) {
	    
		userService.register(newUser, result);
		
	    if(result.hasErrors()) { // unsuccessful
	    	model.addAttribute("newLogin", new LoginUser());
	        return "index.jsp";
	    } else { // successful
	    	session.setAttribute("userId", newUser.getId());
	       	return "redirect:/dashboard";
	    }
	}
	 
	 @PostMapping("/login")
	 public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
	         BindingResult result, Model model, HttpSession session) {

		 User user = userService.login(newLogin, result);
	 
	     if(result.hasErrors()) {
	         model.addAttribute("newUser", new User());
	         return "index.jsp";
	     }
	     
	     session.setAttribute("userId", user.getId());
	     return "redirect:/dashboard";
	 }

	 @GetMapping("/logout")
	 public String logout(HttpSession session) {
		 session.invalidate();
		 return "redirect:/";
		 
	 }

	 
}