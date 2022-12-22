package com.example.testpfsentities.controller.config;

import com.example.testpfsentities.config.AdminManagementBuilder;
import com.example.testpfsentities.dto.PlayerDto;
import com.example.testpfsentities.dto.UserDto;
import com.example.testpfsentities.service.UserService;
import com.example.testpfsentities.utils.consts.ApiPaths;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.KEYCLOAK)
@RequiredArgsConstructor
@Slf4j
public class KeycloakController {
    private final UserService userService;
    private final AdminManagementBuilder adminManagementBuilder;

    @PostMapping("/create-player")
    public void createPlayer(@RequestBody @Validated PlayerDto playerDto) {
        userService.create(playerDto);
    }

    /**
     * this will return the users inside our client !
     */
    @GetMapping("/users-resources")
    public List<UserRepresentation> getUsersResources() {
        return adminManagementBuilder.getUsersResource().list();
    }

    /**
     * this will return the clients inside our realm  !
     */
    @GetMapping("/realm-clients")
    public List<ClientRepresentation> getRealmClients() {
        return adminManagementBuilder.getClientResource().findAll();
    }

    @GetMapping(path = "/{userName}")
    public List<UserRepresentation> getUser(@PathVariable("userName") String userName){
        return userService.getUser(userName);
    }


    @PutMapping(path = "/update/{userId}")
    public String updateUser(@PathVariable("userId") String userId,   @RequestBody UserDto userDTO){
        userService.updateUser(userId, userDTO);
        return "User Details Updated Successfully.";
    }

    @DeleteMapping(path = "/delete/{userId}")
    public String deleteUser(@PathVariable("userId") String userId){
        userService.deleteUser(userId);
        return "User Deleted Successfully.";
    }

    @PutMapping(path = "/enable-user/{userId}")
    public void enableUserStatus(@PathVariable("userId")String userId){
        userService.enableUser(userId);
    }

    @PutMapping(path = "/disable-user/{userId}")
    public void disableUserStatus(@PathVariable("userId")String userId){
        userService.disableUser(userId);
    }

}
