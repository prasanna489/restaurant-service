package com.prasanna.restaurant.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LambdaConfig {
    @Value("${spring.cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${spring.cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${spring.cloud.aws.region.static}")
    private String region;

    @Bean
    public AWSLambda lambdaClient() {
        return AWSLambdaClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(getCredentials()))
            .withRegion(Regions.AP_SOUTH_1).build();
    }

    private AWSCredentials getCredentials() {
        return new BasicAWSCredentials(accessKey, secretKey);
    }
}
