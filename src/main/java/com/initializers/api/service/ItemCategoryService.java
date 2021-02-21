package com.initializers.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.initializers.api.model.ItemCategory;

@Service
public interface ItemCategoryService {
	
	Page<ItemCategory> getItemCategory(Integer first, Integer after);
	
	List<ItemCategory> getItemCategory();
	
	List<ItemCategory> getItemCategoryContentById(Long CategoryId);
	
	Page<ItemCategory> getItemCategoryContentById(Integer first, Integer after, Long CategoryId);
}
