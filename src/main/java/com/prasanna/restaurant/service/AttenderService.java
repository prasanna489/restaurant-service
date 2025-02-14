package com.prasanna.restaurant.service;

import com.prasanna.restaurant.documents.Customer;
import com.prasanna.restaurant.documents.Order;
import com.prasanna.restaurant.manager.Attender;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttenderService {
    @Autowired Attender attender;

    public List<Order> retrieveAllOrderByMobileNumber(String mobileNumber, String history) {
        return attender.retrieveOrderByMobileNumber(mobileNumber, history);
    }

    public String makeOrder(String customerMobileNumber, List<String> items, String customerName) {
        return attender.saveOrder(items, customerMobileNumber, customerName);
    }

    public String saveCustomer (Customer customer) {
       return attender.saveCustomer(customer);
    }

    public String handlerOrder(Map<String, Object> inputPayload) {
        attender.triggerHandler(inputPayload);
        return "Success";
    }
}
