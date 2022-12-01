package com.example.testpfsentities.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PROTECTED)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties( allowSetters = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Notification extends AbstractEntity{
    private String message;
    private Boolean delivered;
    private Boolean read;
}
