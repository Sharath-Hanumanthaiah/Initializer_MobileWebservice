package com.initializers.api.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.initializers.api.model.ItemSubCategory;

public interface ItemSubCategoryRepo extends MongoRepository<ItemSubCategory, Long>{

	
	ItemSubCategory findFirstByPreviousApiId(Long id);
	
	Page<ItemSubCategory> findByCategoryId(Long categoryId, Pageable pageable);
	
	List<ItemSubCategory> findByCategoryId(Long categoryId);
	
	ItemSubCategory findTopByCategoryIdOrderByOfferDesc(Long categoryId);
	
	@Query(value = "{'categoryId' : ?0}", fields = "{'previousApiId' : ?0}")
	List<ItemSubCategory> checkByCategoryId(Long categoryId);
	
	@Query(value = "{'previousApiId' : ?0}", fields = "{ 'name' : ?0 }")
	ItemSubCategory findFirstNameById(Long previousApiId);
}
