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

    public boolean create(User user) {
        if (!repository.existsByUser(user)) {
            repository.save(FriendshipBook.builder()
                    .user(user)
                    .title("My Friendship Book")
                    .pages(new ArrayList<>())
                    .build());
            return true;
        }
        return false;
    }

    public void saveImageForBook(FriendshipBook friendshipBook, MultipartFile file) {
        try {
           saveImageForBook(friendshipBook, file.getBytes(), file.getSize(), file.getContentType());
            repository.save(friendshipBook);
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
        } catch (NullPointerException e) {
            throw new FriendshipBookException("Image couldn't be saved.");
        }
    }

    @Nullable
    public FriendshipBook read(User user) {
        Optional<FriendshipBook> book = repository.findByUser(user);
        return book.orElse(null);
    }

    public boolean delete(User user) {
        FriendshipBook book = read(user);
        if (book != null) {
            repository.delete(book);
            return true;
        }
        return false;
    }

    public FriendshipBook updateTitle(User user, String title) {
        FriendshipBook book = read(user);
        if (book != null) {
            book.setTitle(title);
            return repository.save(book);
        }
        throw new FriendshipBookException("There is no book for user " + user.getUsername());
    }
}
