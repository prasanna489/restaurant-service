package com.prasanna.restaurant.manager.impl;

import com.prasanna.restaurant.documents.Customer;
import com.prasanna.restaurant.documents.Item;
import com.prasanna.restaurant.documents.Order;
import com.prasanna.restaurant.lambda.Handler;
import com.prasanna.restaurant.manager.Attender;
import com.prasanna.restaurant.repository.CustomerRepository;
import com.prasanna.restaurant.repository.OrderRepository;
import com.prasanna.restaurant.util.Helper;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AttenderImpl implements Attender {

    @Autowired Handler handler;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Order> retrieveOrderByMobileNumber(String mobileNumber, String history) {
        Query query = new Query();
        query.addCriteria(Criteria.where("customer.mobileNo").in(Long.valueOf(mobileNumber)));
        return mongoTemplate.find(query, Order.class);
    }

    @Override
    public String saveOrder(List<String> items, String customerMobileNo, String customerName) {
        Customer existingCustomer = customerRepository.
            findByMobileNo(Long.parseLong(customerMobileNo)).orElse(null);
        if (Objects.isNull(existingCustomer)) {
            Customer customer = new Customer();
            customer.setName(customerName);
            customer.setCustomerId(UUID.randomUUID().toString().split("-")[0]);
            customer.setMobileNo(Long.parseLong(customerMobileNo));
            saveCustomer(customer);
        }

        Order order = new Order();
        List<Item> itemsFromRepo = findAllItem(items);
        order.setItems(itemsFromRepo);
        order.setOrderDate(new Date());

        Integer totalAmount = Helper.calculateTotalAmountForOrder(order);
        order.setTotal_amount(totalAmount);

        Customer customer = customerRepository.
            findByMobileNo(Long.parseLong(customerMobileNo)).orElse(null);
        order.setCustomer(customer);
        orderRepository.save(order);
        return "Order Saved!!!";
    }

    @Override
    public String saveCustomer(Customer customer) {
        String existingCustomerId = getCustomerId(String.valueOf(customer.getMobileNo()));
        if (!StringUtils.hasText(existingCustomerId)) {
            customerRepository.save(customer);
            return "Customer Saved!!!";
        }
        return "Customer Already Exists !!!";
    }

    @Override
    public String getCustomerId(String mobileNumber) {
         Customer customer = customerRepository.findByMobileNo(Long.parseLong(mobileNumber)).orElse(null);
         if (Objects.nonNull(customer)) {
             return customer.getCustomerId();
         }
         return "";
    }

    @Override
    public void triggerHandler(Map<String, Object> inputPayload) {
        handler.triggerLambda(inputPayload);
    }

    private List<Item> findAllItem(List<String> itemNames) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").in(itemNames));
        return mongoTemplate.find(query, Item.class);
    }
}
