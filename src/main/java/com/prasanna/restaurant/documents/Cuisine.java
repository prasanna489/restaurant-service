package com.prasanna.restaurant.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document( collection = "cuisines")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cuisine {
    @Id
    private String id;
    private String name;
}
