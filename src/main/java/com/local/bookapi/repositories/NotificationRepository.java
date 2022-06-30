package com.local.bookapi.repositories;

import com.local.bookapi.entities.Notification;
import org.springframework.data.repository.CrudRepository;

public interface NotificationRepository extends CrudRepository<Notification, Integer> {

}
