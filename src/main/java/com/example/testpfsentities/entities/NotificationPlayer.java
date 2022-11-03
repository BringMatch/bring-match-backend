package com.example.testpfsentities.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class NotificationPlayer extends Notification {


    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    private Long currentMatchId;



}
