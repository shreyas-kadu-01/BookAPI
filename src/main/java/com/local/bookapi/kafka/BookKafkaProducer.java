package com.local.bookapi.kafka;

import com.local.bookapi.entities.Book;
import com.local.bookapi.entities.KafkaMessage;
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

    private KafkaTemplate<String, Object> kafkaTemplate;

    public BookKafkaProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(KafkaMessage notification) {
        LOGGER.info(String.format("Message is %s", notification.toString()));

        Message<KafkaMessage> message = MessageBuilder
                .withPayload(notification)
                .setHeader(KafkaHeaders.TOPIC, "books")
                .build();

        kafkaTemplate.send(message);
    }

}
