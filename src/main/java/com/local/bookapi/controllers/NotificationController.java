package com.local.bookapi.controllers;

import com.local.bookapi.entities.Notification;
import com.local.bookapi.kafka.BookKafkaProducer;
import com.local.bookapi.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NotificationController{

    @Autowired
    private NotificationService notificationService;

    private BookKafkaProducer bookKafkaProducer;

    public NotificationController(BookKafkaProducer bookKafkaProducer) {
        this.bookKafkaProducer = bookKafkaProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody Notification notification){
        bookKafkaProducer.sendMessage(notification);
        return ResponseEntity.ok("Json Message sent to topic");
    }

    @GetMapping("/notifications")
    public List<Notification> getAllNotifications(){
        return notificationService.getAllNotifications();
    }
}
