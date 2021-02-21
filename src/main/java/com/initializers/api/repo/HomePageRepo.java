package com.initializers.api.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.initializers.api.model.HomePage;

public interface HomePageRepo extends MongoRepository<HomePage, Long>{
	Page<HomePage> findAll(Pageable pageable);
}
