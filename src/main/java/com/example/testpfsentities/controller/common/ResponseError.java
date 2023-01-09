package com.example.testpfsentities.controller.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class ResponseError {
    private List<String> errors;
    private Integer status;
}
