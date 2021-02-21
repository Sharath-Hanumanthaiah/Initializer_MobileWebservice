package com.initializers.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.initializers.api.exception.ItemNotFoundException;
import com.initializers.api.model.ItemAvailability;
import com.initializers.api.model.ItemDetails;
import com.initializers.api.model.UserWishlist;
import com.initializers.api.repo.ItemDetailsRepo;
import com.initializers.api.repo.UserReviewRepo;
import com.initializers.api.service.ItemAvailabilityService;
import com.initializers.api.service.ItemDetailsService;
import com.initializers.api.service.UserWishlistService;

@Service
public class ItemDetailsServiceImpl implements ItemDetailsService {

//	@Autowired
//	private ItemCategoryService itemCategoryService;
	@Autowired
	private ItemDetailsRepo itemDetailsRepo;
	@Autowired
	private ItemAvailabilityService itemAvailabilityService;
	@Autowired
	private UserReviewRepo userReviewRepo;
	@Autowired
	private UserWishlistService userWishlistService;

	@Override
	public ItemDetails getItemDetails(Long userId, Long itemId) {
		ItemDetails itemDetails = itemDetailsRepo.findFirstId(itemId);
		if (itemDetails == null) {
			throw new ItemNotFoundException("Item Not found ");
		} else {
			List<ItemAvailability> itemAvailability = itemAvailabilityService.getAvailabilityByItemId(itemId);
			try {
				itemDetails.setAverageRating(userReviewRepo.findByIdItemIdAvgRating(itemId));				
			} catch(NullPointerException exception) {
				//handle exception
			}
			itemDetails.setItemAvailability(itemAvailability);
			UserWishlist userWishlist = userWishlistService.getUserWishlist(userId);
			if(userWishlist != null && userWishlist.getItemsId().contains(itemId)) {
				itemDetails.setIsWishlist(true);
			} else {
				itemDetails.setIsWishlist(false);
			}
			return itemDetails;
		}
	}

//	@Override
//	public Object getItemDetailsByCategory(Long categoryID, Pageable pageable) {
////		Map<String, Object> returnVal = new HashMap<>();
//		List<ItemSubCategory> itemSubCategory = itemSubCategoryService.getItemSubCategoryByCategoryId(categoryID, pageable);
//		if (itemSubCategory != null && itemSubCategory.size() > 0) {
////			returnVal.put("returnType", "Sub Category");
////			returnVal.put("value", itemSubCategory);
//			return itemSubCategory;
////			return returnVal;
//		} else {
//			List<ItemListTemp> itemList = new ArrayList<>();
//			for (ItemDetails itemDetails : itemDetailsRepo.findByCategoryId(categoryID, pageable)) {
//				if(itemDetails.getStatus().equals("Active")) {
//					itemList.add(getItemListById(itemDetails.getId()));
//				}
//			}
//			if(itemList.size() <= 0 && pageable.getPageNumber() == 0) {
//				throw new ItemNotFoundException();
//			}
//			return itemList;
//		}
//	}

	@Override
	public Page<ItemDetails> getItemDetailsBySubCategory(Long subCategoryID, Pageable pageable) {
		return itemDetailsRepo.findBySubCategoryId(subCategoryID, pageable);
//		if(itemDetails.size() <= 0 && pageable.getPageNumber() == 0) throw new ItemNotFoundException();
//		
//			List<ItemListTemp> itemListTemp = new ArrayList<>();
//			for(ItemDetails item : itemDetails) {
//				if(item.getStatus().equals("Active")) {
//					itemListTemp.add(getItemListById(item.getId()));
//				}
//			}
//			return itemListTemp;
	} 
	
	@Override
	public List<ItemDetails> getItemDetailsBySubCategory(Long subCategoryID) {
		return itemDetailsRepo.findBySubCategoryId(subCategoryID);
	} 
	
	@Override
	public Page<ItemDetails> getItemDetailsByCategory(Long categoryID,  Pageable pageable) {
		return itemDetailsRepo.findByCategoryId(categoryID, pageable);
	} 

//	@Override
//	public List<ItemDetailsSearch> searchItemDetails(String name) {
//		// TODO Auto-generated method stub
//		List<ItemDetails> itemList = itemDetailsRepo.findCategoryIdByNameRegex(name);
//		List<ItemDetailsSearch> returnItemList = new ArrayList<>();
//		if(itemList != null) {
//			int counter = 1;
//			for (Iterator<ItemDetails> iterator = itemList.iterator(); iterator.hasNext();) {
//				if (counter > 10) {
//					break;
//				}
//				ItemDetailsSearch tempItem = new ItemDetailsSearch();
//				ItemDetails itemDetails = (ItemDetails) iterator.next();
//				if(!itemDetails.getStatus().equals("Active")) {
//					continue;
//				}
//				tempItem.setId(itemDetails.getId());
//				tempItem.setName(itemDetails.getName());
//				returnItemList.add(tempItem);
//				counter++;
//			}
//		}
//		return returnItemList;
//	}

	@Override
	public ItemDetails getItemListById(Long id, Long... availabilityId) {
		ItemDetails itemDetails = new ItemDetails();
		itemDetails = itemDetailsRepo.findListByIdByStatus(id, "Active");

		if (itemDetails != null) {
			if (itemDetails.getImageLinks() != null && itemDetails.getImageLinks().size() > 0) {
				itemDetails.setImageLink(itemDetails.getImageLinks().get(0));
			}
			List<ItemAvailability> itemAvailability = itemAvailabilityService.getAvailabilityByItemId(id);

			itemDetails.setItemAvailability(itemAvailability);

			return itemDetails;
		}
		return null;
	}

	@Override
	public ItemDetails getItemNameImageLinkById(Long id) {
		return itemDetailsRepo.findFirstNameImageLinksById(id);
	}

	@Override
	public Boolean checkItemDetails(Long id) {
		//true - exist , false - doesn't exist 
		if (itemDetailsRepo.findFirstNameById(id) != null) {
			return true;
		}
		return false;
	}

//	@Override
//	public List<Object> getItemDetailsList(String[] filter, Suggestions suggestions) {
//		List<AggregationOperation> list = new ArrayList<AggregationOperation>();
//		if(suggestions.getKey() != null && suggestions.getValue() != null) {
//			List<Object> suggestionsList = new ArrayList<>();
//			list.add(Aggregation.project(Fields.fields().
//					and(Fields.field("key", suggestions.getKey())).
//					and(Fields.field("value", suggestions.getValue()))));
//			Aggregation aggregation = Aggregation.newAggregation(list);
//			suggestionsList = mongoTemplate.aggregate(aggregation, "item_details", Suggestions.class)
//					.getMappedResults().stream().collect(Collectors.toList());
//			return suggestionsList;
//		}else {
//			List<Object> itemDetailsList = new ArrayList<>();
//			list.add(LookupOperation.newLookup().from("item_sub_category").localField("subCategoryId")
//					.foreignField("_id").as("join_subcat"));
//			list.add(LookupOperation.newLookup().from("item_category").localField("categoryId")
//					.foreignField("_id").as("join_cat"));
//			if(filter != null) {
//				List<FilterValue> filterValues =  new FilterAction().getFilterValue(filter);
//				for(FilterValue filterValue : filterValues) {
//					List<Object> alteredFilterValue = new ArrayList<>();
//					if(filterValue.getName() != null && filterValue.getOperator() != null 
//							&& filterValue.getValue() != null) {
//						switch (filterValue.getName()) {
//						case "subCategoryId":
//							alteredFilterValue = filterValue.getValue().stream().map(Long::parseLong).
//							collect(Collectors.toList());
//							break;
//						case "categoryId":
//							alteredFilterValue = filterValue.getValue().stream().map(Long::parseLong).
//							collect(Collectors.toList());
//							break;
//						case "_id":
//							alteredFilterValue = filterValue.getValue().stream().map(Long::parseLong).
//							collect(Collectors.toList());
//							break;
//						default:
//							alteredFilterValue = filterValue.getValue().stream().
//							collect(Collectors.toList());
//							break;
//						}
//						list.add(Aggregation.match( Criteria.where(filterValue.getName()).
//								in(alteredFilterValue)));
//					}
//				}
//			}
//			list.add(Aggregation.project(Fields.fields("_id","name","imageLinks","status").
//					and(Fields.field("subCategoryId", "join_subcat.name")).
//					and(Fields.field("categoryId", "join_cat.name"))));
//			Aggregation aggregation = Aggregation.newAggregation(list);
//			List<ItemDetailsList> results = mongoTemplate.aggregate(aggregation, "item_details", ItemDetailsList.class)
//					.getMappedResults();
//			for(ItemDetailsList item : results) {
//				Map<String, Object> resultVal = new HashMap<String, Object>();
//				resultVal.put("id", item.getId());
//				resultVal.put("name", item.getName());
//				resultVal.put("categoryId", item.getCategoryId());
//				resultVal.put("subCategoryId", item.getSubCategoryId());
//				List<String> images = item.getImageLinks();
//				resultVal.put("imageLinks", images != null && images.size() > 0?images.get(0):"");				
//				String status = item.getStatus();
//				resultVal.put("status", status);
//				resultVal.put("state", status.equals("Active")? "Success": "Error");
//				itemDetailsList.add(resultVal);
//			}
//			return itemDetailsList;	
//		}
//	}

}
