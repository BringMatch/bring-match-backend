package com.example.testpfsentities.repository;

import com.example.testpfsentities.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long> {

}
