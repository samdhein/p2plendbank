package com.samhein.p2plendbank.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.samhein.p2plendbank.models.LoginUser;
import com.samhein.p2plendbank.models.User;
import com.samhein.p2plendbank.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;
    
    public User register(User newUser, BindingResult result) {

    	// 1. Find user in the database by email
    	Optional<User> potentialUser = userRepo.findByEmail(newUser.getEmail());
    
    	// 2. check email--if present, reject
    	if(potentialUser.isPresent()) { // exists, fail validation
    		result.rejectValue("email", "unique", "This email is already in use!");
    	}
    	
    	// 3. check newUser password != newUser confirm, reject
    	if(!newUser.getPassword().equals(newUser.getConfirm())) // not equal, fail validation
    		result.rejectValue("confirm", "match", "The confirmed password does not match.");
    	
    	// 4. If any errors above, return null (go back to controller) 
        if(result.hasErrors()) {
        	return null;
        }
    	// 5. Hash and set password, save user to database
        String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        newUser.setPassword(hashed);
        return userRepo.save(newUser);
    }
    
    public User login(LoginUser newLogin, BindingResult result) {
    
    	// 1. Find user in the database by email
    	Optional<User> potentialUser = userRepo.findByEmail(newLogin.getEmail());
    	
    	// 2. Check email... if not present, reject
    	if(!potentialUser.isPresent()) { // doesn't exist, fail validation
    		result.rejectValue("email", "unique", "Unknown email");
    		return null;
    	}
    	
    	// 3.1: grab user from db
    	User user = potentialUser.get();
    	
    	// 3.2 Check password with BCrypt... if not match, reject
    	if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
    	    result.rejectValue("password", "Matches", "Invalid Password!");
    	}
    	
    	// 4. If the result has any errors, return null
    	if(result.hasErrors()) {
    		return null;
    	}
    	
    	// 5. No errors, return the user object
    	return user;
    	
    }
    
    public User findOne(Long userId) {
    	Optional<User> potentialUser = userRepo.findById(userId);
    	if(potentialUser.isPresent()) {
    		User user = potentialUser.get();
    		return user;
    	} else {
    		return null;
    	}
    }
    public List<User> allUsers(){
    	return (List<User>) userRepo.findAll();
    }
    

}

