package com.initializers.api.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.initializers.api.model.OfferConfigurationDB;

public interface OfferConfigurationDBRepo extends MongoRepository<OfferConfigurationDB, Long>{
	@Query(value = "{'type' : ?0, 'flag' : 'true'}")
	List<OfferConfigurationDB> findByType(String type);
}