package dhbw.online.bookly;

import dhbw.online.bookly.dto.User;
import dhbw.online.bookly.repository.UserRepository;
import dhbw.online.bookly.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserServiceTest {

    @Mock
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp(){
        Mockito.when(userService.getUser()).thenReturn(User.builder().username("user").mail("tester@test.com").build());
    }

    @Test
    public void testGetUser(){
        Assert.assertEquals(userService.getUser(), User.builder().username("user").mail("tester@test.com").build());
        userRepository.save(User.builder().username("user").mail("tester@test.com").build());
        Assert.assertTrue(userRepository.existsByUsername("user"));
       // Mockito.verify(userService.getUser(), Mockito.calls(1));
        userService.delete();
    }
}
