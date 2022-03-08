package com.samhein.p2plendbank.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.samhein.p2plendbank.models.User;

public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByEmail(String email); // must be optional for findby
	Optional<User> findbyId(Long id);
}
