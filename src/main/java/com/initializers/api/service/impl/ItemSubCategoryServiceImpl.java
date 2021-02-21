package com.initializers.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.initializers.api.model.ItemSubCategory;
import com.initializers.api.repo.ItemSubCategoryRepo;
import com.initializers.api.service.ItemSubCategoryService;

@Service
public class ItemSubCategoryServiceImpl implements ItemSubCategoryService {

	@Autowired
	private ItemSubCategoryRepo itemSubCategoryRepo;
//	@Autowired
//	private ItemCategoryRepo itemCategoryRepo;
//	@Autowired
//	private ItemDetailsService itemDetailsService;
//	@Autowired
//	private ItemCategoryService itemCategoryService;
//	@Autowired
//	private ItemAvailabilityService itemAvailabilityService;
//	@Autowired
//	private MongoTemplate mongoTemplate;


//	@Override
//	public List<Object> getItemSubCategory(String[] filter, Suggestions suggestions) {
//		List<AggregationOperation> list = new ArrayList<AggregationOperation>();
//		if (suggestions.getKey() != null && suggestions.getValue() != null) {
//			List<Object> suggestionsList = new ArrayList<>();
//			list.add(Aggregation.project(Fields.fields().and(Fields.field("key", suggestions.getKey()))
//					.and(Fields.field("value", suggestions.getValue()))));
//			Aggregation aggregation = Aggregation.newAggregation(list);
//			suggestionsList = mongoTemplate.aggregate(aggregation, "item_sub_category", Suggestions.class)
//					.getMappedResults().stream().collect(Collectors.toList());
//			return suggestionsList;
//		} else {
//			list.add(LookupOperation.newLookup().from("item_category").localField("categoryId").foreignField("_id")
//					.as("join_cat"));
//			if (filter != null) {
//				List<FilterValue> filterValues = new FilterAction().getFilterValue(filter);
//				for (FilterValue filterValue : filterValues) {
//					List<Object> alteredFilterValue = new ArrayList<>();
//					if (filterValue.getName() != null && filterValue.getOperator() != null
//							&& filterValue.getValue() != null) {
//						switch (filterValue.getName()) {
//						case "categoryId":
//							alteredFilterValue = filterValue.getValue().stream().map(Long::parseLong)
//									.collect(Collectors.toList());
//							break;
//						case "_id":
//							alteredFilterValue = filterValue.getValue().stream().map(Long::parseLong)
//									.collect(Collectors.toList());
//							break;
//						default:
//							alteredFilterValue = filterValue.getValue().stream().collect(Collectors.toList());
//							break;
//						}
//						list.add(Aggregation.match(Criteria.where(filterValue.getName()).in(alteredFilterValue)));
//					}
//				}
//			}
//			list.add(Aggregation.project(Fields.fields("_id", "description", "name", "imageLink", "offer")
//					.and(Fields.field("categoryId", "join_cat.name"))));
//			Aggregation aggregation = Aggregation.newAggregation(list);
//			List<ItemSubCategoryList> itemSubCategory = mongoTemplate
//					.aggregate(aggregation, "item_sub_category", ItemSubCategoryList.class).getMappedResults();
//			return itemSubCategory.stream().collect(Collectors.toList());
//		}
////		return itemSubCategoryRepo.findAll().stream().collect(Collectors.toList());
//	}

//	@Override
//	public ItemSubCategory getItemSubCategoryById(Long id) {
//		return itemSubCategoryRepo.findFirstById(id);
//	}


	@Override
	public Page<ItemSubCategory> getItemSubCategoryByCategoryId(Long categoryId, Pageable pageable) {
		return itemSubCategoryRepo.findByCategoryId(categoryId, pageable);
	}

	@Override
	public List<ItemSubCategory> getItemSubCategoryByCategoryId(Long categoryId) {
		return itemSubCategoryRepo.findByCategoryId(categoryId);
	}


//	@Override
//	public boolean checkItemSubCategory(Long itemSubCategoryId) {
//		if (itemSubCategoryRepo.findFirstById(itemSubCategoryId) != null) {
//			return true;
//		}
//		return false;
//	}


//	@Override
//	public ItemSubCategory getMaxOfferItemSubCategoryByCategoryId(Long categoryId) {
//		return itemSubCategoryRepo.findTopByCategoryIdOrderByOfferDesc(categoryId);
//	}
//
//	@Override
//	public String getItemSubCategoryName(Long id) {
//		ItemSubCategory itemSubCategory = itemSubCategoryRepo.findFirstNameById(id);
//
//		return itemSubCategory == null ? "" : itemSubCategory.getName();
//	}

}
