package com.initializers.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.initializers.api.model.UserOrderSet;

@Service
public interface UserOrderService {
	
	Page<UserOrderSet> getUserOrderList(Long userId, Pageable pageable);
	
	UserOrderSet getUserOrder(Long id);
}
