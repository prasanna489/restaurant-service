package com.prasanna.restaurant.documents;

import com.prasanna.restaurant.documents.Enum.CourseType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document( collection = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    private CourseType courseType;
    private List<Cuisine> cuisines;
}
