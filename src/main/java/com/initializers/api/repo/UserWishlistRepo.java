package com.initializers.api.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.initializers.api.model.UserWishlist;

public interface UserWishlistRepo extends MongoRepository<UserWishlist, Long>{

	UserWishlist findFirstByUserID(Long userID);
	
}
