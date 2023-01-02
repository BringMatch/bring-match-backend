package com.example.testpfsentities.dto;

import com.example.testpfsentities.entities.PlayerStats;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto extends UserDto  {
    private String town;
    private String region;
}
