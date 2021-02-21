package com.initializers.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.initializers.api.model.UserReview;

@Service
public interface UserReviewService {

	Page<UserReview> findReviewByItemId(Long itemId, Pageable pageable); 
	
	
}
