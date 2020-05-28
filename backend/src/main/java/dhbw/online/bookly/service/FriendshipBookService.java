package dhbw.online.bookly.service;

import dhbw.online.bookly.dto.DummyImage;
import dhbw.online.bookly.dto.FriendshipBook;
import dhbw.online.bookly.dto.Image;
import dhbw.online.bookly.dto.User;
import dhbw.online.bookly.exception.FriendshipBookException;
import dhbw.online.bookly.repository.FriendshipBookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    public boolean existsBookForLoggedInUser() {
        User user = authenticationService.getUser();
        boolean exists = repository.existsByUser(user);
        if (!exists) {
            log.warn("Friendship book for user {} does not exist!", user.getUsername());
        }
        return exists;
    }

    public boolean createBookForLoggedInUser() {
        User user = authenticationService.getUser();
        if (!existsBookForLoggedInUser()) {
            FriendshipBook empty = createEmptyFriendshipBook(user);
            repository.save(empty);
            log.debug("Book created for user {}", user.getUsername());
            return true;
        }
        log.debug("Book for user {} does already exist.", user.getUsername());
        return false;
    }

    private FriendshipBook createEmptyFriendshipBook(User user) {
        return FriendshipBook.builder().cover(new DummyImage()).user(user).title("My Friendship Book").pages(new ArrayList<>()).build();
    }

    public void saveCover(MultipartFile file) {
        try {
            saveCover(file.getBytes(), file.getSize(), file.getContentType());
        } catch (NullPointerException | IOException e) {
            throw new FriendshipBookException("Image couldn't be saved.");
        }
    }

    public void saveCover(byte[] data, long size, String contentType) {
        FriendshipBook friendshipBook = getBookForLoggedInUser();
        Image cover = createFriendshipBookCover(data, size, contentType);
        friendshipBook.setCover(cover);
        repository.save(friendshipBook);
        log.debug("Book cover updated for user {} and its book id {}", friendshipBook.getUser().getUsername(), friendshipBook.getUuid());
    }

    private Image createFriendshipBookCover(byte[] data, long size, String contentType) {
        return Image.builder().data(data).size(size).mediaType(contentType).build();
    }

    public FriendshipBook getBookForLoggedInUser() {
        User user = userService.getUser();
        Optional<FriendshipBook> bookOptional = repository.findByUser(user);
        if (!bookOptional.isPresent()) {
            log.warn("Requested user {} couldn't be found in database", user.getUsername());
            throw new FriendshipBookException("Friendship Book couldn't be found!");
        }
        return bookOptional.get();
    }

    public void deleteBookForLoggedInUser() {
        FriendshipBook book = getBookForLoggedInUser();
        repository.delete(book);
        log.debug("Book of user {} with book id {} has been deleted", book.getUser().getUsername(), book.getUuid());
    }

    public void deleteCoverForLoggedInUser() {
        FriendshipBook book = getBookForLoggedInUser();
        book.setCover(null);
        repository.save(book);
        log.debug("Book cover of user {} with book id {} has been deleted", book.getUser().getUsername(), book.getUuid());
    }

    public FriendshipBook updateTitleForLoggedInUser(String title, String subtitle, int theme) {
        FriendshipBook book = getBookForLoggedInUser();
        book.setTitle(title);
        book.setSubtitle(subtitle);
        book.setTheme(theme);
        repository.save(book);
        log.debug("Book titles and theme of book from user {} have been updated", book.getUser().getUsername());
        return book;
    }

    public void saveSticker(int stickerNumber, MultipartFile file) {
        FriendshipBook friendshipBook = getBookForLoggedInUser();
        try {
            friendshipBook.setSticker(stickerNumber, Image.builder().data(file.getBytes()).size(file.getSize()).mediaType(file.getContentType()).build());
        } catch (IOException e) {
           throw new FriendshipBookException("Couldn't update sticker " + stickerNumber);
        }
        repository.save(friendshipBook);
        log.debug("Sticker {} updated for user {} and its book id {}", stickerNumber, friendshipBook.getUser().getUsername(), friendshipBook.getUuid());
    }

    public void deleteSticker(int stickerNumber) {
        FriendshipBook book = getBookForLoggedInUser();
        book.setSticker(stickerNumber, new DummyImage());
        repository.save(book);
        log.debug("Book sticker of user {} with sticker {} has been deleted", book.getUser().getUsername(), stickerNumber);
    }
}

