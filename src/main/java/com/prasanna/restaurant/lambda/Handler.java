package com.prasanna.restaurant.lambda;

import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.model.InvocationType;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.prasanna.restaurant.util.JsonUtil;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Handler {
    @Autowired
    AWSLambda awsLambda;

    @Value("${handler.lambda.function-name}")
    private String functionName;

    public void triggerLambda(Map<String, Object> inputPayload) {
        InvokeRequest invokeRequest = new InvokeRequest()
            .withFunctionName(functionName)
            .withInvocationType(InvocationType.Event)
            .withPayload(JsonUtil.convertToJsonString(inputPayload));

        InvokeResult invokeResult = awsLambda.invoke(invokeRequest);
        assert invokeResult.getStatusCode() == 202;
    }
}
