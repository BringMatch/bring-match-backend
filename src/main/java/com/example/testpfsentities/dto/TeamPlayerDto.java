package com.example.testpfsentities.dto;

import com.example.testpfsentities.entities.composite.TeamPlayerKey;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamPlayerDto {
    String id;
    PlayerDto player;
}
