package com.initializers.api.service;

import org.springframework.stereotype.Service;

import com.initializers.api.model.UserDetails;

@Service
public interface UserDetailsService {
	

	Long addUser(UserDetails userDetails);
	
	UserDetails getUser(Long userId);
	
	Boolean checkUser(Long userId);
	
	Object deleteUser(Long userId);
	
	UserDetails updateUserDetail(UserDetails userDetails);
	
	String getNameById(Long userId);
	
}
