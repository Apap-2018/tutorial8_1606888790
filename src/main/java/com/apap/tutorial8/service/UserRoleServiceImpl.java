package com.apap.tutorial8.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.apap.tutorial8.model.UserRoleModel;
import com.apap.tutorial8.repository.UserRoleDb;

@Service
public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
	private UserRoleDb userDb;
	
	@Override
	public UserRoleModel addUser(UserRoleModel user) {
		String pass = encrypt(user.getPassword());
		user.setPassword(pass);
		return userDb.save(user);
	}

	@Override
	public String encrypt(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}

	@Override
	public UserRoleModel findUserByUsername(String username) {
		return userDb.findByUsername(username);
	}

	@Override
	public boolean checkCurrentPassword(UserRoleModel user, String oldPassword) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(oldPassword,user.getPassword());
	}

	@Override
	public boolean checkMatchPas(String newPassword, String newPasswordConfirm) {
		return newPassword.equals(newPasswordConfirm);
	}

	@Override
	public boolean checkCondition(String password) {
		boolean passwordContainsDigit = false;
	    boolean passwordContainsLetter = false;

	    if (!(password.length() >= 8)){
	        return false;
	    }

	    for (char c : password.toCharArray()) {
	    	if (Character.isDigit(c)) {
	            passwordContainsDigit = true;
	        }
	    }
	    
	    if (!passwordContainsDigit)
	        return false;
	    
	    passwordContainsLetter = password.matches(".*[a-zA-Z]+.*");
	    return passwordContainsLetter;
	}

}
