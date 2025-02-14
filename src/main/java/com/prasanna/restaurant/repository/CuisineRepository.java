package com.prasanna.restaurant.repository;

import com.prasanna.restaurant.documents.Cuisine;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CuisineRepository extends MongoRepository<Cuisine, String> {
    Optional<Cuisine> findByName(String cuisineName);
}
