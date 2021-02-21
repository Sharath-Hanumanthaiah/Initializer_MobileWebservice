package com.initializers.api.repo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;

import com.initializers.api.model.ItemCategory;
import com.initializers.api.repo.ItemCategoryCustomRepo;

public class ItemCategoryCustomRepoImpl implements ItemCategoryCustomRepo {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<ItemCategory> findItemCategoryJoinSubCategory(Integer first, Integer after) {
		// TODO Auto-generated method stub
//		LookupOperation lookupOperation = LookupOperation.newLookup().from("item_sub_category").localField("_id")
//				.foreignField("categoryId").as("cat_join_subcat");

		ProjectionOperation projection = Aggregation.project(Fields.fields("_id","name","description","offer","imageLink"));
//		.and(Fields.field("departments._id", "subID"))
//		ProjectionOperation projection = Aggregation.project("_id","name","description","offer","imageLink","departments.categoryId");
//		GroupOperation sumZips = Aggregation.group("departments").count().as("zipCount");
//		
//		AggregationOperation aggunwind = Aggregation.unwind("departments");
		  
		Aggregation aggregation = Aggregation.newAggregation(projection,Aggregation.skip(after),
				Aggregation.limit(first));
		List<ItemCategory> results = mongoTemplate.aggregate(aggregation, "item_category", ItemCategory.class)
				.getMappedResults();
		return results;
	}

}
