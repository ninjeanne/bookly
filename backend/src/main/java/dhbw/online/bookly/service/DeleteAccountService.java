package dhbw.online.bookly.service;

import dhbw.online.bookly.dto.User;
import dhbw.online.bookly.repository.FriendshipBookRepository;
import dhbw.online.bookly.repository.PageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeleteAccountService {
    @Autowired
    private FriendshipBookRepository friendshipBookRepository;
    @Autowired
    private PageRepository pageRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationService authenticationService;

    public void deleteAndLogout(){
        User user = authenticationService.getUser();
        friendshipBookRepository.deleteByUser(user);
        log.debug("Deleted friendship book for user {}", user.getUsername());
        userService.delete();
        authenticationService.logout();
        log.debug("Logged out the user {}", user.getUsername());
    }
}
