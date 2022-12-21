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

    @Value("${keycloak.clientId}")
    private String clientId;

    @Value("${keycloak.username:''}")
    private String username;

    private String password;
    @Value("${keycloak.password:''}")


    public UsersResource getUsersResource() {
        return newKeycloakBuilderWithClientCredentials().build().realm(realm).users();
    }

    public ClientsResource getClientResource() {
        return newKeycloakBuilderWithClientCredentials().build().realm(realm).clients();
    }

    public RealmResource getRealmResource() {
        Keycloak kc = KeycloakBuilder.builder().serverUrl(keycloakUrl).realm(realm).username("yessine")
                .password("admin").clientId("admin-cli").resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build())
                .build();
        return kc.realm(realm);
//        return newKeycloakBuilderWithClientCredentials().build().realm(realm);
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
