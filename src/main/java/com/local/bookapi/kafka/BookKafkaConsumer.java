package com.local.bookapi.kafka;

import com.local.bookapi.entities.Notification;
import com.local.bookapi.services.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class BookKafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookKafkaConsumer.class);

    @Autowired
    private NotificationService notificationService;

    @KafkaListener(topics = "books", groupId = "myGroup")
    public void consume(Notification notification){
        LOGGER.info(String.format("Message received -> %s", notification.toString()));
        notificationService.addNotification(notification);
    }
}
