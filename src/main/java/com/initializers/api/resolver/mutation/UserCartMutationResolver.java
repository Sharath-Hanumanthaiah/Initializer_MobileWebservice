package com.initializers.api.resolver.mutation;

import java.util.Base64;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.initializers.api.constant.IdTypes;
import com.initializers.api.model.UserCart;
import com.initializers.api.model.UserCartList;
import com.initializers.api.model.input.CreateOrderInput;
import com.initializers.api.model.input.UserCartInput;
import com.initializers.api.model.output.CreateOrderOutput;
import com.initializers.api.service.UserCartService;

@Component
public class UserCartMutationResolver implements GraphQLMutationResolver{

	@Autowired
	UserCartService userCartService;
	
	public UserCartList addUserCart(UserCartInput userCartInput) {
		UserCart userCart = new UserCart();
		BeanUtils.copyProperties(userCartInput, userCart);
		userCart = userCartService.addUserCart(userCart);
		UserCartList userCartList = new UserCartList();
		BeanUtils.copyProperties(userCart, userCartList);
		//set ID
		String idValue = userCartList.getPreviousApiId().getItemId() + IdTypes.Cart+ userCart.getPreviousApiId().getAvailabilityId();
		userCartList.setId(Base64.getUrlEncoder().encodeToString(idValue.getBytes()));
		
		return userCartList;
	}
	
	public CreateOrderOutput createOrderFromCart(CreateOrderInput input) {
		return userCartService.createOrder(input.getUserId(), input.getAddressId(), input.getCoupenCode());
	}
}
