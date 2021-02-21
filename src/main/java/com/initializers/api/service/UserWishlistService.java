package com.initializers.api.service;

import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.initializers.api.model.UserWishlist;

@Service
public interface UserWishlistService {

	UserWishlist addUserWishlist(Long userID, Long itemID);
	
	Map<String, Object> getUserWishlist(Long userID, Pageable page);
	
	UserWishlist getUserWishlist(Long userID);
	
	Object deleteUserWishlist(Long userID, Long itemID);
	
}
