package com.initializers.api.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.initializers.api.constant.Status;
import com.initializers.api.model.ItemAvailability;

public interface ItemAvailabilityRepo extends MongoRepository<ItemAvailability, Long>{
	
	@Query(value = "{'id' : ?0, 'available' : '"+Status.Active+"'}")
	ItemAvailability findFisrtById(Long id);
	
	@Query(value = "{'id' : ?0 }")
	ItemAvailability findFirstId(Long id);
	
	@Query(value = "{'id' : ?0, 'available' : '"+Status.Active+"'}", fields = "{'discountPrice' : ?0}")
	ItemAvailability findFirstDiscountPriceById(Long id);
	
	@Query(value = "{'id' : ?0}", fields = "{'value' : ?0, 'unit' : ?0}")
	ItemAvailability findFirstValueUnitId(Long id);
	
	@Query(value = "{'itemId' : ?0, 'available' : '"+Status.Active+"'}")
	List<ItemAvailability> findByItem(Long itemId);
	
	List<ItemAvailability> findByItemId(Long itemId);
	
	ItemAvailability findTopByItemIdOrderByDiscountDesc(Long itemId);
}
