package com.samhein.p2plendbank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samhein.p2plendbank.models.Account;
import com.samhein.p2plendbank.repositories.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepo;
	
	// create
	public Account saveAccount(Account account) {
		return accountRepo.save(account);
	}
	
}
