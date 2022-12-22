package com.example.testpfsentities.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.ClientsResource;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AdminManagementBuilder {

    @Value("${keycloak.auth-server-url}")
    private String keycloakUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${management.admin.clientId}")
    private String clientId;

    @Value("${management.admin.username}")
    private String username;

    @Value("${management.admin.password}")
    private String password;


    public UsersResource getUsersResource() {
        return newKeycloakBuilderWithClientCredentials().build().realm(realm).users();
    }

    public ClientsResource getClientResource() {
        return newKeycloakBuilderWithClientCredentials().build().realm(realm).clients();
    }

    public RealmResource getRealmResource() {
        return newKeycloakBuilderWithClientCredentials().build().realm(realm);
    }

    private KeycloakBuilder newKeycloakBuilderWithClientCredentials() {
        return KeycloakBuilder.builder()
                .realm(realm)
                .serverUrl(keycloakUrl)
                .clientId(clientId)
                .username(username)
                .password(password)
                .grantType(OAuth2Constants.PASSWORD);
    }

}
