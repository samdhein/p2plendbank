package com.samhein.p2plendbank.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.samhein.p2plendbank.models.Account;
import com.samhein.p2plendbank.services.AccountService;
import com.samhein.p2plendbank.services.UserService;

@Controller
public class BankController {

	//services
	@Autowired 
	private UserService userService;
	
	@Autowired 
	private AccountService accountService;
	
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
	
	//deposit, withdraw
	@GetMapping("/cashops")
	public String cashops(HttpSession session, Model model) {
		if(session.getAttribute("userId")==null) {
			return "redirect:/";
		} else {
			Long userId = (Long) session.getAttribute("userId");
			model.addAttribute("acctBal", userService.findOne(userId).getAccount().getAccountBalance());
			model.addAttribute("acctId", userService.findOne(userId).getAccount().getId());
			return "cashops.jsp";
		}
		
	}
	
	@PostMapping("/cashops")
	public String processCashops(
			Model model,
			@RequestParam("acctId") Long acctId, 
			@RequestParam("transactionType") String transactionType, 
			@RequestParam("amount") Double amount,
			@RequestParam("userId") Long userId
			){
		Account updatedAccount = accountService.findOne(acctId);
		Double currentBal = updatedAccount.getAccountBalance();
		if (amount<0) { // check positive amount input. breaks when amount is zero; doubles are non-nullable
			model.addAttribute("error", "Transaction amount must be a positive number");
			model.addAttribute("acctBal", currentBal);
			model.addAttribute("acctId", updatedAccount.getId());
			return "cashops.jsp";
		}
		if (transactionType.equals("withdraw")){ // error if currentBal insufficient for withdrawal
			if(amount>currentBal) {
				model.addAttribute("error", "Insufficient funds");
				model.addAttribute("acctBal", currentBal);
				model.addAttribute("acctId", updatedAccount.getId());
				return "cashops.jsp";
			} else { // process withdrawal
			Double updatedBal = updatedAccount.getAccountBalance() - amount;
			updatedAccount.setAccountBalance(updatedBal);
			accountService.saveAccount(updatedAccount);
			return "redirect:/dashboard";
			}
		} else { // process deposit
			Double updatedBal = updatedAccount.getAccountBalance() + amount;
			updatedAccount.setAccountBalance(updatedBal);
			accountService.saveAccount(updatedAccount);
			return "redirect:/dashboard";
		}
	}

	// send money		
	@GetMapping("/transfer")
	public String transfer(HttpSession session, Model model) {
		if (session.getAttribute("userId")==null){
			return "redirect:/";
		} else {
			model.addAttribute("allUsers", userService.allUsers());
			return "transfer.jsp";
		}
	}
		
	
}
