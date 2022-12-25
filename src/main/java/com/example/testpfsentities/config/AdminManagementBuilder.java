package com.example.testpfsentities.config;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
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

    @Value("${keycloak.credentials.secret}")
    private String secretKey;


    public UsersResource getUsersResource() {
        return newKeycloakBuilderWithClientCredentials().realm(realm).users();
    }

    public ClientsResource getClientResource() {
        return newKeycloakBuilderWithClientCredentials().realm(realm).clients();
    }

    public RealmResource getRealmResource() {
        return newKeycloakBuilderWithClientCredentials().realm(realm);
    }

    private Keycloak newKeycloakBuilderWithClientCredentials() {
        return KeycloakBuilder.builder()
                .realm(realm)
                .serverUrl(keycloakUrl)
                .clientId(clientId)
                .clientSecret(secretKey)
                .username(username)
                .password(password)
                .grantType(OAuth2Constants.PASSWORD)
                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build()).build();
    }

}
