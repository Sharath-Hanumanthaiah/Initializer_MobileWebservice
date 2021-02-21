package com.initializers.api.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.initializers.api.model.UserDetails;

public interface UserDetailsRepo extends MongoRepository<UserDetails, Long>{

	@Query(value = "{'previousApiId' : ?0, 'status' : 'Active'}")
	UserDetails findFirstByPreviousApiId(Long userId);
	
	@Query(value = "{'previousApiId' : ?0, 'status' : 'Active'}", fields = "{'firstName' : ?0, 'lastName' : ?0}")
	UserDetails findNameByPreviousApiId(Long userId);
	
	UserDetails findFirstIdByEmail(String email);
	 
	List<UserDetails> findByEmail(String email);
	
}
