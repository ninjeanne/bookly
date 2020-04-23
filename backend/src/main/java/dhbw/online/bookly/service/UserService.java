package dhbw.online.bookly.service;

import dhbw.online.bookly.dto.User;
import dhbw.online.bookly.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    public User getUser() {
        sync();
        if (!friendshipBookService.exists()) {
            friendshipBookService.create();
        }
        return authenticationService.getUser();
    }

    /*
     * TODO Logout implementieren und Token löschen im FE und Session invalidieren im BE, Schnittstellen neu implementieren, Nutzer komplett in KC auslagern
     * TODO Keycloak weitere Nutzerattribute erstellen und Registrierung erweitern
     * TODO Keycloak Mailvalidierung erstellen -> SMTP Server notwendig
     * TODO Accountverwaltung über Keycloak einbinden
     * */

    public boolean sync() {
        User user = authenticationService.getUser();
        userRepository.save(user);
        log.debug("User {} has been updated", user.getUsername());
        return true;
    }

}
