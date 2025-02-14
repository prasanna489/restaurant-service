package com.prasanna.restaurant.repository;

import com.prasanna.restaurant.documents.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<Item, String> {
    Item findByName(String itemName);
}
