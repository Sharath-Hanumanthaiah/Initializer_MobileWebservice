package com.initializers.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.initializers.api.model.UserReview;
import com.initializers.api.repo.UserReviewRepo;
import com.initializers.api.service.ItemDetailsService;
import com.initializers.api.service.UserDetailsService;
import com.initializers.api.service.UserReviewService;

@Service
public class UserReviewServiceImpl implements UserReviewService {

	@Autowired
	private UserReviewRepo userReviewRepo;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private ItemDetailsService itemDetailsService;
	
	@Override
	public Page<UserReview> findReviewByItemId(Long itemId, Pageable pageable) {
		// TODO item exist validation
		return userReviewRepo.findByPreviousApiIdItemId(itemId, pageable);
	}

//	@Override
//	public UserReview addUserReview(UserReviewTemp userReviewTemp) {
//		if(userReviewTemp.getUserId() == null || userReviewTemp.getItemId() == null) {
//			throw new RequiredValueMissingException();
//		} else if(userDetailsService.getUser(userReviewTemp.getUserId()) == null) {
//			throw new UserNotFoundException();
//		} else if(itemDetailsService.getItemDetails(userReviewTemp.getItemId()) == null) {
//			throw new ItemNotFoundException();
//		}else {
//			UserReview userReview = new UserReview();
//			userReview.setId(new UserReview.CompositeKey());
//			userReview.getId().setItemId(userReviewTemp.getItemId());
//			userReview.getId().setUserId(userReviewTemp.getUserId());
//			userReview.setRating(userReviewTemp.getRating());
//			userReview.setReview(userReviewTemp.getReview());
//			return userReviewRepo.save(userReview);
//		}
//	}
}
