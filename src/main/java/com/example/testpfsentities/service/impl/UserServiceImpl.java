package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.config.AdminManagementBuilder;
import com.example.testpfsentities.dto.OwnerDto;
import com.example.testpfsentities.dto.PlayerDto;
import com.example.testpfsentities.dto.UserDto;
import com.example.testpfsentities.entities.Player;
import com.example.testpfsentities.entities.User;
import com.example.testpfsentities.exceptions.BusinessException;
import com.example.testpfsentities.mapper.PlayerMapper;
import com.example.testpfsentities.repository.PlayerRepository;
import com.example.testpfsentities.repository.UserRepository;
import com.example.testpfsentities.service.UserService;
import com.example.testpfsentities.utils.PasswordUtils;
import com.example.testpfsentities.utils.SecurityUtils;
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
    private final PlayerMapper playerMapper;
    private final PlayerRepository playerRepository;

    @Override
    public void create(PlayerDto playerDto) throws BusinessException {
        Player user = playerMapper.toBo(playerDto);
        saveUserInProviderWithPermanentPassword(user);
        playerRepository.save(user);
    }

    @Override
    public void create(OwnerDto ownerDto) throws BusinessException {

    }

    @Override
    public void disableUser(String id) {
//        Optional<User> userBo = userRepository.findById(id);
//        if (userBo.isEmpty()) throw new BusinessException(ErrorCodes.USER_NOT_FOUND);
//        userBo.get().setActive(false);
        //userRepository.save(userBo.get());
        desactivateUserInProvider(id);
    }

    @Override
    public void enableUser(String id) {
//        Optional<User> userBo = userRepository.findById(id);
//        if (userBo.isEmpty()) throw new BusinessException(ErrorCodes.USER_NOT_FOUND);
//        userBo.get().setActive(true);
//        userRepository.save(userBo.get());
        activateUserInProvider(id);

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


    private void saveUserInProvider(User userBo, CredentialRepresentation credential) {
        UserRepresentation user = new UserRepresentation();
        user.setEmail(userBo.getEmail());
        user.setFirstName(userBo.getFirstName());
        user.setLastName(userBo.getLastName());
        user.setCredentials(Collections.singletonList(credential));
        user.setEnabled(true);
        admin.getUsersResource().list().forEach((aa) -> log.info(aa.getId()));
        admin.getUsersResource().list().add(user);
        admin.getUsersResource().create(user);
        // userBo.getRoles().stream().map(Role::name).forEach(roleName -> addRoleToUser(userBo.getEmail(), roleName));
        log.info("user saved in provider");
    }

    public List<UserRepresentation> getUser(String userName) {
        return admin.getUsersResource().search(userName, true);
    }

    public void updateUser(String userId, UserDto userDTO) {
        CredentialRepresentation credential = PasswordUtils
                .createPermanentPasswordCredentials(userDTO.getPassword());
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setCredentials(Collections.singletonList(credential));
        admin.getUsersResource().get(userId).update(user);
    }

    @Override
    public void deleteUser(String userId) {
        admin.getUsersResource().get(userId)
                .remove();
    }

    @Override
    public void resetPassword(String password) {
        Optional<String> email = SecurityUtils.getUserEmail();
        if (email.isPresent()) {

            CredentialRepresentation credential = PasswordUtils
                    .createPermanentPasswordCredentials(password);
            setUserPassword(credential, email.get());

        }

    }

    private void setUserPassword(CredentialRepresentation credential, String email) {
        Optional<UserRepresentation> user = admin.getUsersResource().list().stream().filter(usr -> usr.getEmail().equals(email)).findAny();
        if (user.isPresent()) {
            UserRepresentation user1 = user.get();
            UserResource userResource = admin.getUsersResource().get(user1.getId());
            userResource.resetPassword(credential);
            log.info("password updated");
        }

    }

    private void desactivateUserInProvider(String id) {
//        String userId = findByEmailInProvider(userBo.getEmail()).getId();
        log.info("user desactivated in provider");
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(false);
        admin.getUsersResource().get(id).update(user);
    }

    private void activateUserInProvider(String  userId) {
        //String userId = findByEmailInProvider(userBo.getUsername()).getId();
        log.info("user desactivated in provider");
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        admin.getUsersResource().get(userId).update(user);
    }

}
