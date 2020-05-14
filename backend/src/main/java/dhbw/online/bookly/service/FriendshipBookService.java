package dhbw.online.bookly.service;

import dhbw.online.bookly.dto.FriendshipBook;
import dhbw.online.bookly.dto.FriendshipBookCover;
import dhbw.online.bookly.dto.FriendshipBookSticker;
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

    public boolean exists() {
        User user = authenticationService.getUser();
        boolean exists = repository.existsByUser(user);
        if (!exists) {
            log.warn("Friendship book for user {} does not exist!", user.getUsername());
        }
        return exists;
    }

    public boolean create() {
        User user = authenticationService.getUser();
        if (!exists()) {
            repository.save(FriendshipBook.builder().user(user).title("My Friendship Book").pages(new ArrayList<>()).build());
            log.debug("Book created for user {}", user.getUsername());
            return true;
        }
        log.debug("Book for user {} does already exist.", user.getUsername());
        return false;
    }

    public void saveCover(MultipartFile file) {
        try {
            saveCover(file.getBytes(), file.getSize(), file.getContentType());
        } catch (NullPointerException | IOException e) {
            throw new FriendshipBookException("Image couldn't be saved.");
        }
    }

    public void saveCover(byte[] data, long size, String contentType) {
        try {
            FriendshipBook friendshipBook = read();
            friendshipBook.setCover(FriendshipBookCover.builder().data(data).size(size).mediaType(contentType).build());
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
        FriendshipBook book = read();
        if (book != null) {
            repository.delete(book);
            log.debug("Book of user {} with book id {} has been deleted", book.getUser().getUsername(), book.getUuid());
            return true;
        }
        log.warn("Book for user {} doesn't exist. Has it already been deleted?", book.getUser().getUsername());
        return false;
    }

    public boolean deleteCover() {
        User user = userService.getUser();
        FriendshipBook book = read();
        if (book != null) {
            book.setCover(null);
            repository.save(book);
            log.debug("Book cover of user {} with book id {} has been deleted", user.getUsername(), book.getUuid());
            return true;
        }
        log.error("Book for user {} doesn't exist. ", user.getUsername());
        return false;
    }

    public FriendshipBook updateTitle(String title, String subtitle) {
        FriendshipBook book = read();
        if (book != null) {
            book.setTitle(title);
            book.setSubtitle(subtitle);
            repository.save(book);
            log.debug("Book titles of book from user {} have been updated", book.getUser().getUsername());
            return book;
        }
        throw new FriendshipBookException("There is no book for user " + book.getUser().getUsername());
    }

    public FriendshipBook updateTheme(int theme) {
        FriendshipBook book = read();
        if (book != null) {
            book.setTheme(theme);
            repository.save(book);
            log.debug("Cover theme from user {} has been updated", book.getUser().getUsername());
            return book;
        }
        throw new FriendshipBookException("There is no book for user " + book.getUser().getUsername());
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
        FriendshipBook friendshipBook = read();
        friendshipBook.setSticker1(FriendshipBookSticker.builder().data(data).size(size).mediaType(contentType).build());
        repository.save(friendshipBook);
        log.debug("Sticker1 updated for user {} and its book id {}", friendshipBook.getUser().getUsername(), friendshipBook.getUuid());
    }

    private void saveSticker2(byte[] data, long size, String contentType) {
        FriendshipBook friendshipBook = read();
        friendshipBook.setSticker2(FriendshipBookSticker.builder().data(data).size(size).mediaType(contentType).build());
        repository.save(friendshipBook);
        log.debug("Sticker2 updated for user {} and its book id {}", friendshipBook.getUser().getUsername(), friendshipBook.getUuid());
    }

    public void deleteSticker(int stickerNumber) {
        FriendshipBook book = read();
        if (book != null) {
            if(stickerNumber == 1){
                book.setSticker1(null);
            }
            if(stickerNumber == 2){
                book.setSticker2(null);
            }
            repository.save(book);
            log.debug("Book sticker of user {} with sticker {} has been deleted", book.getUser().getUsername(), stickerNumber);
        }
        log.error("Book for user {} doesn't exist. ", book.getUser().getUsername());
        throw new FriendshipBookException("Book for user " + book.getUser().getUsername() + " doesn't exist. ");
    }
}
