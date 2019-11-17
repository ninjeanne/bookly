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

    public boolean exists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Nullable
    public User getUser() {
        String username = authenticationService.getLoggedInUser();
        if (!exists(username)) {
            create(username);
        }
        return userRepository.findByUsername(username).orElse(null);
    }

    public void create(String username) {
        if (!exists(username)) {
            userRepository.save(
                    User.builder()
                            .username(username)
                            .build());
        }
    }
}
