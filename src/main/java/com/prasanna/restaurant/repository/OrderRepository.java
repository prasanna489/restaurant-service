package com.prasanna.restaurant.repository;

import com.prasanna.restaurant.documents.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
