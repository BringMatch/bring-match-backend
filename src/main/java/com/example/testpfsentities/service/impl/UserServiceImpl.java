package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.config.AdminManagementBuilder;
import com.example.testpfsentities.dto.OwnerDto;
import com.example.testpfsentities.dto.PlayerDto;
import com.example.testpfsentities.dto.UserDto;
import com.example.testpfsentities.email.EmailSenderForOwner;
import com.example.testpfsentities.entities.Owner;
import com.example.testpfsentities.entities.Player;
import com.example.testpfsentities.entities.User;
import com.example.testpfsentities.exceptions.BusinessException;
import com.example.testpfsentities.mail.OwnerSenderEmail;
import com.example.testpfsentities.mapper.OwnerMapper;
import com.example.testpfsentities.mapper.PlayerMapper;
import com.example.testpfsentities.repository.OwnerRepository;
import com.example.testpfsentities.repository.PlayerRepository;
import com.example.testpfsentities.service.UserService;
import com.example.testpfsentities.utils.PasswordUtils;
import com.example.testpfsentities.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.*;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Slf4j

public class UserServiceImpl implements UserService {
    private final OwnerRepository ownerRepository;
    private final AdminManagementBuilder admin;
    private final PlayerMapper playerMapper;
    private final PlayerRepository playerRepository;
    private final OwnerMapper ownerMapper;
    private final OwnerSenderEmail ownerEmailSender;
    private final EmailSenderForOwner emailSenderForOwner;

    @Override
    public void create(PlayerDto playerDto) throws BusinessException {
        Player user = playerMapper.toBo(playerDto);
        saveUserInProviderWithPermanentPassword(user);
        var player = playerRepository.save(user);
        String subject = "compte créé avec succes";
        String body = "Bienvenue chez Bring Match " + System.lineSeparator() +
                "Merci pour votre inscription à notre application " + System.lineSeparator() +
                "votre inscription est acceptée" + System.lineSeparator() +
                "Merci de ne pas répondre a cette email";
        emailSenderForOwner.sendEmail(player.getEmail(), subject, body);

    }

    @Override
    public void create(OwnerDto ownerDto) throws BusinessException {
        Owner user = ownerMapper.toBo(ownerDto);
        saveUserInProviderWithPermanentPassword(user);
        var owner = ownerRepository.save(user);
        //ownerEmailSender.newEmailSender(owner);
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
        log.info("this is the email  {}", email);
        UserRepresentation aa = admin.getUsersResource().search(email).get(0);
        log.info("aa {}", aa.getId());
        var list = admin.getUsersResource();
        for (var item : list.list()) {
            if (item.getEmail().equals(email)) {
                return aa;
            }
        }
        return null;
    }

//    private void addRoleToUser(String email, String roleName) {
//        String userId = findByEmailInProvider(email).getId();
//        UserResource user = admin.getUsersResource()
//                .get(userId);
//        RoleRepresentation role = admin.getRealmResource()
//                .roles()
//                .get(roleName)
//                .toRepresentation();
//
//        user.roles().realmLevel().add(Collections.singletonList(role));
//        log.info("role {} added to user", roleName);
//    }

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
        user.setUsername(userBo.getUsername());
        user.setCredentials(Collections.singletonList(credential));
        user.setEnabled(true);

        admin.getUsersResource().list().add(user);
        Response response = admin.getUsersResource().create(user);
        addRoleToUser(response, admin, userBo);
        //addRoleToUser(userBo.getEmail(), userBo.getRoleName().name());
        // userBo.getRoles().stream().map(Role::name).forEach(roleName -> addRoleToUser(userBo.getEmail(), roleName));
        log.info("user saved in provider");
    }

    private void addRoleToUser(Response response, AdminManagementBuilder admin, User userBo) {
        if (response.getStatus() == 201) {
            RoleRepresentation roleRepresentation = admin.getRealmResource().roles()
                    .get(userBo.getRoleName().name()).toRepresentation();
            admin.getRealmResource().users().get(CreatedResponseUtil.getCreatedId(response)).roles().realmLevel().add(Arrays.asList(roleRepresentation));
        } else if (response.getStatus() == 409) {
            throw new IllegalArgumentException("that User already exists");
        } else {
            throw new IllegalArgumentException("error in creating the user !");
        }
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

    @Override
    public OwnerDto getOwnerConnected() {
        Optional<String> email = SecurityUtils.getUserEmail();
        if (email.isEmpty()) {
            throw new IllegalArgumentException("email not found !");
        }
        return ownerMapper.toDto(ownerRepository.findByEmail(email.get()).get());
    }

    @Override
    public Player getPlayerConnected(){
        Optional<String> email = SecurityUtils.getUserEmail();
        if (email.isEmpty()) {
            throw new IllegalArgumentException("email not found !");
        }
        return playerRepository.findByEmail(email.get()).get();
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

    private void activateUserInProvider(String userId) {
        //String userId = findByEmailInProvider(userBo.getUsername()).getId();
        log.info("user desactivated in provider");
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        admin.getUsersResource().get(userId).update(user);
    }

}
