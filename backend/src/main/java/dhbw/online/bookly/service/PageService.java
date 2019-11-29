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

import java.util.List;
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

    public List<Page> add(User user) {
        FriendshipBook book = friendshipBookService.read(user);
        if (book != null) {
            val pages = book.getPages();
            val newPage = new Page();
            pages.add(newPage);
            pageRepository.save(newPage);
            friendshipBookRepository.save(book);
            return pages;
        }
        throw new FriendshipBookException("There is no book for user " + user.getUsername());
    }

    private Page getPage(int uuid) {
        return pageRepository.findByUuid(uuid).orElse(null);
    }

    public List<Page> delete(User user, int uuid) {
        FriendshipBook book = friendshipBookService.read(user);
        if (book != null) {
            val pagesFromDb = book.getPages();
            if (pageRepository.existsByUuid(uuid)) {
                val pageFromDb = getPage(uuid);
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
                    .filter(pageFromDb -> pageFromDb.getUuid()==page.getUuid())
                    .collect(Collectors.toList());
            if (resultpages.isEmpty()) {
                throw new PageException("Requested page with the id " + page.getUuid() + " does not exist! ");
            }
            pages.remove(resultpages.get(0));
            pages.add(page);
            friendshipBookRepository.save(book);
            return pages;
        }
        throw new FriendshipBookException("There is no book for user " + user.getUsername());
    }

}