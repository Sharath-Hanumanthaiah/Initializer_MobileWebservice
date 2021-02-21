package com.initializers.api.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.initializers.api.model.HomePage;

@Service
public interface HomePageService {
	
	Page<HomePage> getHomePage(Integer first, Integer after);
	
}
