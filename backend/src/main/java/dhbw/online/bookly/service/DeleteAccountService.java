package dhbw.online.bookly.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeleteAccountService {
    @Autowired
    private FriendshipBookService friendshipBookService;
    @Autowired
    private PageService pageService;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationService authenticationService;

    public void deleteAndLogout() {
        pageService.deleteAllPages();
        friendshipBookService.delete();
        userService.delete();
        authenticationService.logout();
    }
}
