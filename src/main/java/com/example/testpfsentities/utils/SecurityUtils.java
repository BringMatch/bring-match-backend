package com.example.testpfsentities.utils;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.keycloak.representations.AccessToken;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.Optional;

public interface SecurityUtils {

    static Optional<String> getUserEmail() {
        if (isAuthenticated() && isPrincipalInstanceOfKeycloakPrincipal()) {
            return  Optional.of(getAccessToken().getEmail());
        }
        return Optional.empty();
    }
    static  boolean hasAnyAuthority(String... authorities) {
        if (isAuthenticated() && isPrincipalInstanceOfKeycloakPrincipal()) {
            return getAccessToken().getRealmAccess().getRoles().stream().anyMatch(authority ->  Arrays.asList(authorities).contains(authority));
        }
        return false;
    }


    private static boolean isPrincipalInstanceOfKeycloakPrincipal() {
        return getAuthentication().getPrincipal() instanceof KeycloakPrincipal;
    }
    private static boolean isAuthenticated() {
        Authentication authentication = getAuthentication();
        return authentication != null
                && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken);
    }

    private static AccessToken getAccessToken() {
        SimpleKeycloakAccount account = (SimpleKeycloakAccount) getAuthentication().getDetails();
        return account.getKeycloakSecurityContext().getToken();
    }

    private static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
