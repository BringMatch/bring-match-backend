package com.example.testpfsentities.service.impl;

import com.amazonaws.services.apigateway.model.Op;
import com.example.testpfsentities.config.AdminManagementBuilder;
import com.example.testpfsentities.dto.OwnerDto;
import com.example.testpfsentities.dto.PlayerDto;
import com.example.testpfsentities.dto.UserDto;
import com.example.testpfsentities.email.EmailSenderForOwner;
import com.example.testpfsentities.entities.Admin;
import com.example.testpfsentities.entities.Owner;
import com.example.testpfsentities.entities.Player;
import com.example.testpfsentities.entities.User;
import com.example.testpfsentities.exceptions.BusinessException;
import com.example.testpfsentities.mail.OwnerSenderEmail;
import com.example.testpfsentities.mapper.OwnerMapper;
import com.example.testpfsentities.mapper.PlayerMapper;
import com.example.testpfsentities.repository.AdminRepository;
import com.example.testpfsentities.repository.OwnerRepository;
import com.example.testpfsentities.repository.PlayerRepository;
import com.example.testpfsentities.repository.UserRepository;
import com.example.testpfsentities.service.PlayerStatsService;
import com.example.testpfsentities.service.UserService;
import com.example.testpfsentities.utils.PasswordUtils;
import com.example.testpfsentities.utils.SecurityUtils;
import com.example.testpfsentities.utils.consts.ErrorCodes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.resource.ClientResource;
import org.keycloak.admin.client.resource.ClientsResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.ClientRepresentation;
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
    private final AdminRepository adminRepository;
    private final OwnerRepository ownerRepository;
    private final AdminManagementBuilder admin;
    private final PlayerMapper playerMapper;
    private final PlayerRepository playerRepository;
    private final OwnerMapper ownerMapper;
    private final OwnerSenderEmail ownerEmailSender;
    private final UserRepository userRepository;
    private final EmailSenderForOwner emailSenderForOwner;
    private final PlayerStatsService playerStatsService;

    @Override
    public void create(PlayerDto playerDto) throws BusinessException {
        Player user = playerMapper.toBo(playerDto);
        saveUserInProviderWithPermanentPassword(user);
//        var player = playerRepository.save(user);

        String subject = "compte créé avec succes";
        String body = "Bienvenue chez Bring Match " + System.lineSeparator() +
                "Merci pour votre inscription à notre application " + System.lineSeparator() +
                "votre inscription est acceptée" + System.lineSeparator() +
                "Merci de ne pas répondre a cette email";
        //emailSenderForOwner.sendEmail(player.getEmail(), subject, body);

    }

    @Override
    public void create(OwnerDto ownerDto) throws BusinessException {
        Owner user = ownerMapper.toBo(ownerDto);
        saveUserInProviderWithPermanentPassword(user);
        //ownerEmailSender.newEmailSender(owner);
    }

    @Override
    public void disableUser(String id) {
        Optional<User> userBo = userRepository.findById(id);
        if (userBo.isEmpty()) throw new BusinessException(ErrorCodes.USER_NOT_FOUND);
        userBo.get().setActive(false);
        userRepository.save(userBo.get());
        desactivateUserInProvider(userBo.get());
    }

    @Override
    public void enableUser(String id) {
        Optional<User> userBo = userRepository.findById(id);
        if (userBo.isEmpty()) throw new BusinessException(ErrorCodes.USER_NOT_FOUND);
        userBo.get().setActive(true);
        userRepository.save(userBo.get());
        activateUserInProvider(userBo.get());
    }

    private UserRepresentation findByEmailInProvider(String email) {
        log.info("this is the email {}", email);
        var list = admin.getUsersResource();
        log.info("this is the length of all users {}", list.list().size());
        for (var item : list.list()) {
            if (item.getEmail().equals(email)) {
                return item;
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
        user.setEmailVerified(true);
//        HashMap<String, List<String>> clientRoles = new HashMap<>();
//        clientRoles.put("bring-match-front", List.of("OWNER"));
//        user.setClientRoles(clientRoles);
        if (userBo instanceof Owner) {
            user.setEnabled(true);
        } else {
            user.setEnabled(true);
        }

        admin.getUsersResource().list().add(user);
        Response response = admin.getUsersResource().create(user);

//        var list = admin.getRealmResource().clients().findAll();
//        log.info("this is the id of the client number zero {} ", list.get(1).getName());
//        for (var item : list) {
//            log.info("this is the name {}", item.getName());
//        }
//        log.info("this is the length {} ", (long) list.size());

//        RoleRepresentation userClientRole = admin.getRealmResource().clients().get("${client_bring-match}") //
//                .roles().get(userBo.getRoleName().name()).toRepresentation();
//
//        ClientRepresentation app1Client = admin.getRealmResource().clients() //
//                .findByClientId("bring-match").get(0);
//
//        log.info("this is the representation of the client representation {}", app1Client.getId());
//
//        admin.getRealmResource().users().get(CreatedResponseUtil.getCreatedId(response))
//                .roles()
//                .clientLevel(app1Client.getId())
//                .add(Collections.singletonList(userClientRole));

        addRoleToUser(response, admin, userBo);
        //addRoleToUser(userBo.getEmail(), userBo.getRoleName().name());
        // userBo.getRoles().stream().map(Role::name).forEach(roleName -> addRoleToUser(userBo.getEmail(), roleName));
        log.info("user saved in provider");
        userBo.getId();
    }

    private void addRoleToUser(Response response, AdminManagementBuilder admin, User userBo) {
        if (response.getStatus() == 201) {

            RoleRepresentation roleRepresentation = admin.getRealmResource().roles()
                    .get(userBo.getRole().name()).toRepresentation();

            var user = admin.getRealmResource()
                    .users()
                    .get(CreatedResponseUtil.getCreatedId(response));

            user.roles().realmLevel()
                    .add(Collections.singletonList(roleRepresentation));

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
    public OwnerDto getOwnerDtoConnected() {
        Optional<String> email = SecurityUtils.getUserEmail();
        if (email.isEmpty()) {
            throw new IllegalArgumentException("email not found !");
        }
        return ownerMapper.toDto(ownerRepository.findByEmail(email.get()).get());
    }

    @Override
    public Owner getOwnerBoConnected() {
        Optional<String> email = SecurityUtils.getUserEmail();
        if (email.isEmpty()) {
            throw new IllegalArgumentException("email not found !");
        }
        if (ownerRepository.findByEmail(email.get()).isEmpty()) {
            throw new IllegalArgumentException("owner not existing ");
        }
        return ownerRepository.findByEmail(email.get()).get();
    }

    @Override
    public Player getPlayerConnected() {
        Optional<String> email = SecurityUtils.getUserEmail();
        if (email.isEmpty()) {
            throw new IllegalArgumentException("email not found !");
        }
        Optional<Player> optionPlayer = playerRepository.findByEmail(email.get());
        if (optionPlayer.isEmpty()) {
            throw new IllegalArgumentException("admin not found !");
        }
        return optionPlayer.get();
    }

    @Override
    public Admin getAdminConnected() {
        Optional<String> email = SecurityUtils.getUserEmail();
        if (email.isEmpty()) {
            throw new IllegalArgumentException("email not found !");
        }

        Optional<Admin> optionalAdmin = adminRepository.findByEmail(email.get());
        if (optionalAdmin.isEmpty()) {
            throw new IllegalArgumentException("admin not found !");
        }
        return optionalAdmin.get();
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

    private void desactivateUserInProvider(User userBo) {
        String userId = findByEmailInProvider(userBo.getEmail()).getId();
        log.info("user desactivated in provider");
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(false);
        admin.getUsersResource().get(userId).update(user);
    }

    private void activateUserInProvider(User userBo) {
        String userId = findByEmailInProvider(userBo.getEmail()).getId();
        log.info("user activated in provider");
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        admin.getUsersResource().get(userId).update(user);
    }

}
