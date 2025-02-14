package com.prasanna.restaurant.util;

import com.prasanna.restaurant.documents.Item;
import com.prasanna.restaurant.documents.Order;
import java.util.List;
import java.util.stream.Collectors;

public class Helper {

    static public Integer calculateTotalAmountForOrders(List<Order> orders) {
        List<Item> items = orders.stream().flatMap(order -> order.getItems().stream()).collect(
            Collectors.toList());
        return items.stream().mapToInt(Item::getPrice).sum();
    }

    static public Integer calculateTotalAmountForOrder(Order order) {
        return order.getItems().stream().mapToInt(Item::getPrice).sum();
    }

}
