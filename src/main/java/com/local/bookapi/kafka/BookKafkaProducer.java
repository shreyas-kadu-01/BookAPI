package com.local.bookapi.kafka;

import com.local.bookapi.entities.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


@Service
public class BookKafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookKafkaProducer.class);

    private KafkaTemplate<String, Notification> kafkaTemplate;

    public BookKafkaProducer(KafkaTemplate<String, Notification> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Notification notification){
        LOGGER.info(String.format("Message is %s", notification.toString()));

        Message<Notification> message = MessageBuilder
                .withPayload(notification)
                .setHeader(KafkaHeaders.TOPIC, "books")
                .build();

        kafkaTemplate.send(message);
    }
}
