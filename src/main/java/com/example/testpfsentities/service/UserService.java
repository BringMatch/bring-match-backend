package com.example.testpfsentities.service;

import com.example.testpfsentities.dto.OwnerDto;
import com.example.testpfsentities.dto.PlayerDto;
import com.example.testpfsentities.dto.UserDto;
import com.example.testpfsentities.entities.Player;
import com.example.testpfsentities.entities.User;
import com.example.testpfsentities.exceptions.BusinessException;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public interface UserService {
    void create(PlayerDto playerDto) throws BusinessException;

    void create(OwnerDto ownerDto) throws BusinessException;

    void disableUser(String id);
    void enableUser(String id);

    void saveUserInProviderWithPermanentPassword(User userBo);

    List<UserRepresentation> getUser(String userName);

    void updateUser(String userId, UserDto userDTO);

    void deleteUser(String userId);

    void resetPassword(String password);


}
