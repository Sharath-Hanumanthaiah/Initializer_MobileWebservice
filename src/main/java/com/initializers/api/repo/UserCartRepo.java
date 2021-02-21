package com.initializers.api.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.initializers.api.model.UserCart;

public interface UserCartRepo extends MongoRepository<UserCart, UserCart.CompositeKeyCart>, UserCartCustomRepo{

//	@Query(value = "{'_id' : {'userId' : ?0, 'itemId' : '2', 'availabilityId' : '1'}}")
//	List<UserCart> findByUserId(Long userId);
	
	Page<UserCart> findByPreviousApiIdUserId(Long userId, Pageable pageable);
	
	List<UserCart> findByPreviousApiIdUserId(Long userId);
	void deleteById(UserCart.CompositeKeyCart id);
}
