package dhbw.online.bookly;

import dhbw.online.bookly.dto.FriendshipBook;
import dhbw.online.bookly.dto.User;
import dhbw.online.bookly.repository.FriendshipBookRepository;
import dhbw.online.bookly.service.AuthenticationService;
import dhbw.online.bookly.service.FriendshipBookService;
import dhbw.online.bookly.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class FriendshipBookServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private FriendshipBookRepository friendshipBookRepository;

    @InjectMocks
    @Spy
    private FriendshipBookService friendshipBookService;

    @Mock
    private AuthenticationService authenticationService;


    private User user = User.builder().username("test-user").mail("tester@test.com").build();
    private User otherUser = User.builder().username("other-user").mail("testerchen@test.com").build();

    @Mock
    private FriendshipBook friendshipBook = FriendshipBook.builder().uuid(1).title("Some title").user(user).build();

    @Before
    public void setUp() {
        Mockito.when(userService.getUser()).thenReturn(user);
        Mockito.when(authenticationService.getUser()).thenReturn(user);
        Mockito.when(authenticationService.getUsername()).thenReturn(user.getUsername());
        Mockito.when(friendshipBookRepository.findByUser(user)).thenReturn(Optional.of(friendshipBook));
        Mockito.when(friendshipBookRepository.existsByUser(user)).thenReturn(true);
        Mockito.when(friendshipBookRepository.existsByUser(otherUser)).thenReturn(false);
    }

    @Test
    public void testupdateTitle() {
        Assert.assertEquals(friendshipBookService.updateTitle("Other title").getTitle(), "Other title");
        verify(friendshipBook, times(1)).setTitle("Other title");
    }

    @Test
    public void testDelete() {
        Assert.assertTrue(friendshipBookService.delete());
        verify(friendshipBookRepository, times(1)).delete(friendshipBook);
    }

    @Test
    public void testDeleteCover() {
        Assert.assertTrue(friendshipBookService.deleteCover());
        verify(friendshipBook, times(1)).setCover(null);
        verify(friendshipBookRepository, atLeastOnce()).save(friendshipBook);
    }

    @Test
    public void testCreate() {
        Assert.assertFalse(friendshipBookService.create());
        doReturn(true).when(friendshipBookService).exists();
        verify(friendshipBookRepository, never()).save(friendshipBook);

        Mockito.when(userService.getUser()).thenReturn(otherUser);
        Mockito.when(authenticationService.getUser()).thenReturn(otherUser);
        verify(friendshipBookService, times(1)).exists();
        doReturn(false).when(friendshipBookService).exists();
        Assert.assertTrue(friendshipBookService.create());

        verify(friendshipBookRepository, times(1)).save(any());
    }

    @Test
    public void testExists() {
        Assert.assertTrue(friendshipBookService.exists());
        Mockito.when(userService.getUser()).thenReturn(otherUser);
        Mockito.when(authenticationService.getUser()).thenReturn(otherUser);
        Assert.assertFalse(friendshipBookService.exists());
    }

}
