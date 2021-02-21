package com.initializers.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.initializers.api.model.HomePage;
import com.initializers.api.repo.HomePageRepo;
import com.initializers.api.service.HomePageService;

@Service
public class HomePageServiceImpl implements HomePageService {
	
	@Autowired
	HomePageRepo homePageRepo;
	
	@Override
	public Page<HomePage> getHomePage(Integer first, Integer after) {
		Pageable page = PageRequest.of(after, first);
		return homePageRepo.findAll(page);
	}

}
