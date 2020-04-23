package dhbw.online.bookly.service;

import dhbw.online.bookly.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakPrincipal;
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
        IDToken idToken = getIDToken();
        return User.builder()
                .first_name(idToken.getGivenName())
                .last_name(idToken.getFamilyName())
                .mail(idToken.getEmail())
                .username(idToken.getPreferredUsername())
                .build();
    }

    public IDToken getIDToken(){
        return getPrincipal().getKeycloakSecurityContext().getIdToken();
    }
}
