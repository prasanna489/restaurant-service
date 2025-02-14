package com.prasanna.restaurant.controller;

import com.prasanna.restaurant.documents.Course;
import com.prasanna.restaurant.documents.Cuisine;
import com.prasanna.restaurant.documents.Item;
import com.prasanna.restaurant.service.AdminService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired AdminService adminService;

    @GetMapping("/get/cuisines")
    public List<Cuisine> getAllCuisine() {
        return adminService.getCuisines();
    }

    @GetMapping("/get/course/{course_name}")
    public Course getCourse(@PathVariable("course_name") String courseName) {
        return adminService.getCourse(courseName);
    }

    @GetMapping("/get/item/{item_name}")
    public Item getItem(@PathVariable("item_name") String itemName) {
        return adminService.getItem(itemName);
    }

    @GetMapping("/get/items")
    public List<Item> getItems(@RequestBody List<String> items) {
        return adminService.getItems(items);
    }

    @PostMapping("/save/cuisine/{cuisine_name}")
    public String saveCuisine(@PathVariable("cuisine_name") String cuisine_name) {
        return adminService.saveCuisine(cuisine_name);
    }

    @PostMapping("/save/course/{cuisine_name}/{course_name}")
    public String saveCourse(@PathVariable("cuisine_name") String cuisine_name,
        @PathVariable("course_name") String course_name) {
        return adminService.saveCourse(course_name, cuisine_name);
    }

    @PostMapping("/save/item/{cuisine_name}/{course_name}")
    public String saveItem(@PathVariable("cuisine_name") String cuisine_name,
        @PathVariable("course_name") String course_name,
        @RequestBody Item item) {
        return adminService.saveItem(course_name, cuisine_name, item);
    }
}
