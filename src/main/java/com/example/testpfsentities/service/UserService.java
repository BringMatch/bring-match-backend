package com.example.testpfsentities.service;

import com.example.testpfsentities.dto.OwnerDto;
import com.example.testpfsentities.dto.PlayerDto;
import com.example.testpfsentities.entities.User;
import com.example.testpfsentities.exceptions.BusinessException;

public interface UserService {
    void create(PlayerDto playerDto) throws BusinessException;

    void create(OwnerDto ownerDto) throws BusinessException;

    void disableUser(String id);

    void addRoleToUser(String email, String role);
    public void saveUserInProviderWithPermanentPassword(User userBo);
}
