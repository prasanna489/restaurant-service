package com.prasanna.restaurant.documents;

import com.prasanna.restaurant.documents.Enum.PaymentType;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document( collection = "order")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    private String orderId;
    private Customer customer;

    @EqualsAndHashCode.Exclude
    @CreatedDate
    private Date orderDate;
    private List<Item> items;
    private Integer total_amount;
    private PaymentType paymentType;
    private Transaction transaction;

}
