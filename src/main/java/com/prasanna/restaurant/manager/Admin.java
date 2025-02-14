package com.prasanna.restaurant.manager;

import com.prasanna.restaurant.documents.Course;
import com.prasanna.restaurant.documents.Cuisine;
import com.prasanna.restaurant.documents.Item;
import java.util.List;

public interface Admin {
    List<Cuisine> getCuisines();

    Course getCourse(String courseName);

    Item getItem(String itemName);

    List<Item> getItems(List<String> items);

    String saveCuisine(String cuisineName);

    String saveCourse(String courseName, String cuisineName);

    String saveItem(String courseName, String cuisineName, Item item);
}
