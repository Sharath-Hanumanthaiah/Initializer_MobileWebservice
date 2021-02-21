package com.initializers.api.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.initializers.api.model.UserCartList;

public interface UserCartCustomRepo {
	Page<UserCartList> getUserCart(Long userId, Pageable pageable);
}
