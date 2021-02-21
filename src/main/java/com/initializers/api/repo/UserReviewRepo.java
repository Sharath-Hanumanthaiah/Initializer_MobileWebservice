package com.initializers.api.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.initializers.api.model.UserReview;

public interface UserReviewRepo extends MongoRepository<UserReview, Long>, UserReviewCustomRepo{
	
	Page<UserReview> findByPreviousApiIdItemId(Long itemId, Pageable pageable);
	
//	@Aggregation(pipeline = {"{$match: {$and : [{_id.itemId: ?0}] }}"})
//	@Aggregation(pipeline = { "{ $match: { id: {itemId: ?0} } }"})
	@Aggregation(pipeline = { "{ $match: { '_id.itemId': ?0 } }"
			 ,"{ $group: { _id: '_id.itemId', rating: { $avg: $rating } } }"})
	Float findByIdItemIdAvgRating(Long itemId);
}
