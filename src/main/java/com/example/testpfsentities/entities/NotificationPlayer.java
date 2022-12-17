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

    @OneToOne
    private Match match;

}
