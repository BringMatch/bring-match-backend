package com.example.testpfsentities.repository;

import com.example.testpfsentities.entities.NotificationPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationPlayerRepository extends JpaRepository<NotificationPlayer, String> {
}
