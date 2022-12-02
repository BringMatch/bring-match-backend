package com.example.testpfsentities.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatchDto {
    private String id;
    private String creator_id;
    private String date;
    private String teamName;
    private String positionPlayer;
    private String hour;
    private Boolean privateMatch;
    private String matchType;
    private String groundName;
}
