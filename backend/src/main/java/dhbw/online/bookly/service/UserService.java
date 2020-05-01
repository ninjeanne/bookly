package dhbw.online.bookly.service;

import dhbw.online.bookly.configuration.KeycloakConfiguration;
import dhbw.online.bookly.dto.User;
import dhbw.online.bookly.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private FriendshipBookService friendshipBookService;

    public User getUser() {
        sync();
        if (!friendshipBookService.exists()) {
            log.debug("Friendship book for user does not exist!");
            friendshipBookService.create();
        }
        return authenticationService.getUser();
    }

    public void update(User user){
        UserResource userResource = authenticationService.getUserResource();
        userResource.update(convertUser(user));
    }

    public void delete(){
        User user = authenticationService.getUser();
        userRepository.delete(user);
        log.debug("Deleted user {} on database", user.getUsername());

        UserResource userResource = authenticationService.getUserResource();
        userResource.remove();
        log.debug("Deleted user {} on Keycloak", user.getUsername());
    }

    private UserRepresentation convertUser(User user){
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setFirstName(user.getFirst_name());
        userRepresentation.setLastName(user.getLast_name());
        userRepresentation.setEmail(user.getMail());
        return userRepresentation;
    }

    public boolean sync() {
        User user = authenticationService.getUser();
        userRepository.save(user);
        log.debug("User {} has been updated", user.getUsername());
        return true;
    }

}
