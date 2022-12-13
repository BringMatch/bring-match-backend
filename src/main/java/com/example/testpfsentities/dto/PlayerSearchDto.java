package com.example.testpfsentities.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlayerSearchDto {
    String firstName;
    String lastName;
    String town;
    String region;
}
