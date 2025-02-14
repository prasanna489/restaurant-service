package com.prasanna.restaurant.manager;

import com.prasanna.restaurant.documents.Customer;
import com.prasanna.restaurant.documents.Order;
import java.util.List;
import java.util.Map;

public interface Attender {
    List<Order> retrieveOrderByMobileNumber(String mobileNumber, String history);
    String saveOrder(List<String> items, String customerMobileNo, String customerName);
    String saveCustomer(Customer customer);
    String getCustomerId(String mobileNumber);
    void triggerHandler(Map<String, Object> inputPayload);
}
