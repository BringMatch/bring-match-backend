package com.example.testpfsentities.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class NotificationPlayer extends Notification {


    private Long playerId;

    private Long matchId;

}
