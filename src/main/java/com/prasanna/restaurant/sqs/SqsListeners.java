package com.prasanna.restaurant.sqs;

import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.model.Message;

@Service
public class SqsListeners {
    @Autowired
    SqsTemplate sqsTemplate;

    @SqsListener("Restaurant_Action_Handler_Response_Queue")
    public void handlerListener(Message message) {
        System.out.println("Result of handler action " + message.body());
    }
}
