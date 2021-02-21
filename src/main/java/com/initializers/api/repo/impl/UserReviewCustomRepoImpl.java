package com.initializers.api.repo.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.query.Criteria;

import com.initializers.api.model.UserReview;
import com.initializers.api.repo.UserReviewCustomRepo;

public class UserReviewCustomRepoImpl implements UserReviewCustomRepo {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public Float findAverageRatingByItemId(Long itemId) {
		// TODO Auto-generated method stub
		UserReview.CompositeKey t = new UserReview.CompositeKey();
		t.setItemId(itemId);
		List<AggregationOperation> list = new ArrayList<AggregationOperation>();
		list.add(Aggregation.unwind("_id"));
		list.add(Aggregation.match( Criteria.where("_id.itemId").
				in(1)));
		list.add(Aggregation.group("_id.itemId").avg("rating").as("averageRating"));
		Aggregation aggregation = Aggregation.newAggregation(list);
		List<UserReview> results = mongoTemplate.aggregate(aggregation, "user_review", UserReview.class)
				.getMappedResults();
//		return results.getAverageRating();
		return 1F;
	}
}
