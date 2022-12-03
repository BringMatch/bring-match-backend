package com.example.testpfsentities.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamDto {
    private String id;
    private String name;
    private List<String> players;
    private String match_id;
    private String positionPlayer;
}
