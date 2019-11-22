package dhbw.online.bookly.service;

import dhbw.online.bookly.dto.FriendshipBook;
import dhbw.online.bookly.dto.Page;
import dhbw.online.bookly.dto.User;
import dhbw.online.bookly.exception.FriendshipBookException;
import dhbw.online.bookly.exception.PageException;
import dhbw.online.bookly.repository.FriendshipBookRepository;
import dhbw.online.bookly.repository.PageRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FriendshipBookService {
    @Autowired
    private FriendshipBookRepository repository;
    @Autowired
    private PageRepository pageRepository;

    public boolean create(User user) {
        if (!repository.existsByUser(user)) {
            repository.save(FriendshipBook.builder()
                    .user(user)
                    .title("My Friendship Book")
                    .pages(new ArrayList<>())
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

    public FriendshipBook updateTitle(User user, String title) {
        FriendshipBook book = read(user);
        if (book != null) {
            book.setTitle(title);
            return repository.save(book);
        }
        throw new FriendshipBookException("There is no book for user " + user.getUsername());
    }

    public FriendshipBook addPage(User user, Page page) {
        FriendshipBook book = read(user);
        if (book != null) {
            val pages = book.getPages();
            page.setUuid(UUID.randomUUID().toString());
            setMissingUuidsToPageEntries(page);
            pages.add(page);
            return repository.save(book);
        }
        throw new FriendshipBookException("There is no book for user " + user.getUsername());
    }

    private Page getPage(String uuid) {
        return pageRepository.findByUuid(uuid).orElse(null);
    }

    public FriendshipBook deletePage(User user, Page page) {
        FriendshipBook book = read(user);
        if (book != null) {
            val pagesFromDb = book.getPages();
            if (pageRepository.existsByUuid(page.getUuid())) {
                val pageFromDb = getPage(page.getUuid());
                pagesFromDb.remove(pageFromDb);
                return repository.save(book);
            }
        }
        throw new FriendshipBookException("There is no book for user " + user.getUsername());
    }

    public FriendshipBook updatePage(User user, Page page) {
        FriendshipBook book = read(user);
        if (book != null) {
            val pages = book.getPages();
            val resultpages = pages.stream()
                    .filter(pageFromDb -> pageFromDb.getUuid().equals(page.getUuid()))
                    .collect(Collectors.toList());
            if (resultpages.isEmpty()) {
                throw new PageException("Requested page with the id " + page.getUuid() + " does not exist! ");
            }
            setMissingUuidsToPageEntries(page);
            pages.remove(resultpages.get(0));
            pages.add(page);
            return repository.save(book);
        }
        throw new FriendshipBookException("There is no book for user " + user.getUsername());
    }

    private void setMissingUuidsToPageEntries(Page page) {
        if (page.getEntries().isEmpty()) {
            page.setEntries(new ArrayList<>());
        }
        page.getEntries().stream()
                .filter(pageEntry -> pageEntry.getUuid() == null)
                .forEach(pageEntry -> pageEntry.setUuid(UUID.randomUUID().toString()));
    }
}
