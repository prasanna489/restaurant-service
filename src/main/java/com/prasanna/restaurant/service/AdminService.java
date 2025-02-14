package com.prasanna.restaurant.service;

import com.prasanna.restaurant.documents.Course;
import com.prasanna.restaurant.documents.Cuisine;
import com.prasanna.restaurant.documents.Item;
import com.prasanna.restaurant.manager.Admin;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired Admin admin;

    public List<Cuisine> getCuisines() {
        return admin.getCuisines();
    }

    public Course getCourse(String courseName) {
        return admin.getCourse(courseName);
    }

    public Item getItem(String itemName) {
        return admin.getItem(itemName);
    }

    public List<Item> getItems(List<String> items) {
        return admin.getItems(items);
    }

    public String saveCuisine(String cuisineName) {
       return admin.saveCuisine(cuisineName);
    }

    public String saveCourse(String courseName, String cuisineName) {
        return admin.saveCourse(courseName, cuisineName);
    }

    public String saveItem(String courseName, String cuisineName, Item item) {
        return admin.saveItem(courseName, cuisineName, item);
    }
}
