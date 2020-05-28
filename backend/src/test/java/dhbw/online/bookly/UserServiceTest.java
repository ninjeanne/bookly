package dhbw.online.bookly;

import dhbw.online.bookly.dto.User;
import dhbw.online.bookly.repository.UserRepository;
import dhbw.online.bookly.service.AuthenticationService;
import dhbw.online.bookly.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserServiceTest {

    @Mock
    private UserService userService;
    @Mock
    private AuthenticationService authenticationService;

    private User user = User.builder().username("test-user").mail("tester@test.com").build();

    @Before
    public void setUp() {
        Mockito.when(userService.getUser()).thenReturn(user);
        Mockito.when(authenticationService.getUser()).thenReturn(user);
        Mockito.when(authenticationService.getUsername()).thenReturn(user.getUsername());
    }

    @Test
    public void testGetUser() {
        Assert.assertEquals(userService.getUser(), user);
    }

}
