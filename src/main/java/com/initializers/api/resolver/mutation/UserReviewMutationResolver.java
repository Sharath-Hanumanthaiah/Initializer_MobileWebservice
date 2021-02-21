package com.initializers.api.resolver.mutation;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.initializers.api.exception.ItemNotFoundException;
import com.initializers.api.exception.UserNotFoundException;
import com.initializers.api.model.UserReview;
import com.initializers.api.repo.UserReviewRepo;
import com.initializers.api.service.ItemDetailsService;
import com.initializers.api.service.UserDetailsService;

@Component
public class UserReviewMutationResolver implements GraphQLMutationResolver{
	
	@Autowired
	UserReviewRepo userReviewRepo;
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	ItemDetailsService itemDetailsService;
	
	public UserReview addItemReview(Long itemId, Long userId, int rating, String review) {
		 if(userDetailsService.getUser(userId) == null) {
				throw new UserNotFoundException("User not found");
			} else if(!itemDetailsService.checkItemDetails(itemId)) {
				throw new ItemNotFoundException("Item not found");
			} else {
				UserReview userReview = new UserReview();
				UserReview.CompositeKey key = new UserReview.CompositeKey();
				key.setItemId(itemId);
				key.setUserId(userId);
				userReview.setPreviousApiId(key);
				userReview.setRating(rating);
				userReview.setReview(review);
				userReview.setChangedAt(LocalDate.now());
				return userReviewRepo.save(userReview);				
			}
	}

}
