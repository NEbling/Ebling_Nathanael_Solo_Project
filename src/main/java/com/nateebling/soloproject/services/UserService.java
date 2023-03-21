package com.nateebling.soloproject.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.nateebling.soloproject.models.LoginUser;
import com.nateebling.soloproject.models.User;
import com.nateebling.soloproject.repositories.UserRepository;

    
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public User registerNewUser(User newUser, BindingResult result) {
    	
    	Optional<User> registerAttempt = userRepository.findByEmail(newUser.getEmail());
    	
    	if(registerAttempt.isPresent()) {
    		result.rejectValue("email", "Matches", "Email already exists");
    	}
    	
    	if(!newUser.getPassword().equals(newUser.getConfirmPassword())) {
    		result.rejectValue("confirmPassword", "Matches", "Passwords don't match");
    	}
    	
    	if(result.hasErrors()) {
    		return null;    		
    	}
    	
    	String hashedPassword = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
    	
    	newUser.setPassword(hashedPassword);
    	
    	return userRepository.save(newUser);
    }
    public User loginExistingUser(LoginUser newLoginUser, BindingResult result) {
        Optional<User> loginAttempt = userRepository.findByEmail(newLoginUser.getEmail());
        
        if(loginAttempt.isEmpty()) {
        	result.rejectValue("email", "Matches", "Invalid login credentials");
        	return null;
        }
        
        User thisUser = loginAttempt.get();
        if(!BCrypt.checkpw(newLoginUser.getPassword(), thisUser.getPassword())) {
        	result.rejectValue("password", "Matches", "Invalid login credentials");
        }
        
        if(result.hasErrors()) {
        	return null;        	
        }
        
        return thisUser;
    }
    
    public User findUserById(Long id) {
    	Optional<User> thisUser = userRepository.findById(id);
    	if(thisUser.isPresent()) {
    		return thisUser.get();
    	}
    	return null;
    }
}