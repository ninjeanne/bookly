package dhbw.online.bookly.service;

import dhbw.online.bookly.dto.User;
import dhbw.online.bookly.exception.AuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticationService {

    public String getUsername() {
        return getPrincipal().getName();
    }

    private KeycloakPrincipal getPrincipal() throws AuthenticationException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof String){
            throw new AuthenticationException(principal.toString(), "Somehow the visitor was not logged in properly.");
        }
        return (KeycloakPrincipal) principal;
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
