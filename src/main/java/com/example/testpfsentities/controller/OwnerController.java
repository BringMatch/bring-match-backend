package com.example.testpfsentities.controller;

import com.example.testpfsentities.dto.OwnerDto;
import com.example.testpfsentities.service.OwnerService;
import com.example.testpfsentities.service.UserService;
import com.example.testpfsentities.utils.consts.ApiPaths;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RequiredArgsConstructor
@Slf4j
@RequestMapping(ApiPaths.OWNERS)
@RestController
@CrossOrigin("http://localhost:8081/")
public class OwnerController {
    private final OwnerService ownerService;
    private final UserService userService;

    @PostMapping(ApiPaths.SAVE_OWNER)
    public void save(@RequestBody @Validated OwnerDto ownerDto) {
        ownerService.save(ownerDto);
    }

    @GetMapping(ApiPaths.GET_OWNERS)
    public ResponseEntity<List<OwnerDto>> getOwners() {
        return ResponseEntity.ok().body(ownerService.getOwners());
    }

    @GetMapping(ApiPaths.OWNER_CONNECTED)
    public ResponseEntity<OwnerDto> getOwnerConnected(){
        return ResponseEntity.ok().body(userService.getOwnerDtoConnected());
    }

    @DeleteMapping(ApiPaths.DELETE_OWNER + "/{owner_id}")
    public void deleteOwner(@PathVariable(name = "owner_id") String owner_id){
        ownerService.delete(owner_id);
    }

}
