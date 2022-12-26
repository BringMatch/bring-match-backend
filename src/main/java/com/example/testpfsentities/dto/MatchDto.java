package com.example.testpfsentities.dto;

import com.example.testpfsentities.entities.enums.MatchResult;
import com.example.testpfsentities.entities.enums.MatchStatus;
import com.example.testpfsentities.entities.enums.MatchType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatchDto {
    private String id;
    private Date date;
    private List<TeamDto> teams;
    private Boolean privateMatch;

    @Enumerated(EnumType.STRING)
    private MatchType matchType;

    private String groundName;
    private int startHour;
    private int duration;
    private String matchCode;
    private int numberTeamPlayers;
    private boolean draw;
    @Enumerated(EnumType.STRING)
    private MatchStatus matchStatus;

    @Enumerated(EnumType.STRING)
    private MatchResult matchResult;
}
