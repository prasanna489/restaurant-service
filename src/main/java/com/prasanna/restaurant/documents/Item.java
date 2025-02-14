package com.prasanna.restaurant.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document( collection = "items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    private String name;
    private int price;
    private String description;
    private boolean availability;
    private Course course;
}
