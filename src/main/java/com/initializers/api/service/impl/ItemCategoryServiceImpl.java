package com.initializers.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.initializers.api.model.ItemCategory;
import com.initializers.api.repo.ItemCategoryRepo;
import com.initializers.api.service.ItemCategoryService;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

	@Autowired 
	private ItemCategoryRepo itemCategoryRepo;
	@Override
	public Page<ItemCategory> getItemCategory(Integer first, Integer after) {
		Pageable page = PageRequest.of(after, first);
		return itemCategoryRepo.findAll(page);
	}
	@Override
	public List<ItemCategory> getItemCategory() {
		return itemCategoryRepo.findAll();
	}
	@Override
	public Page<ItemCategory> getItemCategoryContentById(Integer first, Integer after, Long CategoryId) {
		Pageable page = PageRequest.of(after, first);
		return itemCategoryRepo.findAll(page);
	}
	@Override
	public List<ItemCategory> getItemCategoryContentById(Long CategoryId) {
		return itemCategoryRepo.findAll();
	}
	
}