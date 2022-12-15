package com.example.testpfsentities.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationPlayer extends Notification {
    @ManyToOne
    private Player owner_match;
    private String currentMatchId;
    private String team_name;

    @OneToOne
    private Player playerJoined;

}
