package dhbw.online.bookly.service;

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
        return FriendshipBook.builder().user(user).title("My Friendship Book").pages(new ArrayList<>()).build();
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

    public FriendshipBook updateTitleForLoggedInUser(String title, String subtitle) {
        FriendshipBook book = getBookForLoggedInUser();
        book.setTitle(title);
        book.setSubtitle(subtitle);
        repository.save(book);
        log.debug("Book titles of book from user {} have been updated", book.getUser().getUsername());
        return book;
    }

    public FriendshipBook updateCoverThemeOfLoggedInUser(int theme) {
        FriendshipBook book = getBookForLoggedInUser();
        book.setTheme(theme);
        repository.save(book);
        log.debug("Cover theme from user {} has been updated", book.getUser().getUsername());
        return book;
    }

    public void saveSticker(MultipartFile file, int stickerNumber) {
        try {
            switch (stickerNumber) {
                case 1:
                    saveSticker1(file.getBytes(), file.getSize(), file.getContentType());
                    break;
                case 2:
                    saveSticker2(file.getBytes(), file.getSize(), file.getContentType());
                    break;
                default:
                    throw new FriendshipBookException("Wrong sticker number");
            }
        } catch (IOException e) {
            throw new FriendshipBookException("Sticker " + stickerNumber + " couldn't be saved.");
        }
    }

    private void saveSticker1(byte[] data, long size, String contentType) {
        FriendshipBook friendshipBook = getBookForLoggedInUser();
        friendshipBook.setSticker1(Image.builder().data(data).size(size).mediaType(contentType).build());
        repository.save(friendshipBook);
        log.debug("Sticker1 updated for user {} and its book id {}", friendshipBook.getUser().getUsername(), friendshipBook.getUuid());
    }

    private void saveSticker2(byte[] data, long size, String contentType) {
        FriendshipBook friendshipBook = getBookForLoggedInUser();
        friendshipBook.setSticker2(Image.builder().data(data).size(size).mediaType(contentType).build());
        repository.save(friendshipBook);
        log.debug("Sticker2 updated for user {} and its book id {}", friendshipBook.getUser().getUsername(), friendshipBook.getUuid());
    }

    public void deleteSticker(int stickerNumber) {
        FriendshipBook book = getBookForLoggedInUser();
        if (stickerNumber == 1) {
            book.setSticker1(null);
        }
        if (stickerNumber == 2) {
            book.setSticker2(null);
        }
        repository.save(book);
        log.debug("Book sticker of user {} with sticker {} has been deleted", book.getUser().getUsername(), stickerNumber);
    }
}

