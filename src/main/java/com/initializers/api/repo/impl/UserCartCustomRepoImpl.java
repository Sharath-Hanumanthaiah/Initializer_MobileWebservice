package com.initializers.api.repo.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;

import com.initializers.api.constant.Status;
import com.initializers.api.model.UserCartList;
import com.initializers.api.repo.UserCartCustomRepo;

public class UserCartCustomRepoImpl implements UserCartCustomRepo {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public Page<UserCartList> getUserCart(Long userId, Pageable pageable) {
		
		List<AggregationOperation> list = new ArrayList<AggregationOperation>();
			list.add(LookupOperation.newLookup().from("item_details").localField("_id.itemId")
					.foreignField("_id").as("join_item"));
			list.add(LookupOperation.newLookup().from("item_availability").localField("_id.availabilityId")
					.foreignField("_id").as("join_availability"));
			list.add(Aggregation.match( Criteria.where("_id.userId").
					is(userId)));
			list.add(Aggregation.match( Criteria.where("join_item.status").
					is(Status.Active)));
			list.add(Aggregation.match( Criteria.where("join_availability.available").
					is(Status.Active)));
			list.add(Aggregation.project(Fields.fields("_id","quantity", "unit", "value")
					.
					and(Fields.field("itemName", "join_item.name")).
					and(Fields.field("imageLinks", "join_item.imageLinks")).
					and(Fields.field("discountPrice", "join_availability.discountPrice")).
					and(Fields.field("unit", "join_availability.unit")).
					and(Fields.field("value", "join_availability.value"))));
			Aggregation aggregation = Aggregation.newAggregation(list);
			List<UserCartList> results = mongoTemplate.aggregate(aggregation, "user_cart", UserCartList.class)
					.getMappedResults();
			int start = (int) pageable.getPageNumber() * pageable.getPageSize();
			int end = (start + pageable.getPageSize()) > results.size() ? results.size() : (start + pageable.getPageSize());
			return new PageImpl<>(results.subList(start, end), pageable, results.size());
	
	}
	
}
