package com.samhein.p2plendbank.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.samhein.p2plendbank.models.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
	
	Optional<Account> findByUserId(String user_id); // must be optional for findby
	Optional<Account> findById(Long id);
}
