package com.initializers.api.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.initializers.api.exception.ItemNotFoundException;
import com.initializers.api.exception.UserNotFoundException;
import com.initializers.api.model.ItemDetails;
import com.initializers.api.model.UserWishlist;
import com.initializers.api.repo.UserWishlistRepo;
import com.initializers.api.service.ItemDetailsService;
import com.initializers.api.service.UserDetailsService;
import com.initializers.api.service.UserWishlistService;

@Service
public class UserWishlistServiceImpl implements UserWishlistService {

	@Autowired
	private UserWishlistRepo userWishListRepo;
	@Autowired
	private ItemDetailsService itemDetailsService;
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public UserWishlist addUserWishlist(Long userID, Long itemID) {
		UserWishlist dbUserWishlist = userWishListRepo.findFirstByUserID(userID);

		if (dbUserWishlist == null) {
			dbUserWishlist = new UserWishlist();
			if (userDetailsService.checkUser(userID)) {
				throw new UserNotFoundException("User Not found");
			}
			dbUserWishlist.setUserID(userID);
		}
		if (!itemDetailsService.checkItemDetails(itemID)) {
			throw new ItemNotFoundException("Item not found");
		}
		dbUserWishlist.setItemsId(itemID);
		userWishListRepo.save(dbUserWishlist);
		return dbUserWishlist;
	}

	@Override
	public Map<String, Object> getUserWishlist(Long userID, Pageable page) {
		Map<String, Object> returnVal = new HashMap<>();
		UserWishlist userWishlist = userWishListRepo.findFirstByUserID(userID);
//		Page<UserWishlist> userWishlistPage 
		
		int after = page.getPageNumber();
		int pageSize = page.getPageSize();
		int start = after;
//		int end =  (after + 1) * pageSize;

		int count = 0;
//		if (after > 0) {
//			start = (after - 1) * pageSize;
//			end = after * pageSize;
//		}
//		int count = 0;
		if (userWishlist == null) {
			throw new UserNotFoundException("User Not found");
		}
		Set<Long> itemsId = userWishlist.getItemsId();
		List<Long> itemsIdList = new ArrayList<>(itemsId);
		List<ItemDetails> itemDetailsList = new ArrayList<ItemDetails>();
//		if (end > itemsId.size()) {
//			end = itemsId.size();
//		}
//		
		System.out.println("size" + itemsIdList.size());
		//should fetch given pagesize and check if 
		while((start < itemsIdList.size()) && (count < pageSize)) {
			ItemDetails itemDetails = itemDetailsService.getItemListById(itemsIdList.get(start));
			start++;
			if (itemDetails != null) {
				itemDetails.setIsWishlist(true);
				itemDetailsList.add(itemDetails);
				count++;
			}
		}
		returnVal.put("endCursor", start);
		if(start >= itemsIdList.size()) {
			returnVal.put("hasNextPage", false);
//			page = PageRequest.of(after, (start - (after * pageSize)));			
		} else {
			returnVal.put("hasNextPage", true);
		}
//		for (int i = start; i < end; i++) {
//			ItemDetails itemDetails = itemDetailsService.getItemListById(itemsIdList.get(i));
//			if (itemDetails != null) {
//				itemDetails.setIsWishlist(true);
//				itemDetailsList.add(itemDetails);
//			}
//		}
//		for(Long item : itemsId) {
//			ItemDetails itemDetails = itemDetailsService.getItemListById(item);
//			if(itemDetails != null) {
//				itemDetailsList.add(itemDetails);	
//			}
//		}
//		userWishlist.setItemDetails(itemDetailsList);
		returnVal.put("page", new PageImpl<>(itemDetailsList, page, itemsId.size()));
//		return new PageImpl<>(itemDetailsList, page, itemsId.size());
		return returnVal;
	}

	@Override
	public Object deleteUserWishlist(Long userID, Long itemID) {
		UserWishlist userWishlist = userWishListRepo.findFirstByUserID(userID);
//		Set<Long> items = userWishlist.getItemsId();
//		items.remove(userWishlistTemp.getItemsId());
		if (userWishlist != null) {
			userWishlist.getItemsId().remove(itemID);
			userWishListRepo.save(userWishlist);
			return itemID;
		}
		return null;
	}

	@Override
	public UserWishlist getUserWishlist(Long userID) {
		return userWishListRepo.findFirstByUserID(userID);
	}
}
