package com.example.testpfsentities.dto;

import com.example.testpfsentities.entities.enums.PlayerPosition;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamPlayerDto {
    String id;

    @Pattern(regexp = "GARDIEN|DEFENSEUR|MILIEU|ATTAQUANT", message = "you must respect these types  : OWNER , ADMIN , PLAYER")
    @Enumerated(EnumType.STRING)
    PlayerPosition position;
    PlayerDto player;
}
