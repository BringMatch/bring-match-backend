package com.example.testpfsentities.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationPlayer extends Notification {

    @ManyToOne
    @JoinColumn(name = "owner_player_id")
    private Player owner_match;

    private String currentMatchId;


}
