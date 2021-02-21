package com.initializers.api.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.initializers.api.constant.Status;
import com.initializers.api.model.Address;


public interface AddressRepo extends MongoRepository<Address, Long>{
	
	@Query(value = "{'userId' : ?0, 'status' : '"+ Status.Active +"'}")
	Page<Address> findByUserId(Long userId, Pageable pageable);
	
	Address findFirstByPreviousApiId(Long id);
	
	@Query(value = "{'id' : ?0, 'userId' : ?0}", fields = "{'name' : ?0}")
	Address findByIdUserID(Long id, Long userId);
	 
//	Address deleteByPreviousApiId(Long id);
}
