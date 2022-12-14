package com.example.testpfsentities.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PROTECTED)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"userFrom,userTo"},allowSetters = true)
public class NotificationAdmin extends Notification {
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin userTo;

    @OneToOne
    private Owner userFrom;

}
