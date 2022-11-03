package com.example.testpfsentities.entities;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.Instant;
import java.util.Date;

@MappedSuperclass
public class Notification {

    @Id
    private Long id;

    @CreatedDate
    private Instant createdAt;

    private String message;

}
