package com.example.testpfsentities.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class OwnerDto extends UserDto {
    private Boolean active;
    @NotNull
    @Size(max = 10, message = "max is 10")
    @Size(min = 8, message = "min is 8")
    private String num_cin;
    @NotBlank
    @NotNull
    @Size(max = 10, message = "max is 10")
    @Size(min = 8, message = "min is 8")
    private String num_certif_prop;
}