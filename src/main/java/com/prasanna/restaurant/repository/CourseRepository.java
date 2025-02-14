package com.prasanna.restaurant.repository;

import com.prasanna.restaurant.documents.Course;
import com.prasanna.restaurant.documents.Enum.CourseType;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<Course, String> {
    Optional<Course> findByCourseType(CourseType courseType);
}
