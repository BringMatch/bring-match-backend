package com.example.testpfsentities.repository;

import com.example.testpfsentities.entities.NotificationOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationOwnerRepository extends JpaRepository<NotificationOwner , String> {
}
