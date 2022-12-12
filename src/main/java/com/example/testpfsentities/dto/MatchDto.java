package com.example.testpfsentities.dto;

import com.example.testpfsentities.entities.Owner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String matchType;
    private int startHour;
    private String groundName;
    private int duration;
    private String matchCode;
    private OwnerDto ownerDto;
}
