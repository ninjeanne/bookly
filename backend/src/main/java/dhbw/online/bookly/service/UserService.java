package dhbw.online.bookly.service;

import dhbw.online.bookly.dto.User;
import dhbw.online.bookly.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
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

    @Nullable
    public User getUser() {
        String username = authenticationService.getLoggedInUser();
        if (!exists(username) && !username.equals("anonymousUser")) {
            User user = create(username);
            friendshipBookService.create(user);
        }
        return userRepository.findByUsername(username).orElse(null);
    }

    public User create(String username) {
        return userRepository.save(
                User.builder()
                        .username(username)
                        .build());
    }
}
