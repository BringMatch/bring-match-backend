package com.example.testpfsentities.repository;

import com.example.testpfsentities.entities.NotificationAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationAdminRepository extends JpaRepository<NotificationAdmin, String> {
    @Query("UPDATE NotificationAdmin notifAdmin SET notifAdmin.read=true WHERE notifAdmin.id=:notif_id")
    void updateStatusNotification(String notif_id);
}
