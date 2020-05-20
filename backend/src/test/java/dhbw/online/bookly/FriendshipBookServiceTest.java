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
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private FriendshipBookService friendshipBookService;

    @Mock
    private AuthenticationService authenticationService;

    private User user = User.builder().username("test-user").mail("tester@test.com").build();
    private User otherUser = User.builder().username("other-user").mail("testerchen@test.com").build();

    @Mock
    private FriendshipBook friendshipBook = FriendshipBook.builder().uuid(1).title("Some title").user(user).build();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(userService.getUser()).thenReturn(user);
        Mockito.when(authenticationService.getUser()).thenReturn(user);
        Mockito.when(authenticationService.getUsername()).thenReturn(user.getUsername());
        Mockito.when(friendshipBookRepository.findByUser(user)).thenReturn(Optional.of(friendshipBook));
        Mockito.when(friendshipBookRepository.existsByUser(user)).thenReturn(true);
        Mockito.when(friendshipBookRepository.existsByUser(otherUser)).thenReturn(false);
        Mockito.when(friendshipBook.getUser()).thenReturn(user);
    }

    @Test
    public void testupdateTitle() {
        String title = "Other title";
        FriendshipBook friendshipBook = friendshipBookService.updateTitleForLoggedInUser(title, null);
        verify(friendshipBook, times(1)).setTitle(title);
        verify(friendshipBook, times(1)).setSubtitle(null);
        verify(friendshipBookRepository, times(1)).save(friendshipBook);
    }

    @Test
    public void testupdateSubtitle() {
        String title = "Other subtitle";

        FriendshipBook friendshipBook = friendshipBookService.updateTitleForLoggedInUser(null, title);
        verify(friendshipBook, times(1)).setTitle(null);
        verify(friendshipBook, times(1)).setSubtitle(title);
        verify(friendshipBookRepository, times(1)).save(friendshipBook);
    }

    @Test
    public void testupdateNullTitles() {
        FriendshipBook friendshipBook = friendshipBookService.updateTitleForLoggedInUser(null, null);
        verify(friendshipBook, times(1)).setTitle(null);
        verify(friendshipBook, times(1)).setSubtitle(null);
        verify(friendshipBookRepository, times(1)).save(friendshipBook);
    }

    @Test
    public void testupdateTitles() {
        String title = "title";
        String subtitle = "subtitle";
        FriendshipBook friendshipBook = friendshipBookService.updateTitleForLoggedInUser(title, subtitle);
        verify(friendshipBook, times(1)).setTitle(title);
        verify(friendshipBook, times(1)).setSubtitle(subtitle);
        verify(friendshipBookRepository, times(1)).save(friendshipBook);
    }

    @Test
    public void testDelete() {
        Assert.assertTrue(friendshipBookService.deleteBookForLoggedInUser());
        verify(friendshipBookRepository, times(1)).delete(friendshipBook);
    }

    @Test
    public void testDeleteCover() {
        Assert.assertTrue(friendshipBookService.deleteCoverForLoggedInUser());
        verify(friendshipBook, times(1)).setCover(null);
        verify(friendshipBookRepository, atLeastOnce()).save(friendshipBook);
    }

    @Test
    public void testCreate() {
        Assert.assertFalse(friendshipBookService.createBookForLoggedInUser());
        doReturn(true).when(friendshipBookService).existsBookForLoggedInUser();
        verify(friendshipBookRepository, never()).save(friendshipBook);

        Mockito.when(userService.getUser()).thenReturn(otherUser);
        Mockito.when(authenticationService.getUser()).thenReturn(otherUser);
        verify(friendshipBookService, times(1)).existsBookForLoggedInUser();
        doReturn(false).when(friendshipBookService).existsBookForLoggedInUser();
        Assert.assertTrue(friendshipBookService.createBookForLoggedInUser());

        verify(friendshipBookRepository, times(1)).save(any());
    }

    @Test
    public void testExists() {
        Assert.assertTrue(friendshipBookService.existsBookForLoggedInUser());
        Mockito.when(userService.getUser()).thenReturn(otherUser);
        Mockito.when(authenticationService.getUser()).thenReturn(otherUser);
        Assert.assertFalse(friendshipBookService.existsBookForLoggedInUser());
    }

}
