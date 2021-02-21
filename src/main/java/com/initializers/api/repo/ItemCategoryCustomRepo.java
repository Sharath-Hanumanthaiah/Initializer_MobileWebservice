package com.initializers.api.repo;

import java.util.List;

import com.initializers.api.model.ItemCategory;

public interface ItemCategoryCustomRepo {
	
	List<ItemCategory> findItemCategoryJoinSubCategory(Integer first, Integer after);
}
