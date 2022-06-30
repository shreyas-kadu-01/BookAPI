package com.local.bookapi.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class BookTopicConfig {

    @Bean
    public NewTopic BooksTopic(){
        return TopicBuilder.name("books").build();
    }

}
