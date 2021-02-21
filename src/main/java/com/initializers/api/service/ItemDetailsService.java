package com.initializers.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.initializers.api.model.ItemDetails;

@Service
public interface ItemDetailsService {
	
	ItemDetails getItemDetails(Long userId, Long itemId);
	
	Boolean checkItemDetails(Long id);
	
//	List<Object> getItemDetailsList(String[] filter, Suggestions suggestions);
	
//	Object getItemDetailsByCategory(Long categoryID, Pageable pageable);
	
	Page<ItemDetails> getItemDetailsBySubCategory(Long subCategoryID, Pageable pageable);
	
	List<ItemDetails> getItemDetailsBySubCategory(Long subCategoryID);
	
	Page<ItemDetails> getItemDetailsByCategory(Long categoryID, Pageable pageable);

	
//	List<ItemDetailsSearch> searchItemDetails(String name);
	
	ItemDetails getItemListById(Long id, Long... availabilityId);
	
	ItemDetails getItemNameImageLinkById(Long id);
	
}
