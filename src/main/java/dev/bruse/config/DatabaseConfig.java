package dev.bruse.config;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    @Bean
    AmazonDynamoDB dynamoDbClient() {
        return AmazonDynamoDBClientBuilder.defaultClient();
    }

    @Bean
    DynamoDBMapper dynamoDBMapper(final AmazonDynamoDB client) {
        return new DynamoDBMapper(client);
    }

}
