package com.prasanna.restaurant.controller;

import com.prasanna.restaurant.documents.Customer;
import com.prasanna.restaurant.documents.Order;
import com.prasanna.restaurant.service.AttenderService;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AttenderController {

    @Autowired AttenderService attenderService;

    @GetMapping("/get/order")
    public List<Order> retrieveAllOrdersByCustomerMobileNo(
        @RequestParam("mobile_number") String mobileNumber,
        @RequestParam(name = "history", required = false, defaultValue = "current") String history) {
        return attenderService.retrieveAllOrderByMobileNumber(mobileNumber, history);
    }

    @PostMapping("/save/customer")
    public String saveCustomer(@RequestBody Customer customer) {
        return attenderService.saveCustomer(customer);
    }

    @PostMapping("/make/order/{customer_mobile_number}/{customer_name}")
    public String  makeOrder(@PathVariable("customer_mobile_number") String customer_mobile_number,
        @PathVariable("customer_name") String customerName,
        @RequestBody List<String> items) {
        return attenderService.makeOrder(customer_mobile_number, items, customerName);
    }

    @PostMapping("/trigger/handler")
    public String  triggerHandler(@RequestBody Map<String, Object> inputPayload) {
        return attenderService.handlerOrder(inputPayload);
    }

}
