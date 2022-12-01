package com.example.testpfsentities.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class OwnerDto extends UserDto {
    private Boolean active;
    @NotNull
    private String num_cin;
    @NotBlank
    private String num_certif_prop;
}