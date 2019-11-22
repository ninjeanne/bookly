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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PageService {
    @Autowired
    private FriendshipBookService friendshipBookService;
    @Autowired
    private FriendshipBookRepository friendshipBookRepository;
    @Autowired
    private PageRepository pageRepository;

    public List<Page> read(User user) {
        return friendshipBookService.read(user).getPages();
    }

    public List<Page> add(User user, Page page) {
        FriendshipBook book = friendshipBookService.read(user);
        if (book != null) {
            val pages = book.getPages();
            page.setUuid(UUID.randomUUID().toString());
            setMissingUuidsToPageEntries(page);
            pages.add(page);
            friendshipBookRepository.save(book);
            return pages;
        }
        throw new FriendshipBookException("There is no book for user " + user.getUsername());
    }

    private Page getPage(String uuid) {
        return pageRepository.findByUuid(uuid).orElse(null);
    }

    public List<Page> delete(User user, Page page) {
        FriendshipBook book = friendshipBookService.read(user);
        if (book != null) {
            val pagesFromDb = book.getPages();
            if (pageRepository.existsByUuid(page.getUuid())) {
                val pageFromDb = getPage(page.getUuid());
                pagesFromDb.remove(pageFromDb);
                friendshipBookRepository.save(book);
                return pagesFromDb;
            }
        }
        throw new FriendshipBookException("There is no book for user " + user.getUsername());
    }

    public List<Page> update(User user, Page page) {
        FriendshipBook book = friendshipBookService.read(user);
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
            friendshipBookRepository.save(book);
            return pages;
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
