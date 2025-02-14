package com.prasanna.restaurant.repository;

import com.prasanna.restaurant.documents.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
}
