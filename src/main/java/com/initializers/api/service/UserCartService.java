package com.initializers.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.initializers.api.model.UserCart;
import com.initializers.api.model.UserCartList;
import com.initializers.api.model.output.CreateOrderOutput;

@Service
public interface UserCartService {

	UserCart addUserCart(UserCart userCart);
	
	Page<UserCartList> getUserCartByuserId(Long userId, Pageable pageable);
	
	CreateOrderOutput createOrder(Long userId, Long addressId,String coupenCode);
}
