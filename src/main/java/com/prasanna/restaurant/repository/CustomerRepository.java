package com.prasanna.restaurant.repository;

import com.prasanna.restaurant.documents.Customer;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    Optional<Customer> findByMobileNo(long mobileNumber);
}
