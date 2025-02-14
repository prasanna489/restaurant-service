package com.prasanna.restaurant.manager.impl;

import com.prasanna.restaurant.documents.Course;
import com.prasanna.restaurant.documents.Cuisine;
import com.prasanna.restaurant.documents.Enum.CourseType;
import com.prasanna.restaurant.documents.Enum.OperationConstants;
import com.prasanna.restaurant.documents.Item;
import com.prasanna.restaurant.manager.Admin;
import com.prasanna.restaurant.repository.CourseRepository;
import com.prasanna.restaurant.repository.CuisineRepository;
import com.prasanna.restaurant.repository.ItemRepository;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class AdminImpl implements Admin {

    @Autowired
    CuisineRepository cuisineRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Cuisine> getCuisines() {
        return cuisineRepository.findAll();
    }

    @Override
    public Course getCourse(String courseName) {
        CourseType courseType = CourseType.fromString(courseName);
        return findCourse(courseType);
    }

    @Override
    public Item getItem(String itemName) {
        return itemRepository.findByName(itemName);
    }

    @Override
    public List<Item> getItems(List<String> items) {
        return itemRepository.findAllById(items);
    }

    @Override
    public String saveCuisine(String cuisineName) {
        Cuisine existingCuisine = findCuisine(cuisineName);
        if (Objects.isNull(existingCuisine)) {
            Cuisine cuisine = new Cuisine();
            cuisine.setName(cuisineName);
            cuisineRepository.save(cuisine);
            return OperationConstants.CUISINE_SAVED_SUCCESS;
        }
       return OperationConstants.CUISINE_EXISTS_ALREADY;
    }

    @Override
    public String saveCourse(String courseName, String cuisineName) {
        CourseType courseType = CourseType.fromString(courseName);
        Course existingCourseWithCuisine = findExistingCourseWithCuisine(courseName, cuisineName);
        if (Objects.isNull(existingCourseWithCuisine)) {
            Course existingCourse = findCourse(courseType);
            if (Objects.nonNull(existingCourse)) {
                List<Cuisine> updatedCuisines = existingCourse.getCuisines();
                Cuisine cuisine = findCuisine(cuisineName);
                if (Objects.nonNull(cuisine)) {
                    updatedCuisines.add(cuisine);
                    existingCourse.setCuisines(updatedCuisines);
                    courseRepository.save(existingCourse);
                    return OperationConstants.COURSE_UPDATION_SUCCESS;
                }
            } else {
                Course course = new Course();
                course.setCourseType(courseType);
                Cuisine cuisine = findCuisine(cuisineName);
                if (Objects.nonNull(cuisine)) {
                    course.setCuisines(List.of(cuisine));
                    courseRepository.save(course);
                    return OperationConstants.COURSE_SAVED_SUCCESS;
                }
                return OperationConstants.CUISINE_NOT_EXISTS;
            }
        }
        return OperationConstants.COURSE_WITH_CUISINE_EXISTS_ALREADY;
    }

    @Override
    public String saveItem(String courseName, String cuisineName, Item item) {
        Cuisine cuisine = findCuisine(cuisineName);
        if (Objects.nonNull(cuisine)) {
            Course existingCourse = findExistingCourseWithCuisine(courseName, cuisineName);
            if (Objects.nonNull(existingCourse)) {
                Item existingItem = findExistingItemWithCourseAndCuisine(courseName, cuisineName, item);
                if (Objects.isNull(existingItem)) {
                    item.setCourse(existingCourse);
                    itemRepository.save(item);
                    return OperationConstants.ITEM_SAVED_SUCCESS;
                } else {
                   return updateExistingItemUnderCourseAndCuisine(courseName, cuisineName, item);
                }
            }
            return OperationConstants.COURSE_NOT_EXISTS;
        }
        return OperationConstants.CUISINE_NOT_EXISTS;
    }

    private Cuisine findCuisine(String cuisineName) {
        return cuisineRepository.findByName(cuisineName).orElse(null);
    }

    private Course findCourse(CourseType courseType) {
        return courseRepository.findByCourseType(courseType).orElse(null);
    }

    private Course findExistingCourseWithCuisine(String courseName, String cuisineName) {
        CourseType courseType = CourseType.fromString(courseName);
        Query query = new Query();
        query.addCriteria(
            Criteria.where("courseType").in(courseType)
                .and("cuisines.name").in(cuisineName));
        List<Course> existingCourses = mongoTemplate.find(query, Course.class);
        if (existingCourses.isEmpty()) {
            return null;
        }
        return existingCourses.get(0);
    }

    private Item findExistingItemWithCourseAndCuisine(String courseName,
        String cuisineName, Item item) {
        CourseType courseType = CourseType.fromString(courseName);
        String itemName = item.getName();
        Query query = new Query();
        query.addCriteria(
            Criteria.where("course.courseType").in(courseType)
                .and("course.cuisines.name").in(cuisineName)
                .and("name").in(itemName));
        List<Item> existingItems = mongoTemplate.find(query, Item.class);
        if (existingItems.isEmpty()) {
            return null;
        }
        return existingItems.get(0);
    }

    private String updateExistingItemUnderCourseAndCuisine(String courseName,
        String cuisineName, Item item) {
        CourseType courseType = CourseType.fromString(courseName);
        String itemName = item.getName();
        Query query = new Query();
        query.addCriteria(
            Criteria.where("course.courseType").in(courseType)
                .and("course.cuisines.name").in(cuisineName)
                .and("name").in(itemName));
        Update update = new Update();
        update.set("price", item.getPrice());
        mongoTemplate.findAndModify(query, update, Item.class);
        return OperationConstants.ITEM_UPDATION_SUCCESS;
    }

}
