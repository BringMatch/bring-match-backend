package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.config.AdminManagementBuilder;
import com.example.testpfsentities.dto.OwnerDto;
import com.example.testpfsentities.dto.PlayerDto;
import com.example.testpfsentities.entities.User;
import com.example.testpfsentities.exceptions.BusinessException;
import com.example.testpfsentities.repository.UserRepository;
import com.example.testpfsentities.service.UserService;
import com.example.testpfsentities.utils.PasswordUtils;
import com.example.testpfsentities.utils.consts.ErrorCodes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Slf4j

public class UserServiceImpl implements UserService {
    private final AdminManagementBuilder admin;
    private final UserRepository userRepository;

    @Override
    public void create(PlayerDto playerDto) throws BusinessException {

    }

    @Override
    public void create(OwnerDto ownerDto) throws BusinessException {

    }

    @Override
    public void disableUser(String id) {
        Optional<User> userBo = userRepository.findById(id);
        if (userBo.isEmpty()) throw new BusinessException(ErrorCodes.USER_NOT_FOUND);
        userBo.get().setActive(false);
        userRepository.save(userBo.get());
        desactivateUserInProvider(userBo.get());
    }
    private UserRepresentation findByEmailInProvider(String email) {
        return admin.getUsersResource().search(email, true).get(0);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        String userId = findByEmailInProvider(email).getId();
        UserResource user = admin.getUsersResource()
                .get(userId);
        List<RoleRepresentation> roleToAdd = new LinkedList<>();
        roleToAdd.add(admin.getRealmResource()
                .roles()
                .get(roleName)
                .toRepresentation());

        user.roles().realmLevel().add(roleToAdd);
        log.info("role {} added to user", roleName);
    }

    @Override
    public void saveUserInProviderWithPermanentPassword(User userBo) {
        CredentialRepresentation credential = PasswordUtils
                .createPermanentPasswordCredentials(userBo.getPassword());
        saveUserInProvider(userBo, credential);
    }
    private void desactivateUserInProvider(User userBo) {
        String userId = findByEmailInProvider(userBo.getEmail()).getId();
        log.info("user desactivated in provider");
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(false);
        admin.getUsersResource().get(userId).update(user);
    }
    private void saveUserInProvider(User userBo, CredentialRepresentation credential) {
        UserRepresentation user = new UserRepresentation();
        user.setEmail(userBo.getEmail());
        user.setFirstName(userBo.getFirstName());
        user.setLastName(userBo.getLastName());
        user.setCredentials(Collections.singletonList(credential));
        user.setEnabled(true);
        admin.getUsersResource().create(user);
       // userBo.getRoles().stream().map(Role::name).forEach(roleName -> addRoleToUser(userBo.getEmail(), roleName));
        log.info("user saved in provider");
    }

}
