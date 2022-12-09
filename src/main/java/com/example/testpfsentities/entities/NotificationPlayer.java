package com.example.testpfsentities.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationPlayer extends Notification {


    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    private String currentMatchId;



}
