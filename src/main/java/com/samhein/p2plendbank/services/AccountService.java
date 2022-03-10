package com.samhein.p2plendbank.services;

import java.util.Optional;

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
	
    public Account findOne(Long acctId) {
    	Optional<Account> potentialAccount = accountRepo.findById(acctId);
    	if(potentialAccount.isPresent()) {
    		Account account = potentialAccount.get();
    		return account;
    	} else {
    		return null;
    	}
    }
}
