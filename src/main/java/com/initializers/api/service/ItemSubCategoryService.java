package com.initializers.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.initializers.api.model.ItemSubCategory;

@Service
public interface ItemSubCategoryService {

//	Object addItemSubCategory(ItemSubCategory itemSubCategory, MultipartFile image);
//	
//	ItemSubCategory addItemSubCategoryAdmin(ItemSubCategory itemSubCategory);
//	
//	List<Object> getItemSubCategory(String[] filter, Suggestions suggestions);
	
//	ItemSubCategory getItemSubCategoryById(Long id);
//	
//	String getItemSubCategoryName(Long id);
//	
//	ItemSubCategory getMaxOfferItemSubCategoryByCategoryId(Long categoryId);
//
//	Map<String, Object> deleteItemSubCategoryByCategoryId(Long categoryId);
//	
//	Map<String, Object> deleteItemSubCategoryById(Long subCategoryId);
//	
//	Map<String, Object> updateSubCategory(ItemSubCategory itemSubCategory, MultipartFile image);
//	
//	ItemSubCategory updateSubCategoryAdmin(ItemSubCategory itemSubCategory);
//	
//	Map<String, Object> updateItemSubCategoryImage(MultipartFile image, Long subCategoryId);
//	
	Page<ItemSubCategory> getItemSubCategoryByCategoryId(Long categoryId, Pageable pageable);
	
	List<ItemSubCategory> getItemSubCategoryByCategoryId(Long categoryId);
	
//	Map<String, Object> updateItemSubCategoryDescription(ItemSubCategory itemSubCategory, Long subCategoryId);
//	
//	boolean checkItemSubCategory(Long itemSubCategoryId);
//	
//	void updateItemSubCategoryOffer(Long itemId, Long offer);
}
