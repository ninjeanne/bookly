package dhbw.online.bookly.service;

import dhbw.online.bookly.dto.User;
import dhbw.online.bookly.exception.UserException;
import dhbw.online.bookly.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private FriendshipBookService friendshipBookService;

    public boolean exists(String username) {
        return userRepository.existsByUsername(username);
    }

    public User getUser(){
        String username = ((KeycloakPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getName();

        if (!exists(username) && !username.equals("anonymousUser")) {
            User user = create(username);
            friendshipBookService.create(user);
        }
        if (username.equals("anonymousUser")) {
            throw new UserException("Critical error! User is not logged in valid");
        }
        return userRepository.findByUsername(username).orElse(null); //TODO Infos aus Keycloak holen und syncen ggf.
    }

    /*
    * TODO Logout implementieren und Token löschen im FE und Session invalidieren im BE, Schnittstellen neu implementieren, Nutzer komplett in KC auslagern
    * TODO Keycloak weitere Nutzerattribute erstellen und Registrierung erweitern
    * TODO Keycloak Mailvalidierung erstellen -> SMTP Server notwendig
    * TODO Keycloak auf dem Devsystem erstellen und auch von außen verfügbar machen
    * TODO Startseite freigeben, restliche Seiten verbieten
    * TODO Eigenes Login Theme in Keycloak hinzufügen (zumindest das Hintergrundbild ändern)
    * TODO Accountverwaltung über Keycloak einbinden
    * */

    public User update(User user) {
        String username = authenticationService.getLoggedInUser();
        if (username.equals("anonymousUser")) {
            throw new UserException("Critical error! User is not logged in valid");
        }
        user.setUsername(username);
        userRepository.save(user);
        log.debug("User {} has been updated", user.getUsername());
        return user;
    }

    public User create(String username) {
        User user = User.builder()
                .username(username)
                .build();
        userRepository.save(user);
        log.debug("User {} has been created.", user.getUsername());
        return user;
    }
}
