package com.local.bookapi.services;

import com.local.bookapi.entities.Notification;
import com.local.bookapi.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getAllNotifications(){
        return (List<Notification>) notificationRepository.findAll();
    }

    public Notification addNotification(Notification notification){
        return notificationRepository.save(notification);
    }
}
