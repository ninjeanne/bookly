package dhbw.online.bookly.service;

import dhbw.online.bookly.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.IDToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticationService {

    public String getUsername() {
        return getPrincipal().getName();
    }

    private KeycloakPrincipal getPrincipal(){
        return (KeycloakPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public User getUser(){
        AccessToken accessToken = getAccessToken();
        return User.builder()
                .first_name(accessToken.getGivenName())
                .last_name(accessToken.getFamilyName())
                .mail(accessToken.getEmail())
                .username(accessToken.getPreferredUsername())
                .build();
    }

    public AccessToken getAccessToken(){
        return getPrincipal().getKeycloakSecurityContext().getToken();
    }
}
