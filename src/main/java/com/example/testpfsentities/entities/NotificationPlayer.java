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
    @ManyToOne(cascade = CascadeType.ALL)
    private Player player;

    @OneToOne
    private Match match;

}
