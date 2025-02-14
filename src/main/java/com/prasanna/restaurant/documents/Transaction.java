package com.prasanna.restaurant.documents;

import com.prasanna.restaurant.documents.Enum.PaymentStatus;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document( collection = "transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    private String transactionId;
    private boolean isPaid;
    private PaymentStatus paymentStatus;

    @EqualsAndHashCode.Exclude
    @CreatedDate
    private Date paymentDate;
}
