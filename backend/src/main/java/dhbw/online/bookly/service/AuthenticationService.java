package dhbw.online.bookly.service;

import dhbw.online.bookly.configuration.KeycloakConfiguration;
import dhbw.online.bookly.dto.User;
import dhbw.online.bookly.exception.AuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class AuthenticationService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private KeycloakConfiguration keycloakConfiguration;

    public String getUsername() {
        return getPrincipal().getName();
    }

    private KeycloakPrincipal getPrincipal() throws AuthenticationException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof String) {
            throw new AuthenticationException(principal.toString(), "Somehow the visitor was not logged in properly.");
        }
        return (KeycloakPrincipal) principal;
    }

    public void logout() {
        try {
            String username = getUsername();
            request.logout();
            log.debug("Logged out the user {}", username);
        } catch (ServletException e) {
            throw new AuthenticationException("Couldn't log out the current user" + getUsername());
        }
    }

    public Keycloak getAdminKeycloakInstance() {
        return KeycloakBuilder.builder()
                .serverUrl(keycloakConfiguration.getKEYCLOAK_URL())
                .realm(keycloakConfiguration.getKEYCLOAK_REALM())
                .grantType(OAuth2Constants.PASSWORD)
                .clientId(keycloakConfiguration.getKEYCLOAK_RESOURCE())
                .clientSecret(keycloakConfiguration.getKEYCLOAK_CREDENTIALS())
                .username(keycloakConfiguration.getKEYCLOAK_USER())
                .password(keycloakConfiguration.getKEYCLOAK_USER_PASSWORD())
                .build();
    }

    public UserResource getUserResource() {
        Keycloak keycloak = getAdminKeycloakInstance();

        RealmResource realmResource = keycloak.realm(keycloakConfiguration.getKEYCLOAK_REALM());
        UsersResource usersRessource = realmResource.users();

        String subjectId = getAccessToken().getSubject();
        return usersRessource.get(subjectId);
    }

    public User getUser() {
        AccessToken accessToken = getAccessToken();
        return User.builder().first_name(accessToken.getGivenName()).last_name(accessToken.getFamilyName()).mail(accessToken.getEmail())
                .username(accessToken.getPreferredUsername()).build();
    }

    public AccessToken getAccessToken() {
        return getPrincipal().getKeycloakSecurityContext().getToken();
    }
}
