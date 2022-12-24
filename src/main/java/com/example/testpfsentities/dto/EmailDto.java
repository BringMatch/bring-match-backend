package com.example.testpfsentities.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class EmailDto {
    String to;
    String from;
    String subject;
    String content;
    boolean hasHtml;
}
