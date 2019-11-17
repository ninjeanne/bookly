package dhbw.online.bookly.service;

import dhbw.online.bookly.dto.FriendshipBook;
import dhbw.online.bookly.dto.Page;
import dhbw.online.bookly.dto.User;
import dhbw.online.bookly.repository.FriendshipBookRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class FriendshipBookService {
    @Autowired
    private FriendshipBookRepository repository;

    public boolean create(User user) {
        if (!repository.existsByUser(user)) {
            repository.save(FriendshipBook.builder()
                    .user(user)
                    .pages(Collections.emptyList())
                    .uuid(UUID.randomUUID().toString())
                    .build());
            return true;
        }
        return false;
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

    public boolean updateTitle(User user, String title) {
        FriendshipBook book = read(user);
        if (book != null) {
            book.setTitle(title);
            repository.save(book);
            return true;
        }
        return false;
    }

    public boolean addPage(User user, Page page) {
        FriendshipBook book = read(user);
        if (book != null) {
            val pages = book.getPages();
            pages.add(page);
            repository.save(book);
            return true;
        }
        return false;
    }
}
