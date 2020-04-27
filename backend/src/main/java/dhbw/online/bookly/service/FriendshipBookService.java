package dhbw.online.bookly.service;

import dhbw.online.bookly.dto.FriendshipBook;
import dhbw.online.bookly.dto.FriendshipBookCover;
import dhbw.online.bookly.dto.User;
import dhbw.online.bookly.exception.FriendshipBookException;
import dhbw.online.bookly.repository.FriendshipBookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Service
@Slf4j
public class FriendshipBookService {
    @Autowired
    private FriendshipBookRepository repository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    public boolean exists(){
        return repository.existsByUser(authenticationService.getUser());
    }

    public boolean create() {
        User user = authenticationService.getUser();
        if (!exists()) {
            repository.save(FriendshipBook.builder()
                    .user(user)
                    .title("My Friendship Book")
                    .pages(new ArrayList<>())
                    .build());
            log.debug("Book created for user {}", user.getUsername());
            return true;
        }
        log.debug("Book for user {} does already exist.", user.getUsername());
        return false;
    }

    public void saveImageForBook(FriendshipBook friendshipBook, MultipartFile file) {
        try {
            saveImageForBook(friendshipBook, file.getBytes(), file.getSize(), file.getContentType());
        } catch (NullPointerException | IOException e) {
            throw new FriendshipBookException("Image couldn't be saved.");
        }
    }

    public void saveImageForBook(FriendshipBook friendshipBook, byte[] data, long size, String contentType) {
        try {
            friendshipBook.setCover(FriendshipBookCover.builder()
                    .data(data)
                    .size(size)
                    .mediaType(contentType)
                    .build());
            repository.save(friendshipBook);
            log.debug("Book cover updated for user {} and its book id {}", friendshipBook.getUser().getUsername(), friendshipBook.getUuid());
        } catch (NullPointerException e) {
            throw new FriendshipBookException("Image couldn't be saved.");
        }
    }

    @Nullable
    public FriendshipBook read() {
        User user = userService.getUser();
        Optional<FriendshipBook> book = repository.findByUser(user);
        if (!book.isPresent()) {
            log.warn("Requested user {} couldn't be found in database", user.getUsername());
        }
        return book.orElse(null);
    }

    public boolean delete() {
        User user = userService.getUser();
        FriendshipBook book = read();
        if (book != null) {
            repository.delete(book);
            log.debug("Book of user {} with book id {} has been deleted", user.getUsername(), book.getUuid());
            return true;
        }
        log.warn("Book for user {} doesn't exist. Has it already been deleted?", user.getUsername());
        return false;
    }

    public FriendshipBook updateTitle(String title) {
        User user = userService.getUser();
        FriendshipBook book = read();
        if (book != null) {
            book.setTitle(title);
            repository.save(book);
            log.debug("Book title of book from user {} and with book id {} and title {} has been updated", user.getUsername(), book.getUuid(), title);
            return book;
        }
        throw new FriendshipBookException("There is no book for user " + user.getUsername());
    }
}
