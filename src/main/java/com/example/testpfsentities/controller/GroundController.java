package com.example.testpfsentities.controller;

import com.example.testpfsentities.dto.GroundDto;
import com.example.testpfsentities.service.GroundService;
import com.example.testpfsentities.utils.consts.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.GROUNDS)
@RequiredArgsConstructor
public class GroundController {
    private final GroundService groundService;
    @GetMapping(ApiPaths.GET_GROUNDS)
    public ResponseEntity<List<GroundDto>> getGrounds(){
        return ResponseEntity.ok().body(groundService.getGrounds());
    }
}
