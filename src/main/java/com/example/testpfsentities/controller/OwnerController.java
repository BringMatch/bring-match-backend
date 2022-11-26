package com.example.testpfsentities.controller;

import com.example.testpfsentities.dto.OwnerDto;
import com.example.testpfsentities.service.OwnerService;
import com.example.testpfsentities.utils.consts.ApiPaths;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Data
@RequiredArgsConstructor
@Slf4j

@RequestMapping(ApiPaths.OWNERS)
@RestController
public class OwnerController {
    private final OwnerService ownerService;

    @PostMapping(ApiPaths.SAVE_OWNER)
    public void save(@RequestBody OwnerDto ownerDto) {
        ownerService.save(ownerDto);
    }

    @GetMapping("hello")
    public String getString() {
        return "Hello";
    }
}
