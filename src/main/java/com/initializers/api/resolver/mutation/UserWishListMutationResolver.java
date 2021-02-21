package com.initializers.api.resolver.mutation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.initializers.api.model.input.UserWishListInput;
import com.initializers.api.service.UserWishlistService;

@Component
public class UserWishListMutationResolver implements GraphQLMutationResolver{

	@Autowired
	UserWishlistService userWishlistService;

	public Boolean changeWishList(UserWishListInput userWishListInput) {
		if (userWishListInput.getType().equals("add")) {
			userWishlistService.addUserWishlist(userWishListInput.getUserId(), userWishListInput.getItemId());
		}
		if (userWishListInput.getType().equals("remove")) {
			userWishlistService.deleteUserWishlist(userWishListInput.getUserId(), userWishListInput.getItemId());
		}
		return true;
	}
}
