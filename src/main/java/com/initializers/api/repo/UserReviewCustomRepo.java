package com.initializers.api.repo;

public interface UserReviewCustomRepo {
	
	Float findAverageRatingByItemId(Long itemId);
}
