package com.initializers.api.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.initializers.api.model.UserOrderSet;

public interface UserOrderSetRepo extends MongoRepository<UserOrderSet, Long>{
	
	UserOrderSet findFirstByPreviousApiId(Long id);
	
	Page<UserOrderSet> findByOrderListUserId(Long userId, Pageable pageable);
	
}