package com.initializers.api.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.initializers.api.constant.Status;
import com.initializers.api.model.ItemDetails;

public interface ItemDetailsRepo extends MongoRepository<ItemDetails 	, Long>{
	
	@Query(value = "{'previousApiId' : ?0, 'status' : 'Active'}")
	ItemDetails findFirstById(Long previousApiId);
		
	@Query(value = "{'previousApiId' : ?0}")
	ItemDetails findFirstId(Long previousApiId);
	
	@Query(value = "{'previousApiId' : ?0}", fields = "{'previousApiId' : ?0, 'name' : ?0,"
			+ "'imageLinks' : ?0 }")
	ItemDetails findByIdForAllStatus(Long previousApiId);
	
	@Query(value = "{'previousApiId' : ?0, 'status' : '"+ Status.Active +"'}", fields = "{'name' : ?0 }")
	ItemDetails findFirstNameById(Long previousApiId);
	
	@Query(value = "{'previousApiId' : ?0}", fields = "{'name' : ?0, 'imageLinks' : ?0 }")
	ItemDetails findFirstNameImageLinksById(Long previousApiId);
	
//	@Query(value = "{'categoryId' : ?0}", fields = "{'previousApiId' : ?0, 'name' : ?0,"
//			+ "'availability' : ?0,'imageLinks' : ?0, 'status' : ?0 }")
	Page<ItemDetails> findByCategoryId(Long categoryID, Pageable pageable);
	
	Page<ItemDetails> findBySubCategoryId(Long subCategoryID, Pageable pageable);
	
	List<ItemDetails> findBySubCategoryId(Long subCategoryID);
	
	
	@Query(value = "{'name' : { $regex: ?0, $options:'i' }, 'status' : 'Active'}", fields = "{'previousApiId' : ?0, 'name' : ?0, 'status' : ?0}")
	List<ItemDetails> findCategoryIdByNameRegex(String name);
	
	@Query(value = "{'previousApiId' : ?0, 'status' : 'Active'}", fields = "{'previousApiId' : ?0, 'name' : ?0,"
			+ "'imageLinks' : ?0 }")
	ItemDetails findListByIdByStatus(Long previousApiId, String status);
	
	@Query(value = "{'id' : ?0}", fields = "{'id' : ?0, 'imageLinks' : ?0 }")
	ItemDetails findFirstByIdAndImageLinks();
	
	
}
