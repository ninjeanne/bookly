package dhbw.online.bookly.service;

import dhbw.online.bookly.dto.FriendshipBook;
import dhbw.online.bookly.dto.Page;
import dhbw.online.bookly.dto.PageImage;
import dhbw.online.bookly.exception.FriendshipBookException;
import dhbw.online.bookly.exception.PageException;
import dhbw.online.bookly.repository.FriendshipBookRepository;
import dhbw.online.bookly.repository.PageRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PageService {
    @Autowired
    private FriendshipBookService friendshipBookService;
    @Autowired
    private FriendshipBookRepository friendshipBookRepository;
    @Autowired
    private PageRepository pageRepository;
    @Autowired
    private AuthenticationService authenticationService;

    public List<Page> read() {
        return friendshipBookService.read().getPages();
    }

    public void saveImageForPage(Page page, MultipartFile file) {
        try {
            saveImageForPage(page, file.getBytes(), file.getSize(), file.getContentType());
        } catch (NullPointerException | IOException e) {
            throw new FriendshipBookException("Image couldn't be saved.");
        }
    }

    public void saveImageForPage(Page page, byte[] data, long size, String contentType) {
        try {
            page.setPageImage(PageImage.builder().data(data).size(size).mediaType(contentType).build());
            log.debug("Page image has been set for page with uuid {}", page.getUuid());
            pageRepository.save(page);
        } catch (NullPointerException e) {
            throw new FriendshipBookException("Image couldn't be saved.");
        }
    }

    public Page findPageByUserAndId(int uuid) {
        List<Page> pagesByUser = read();

        for (Page page : pagesByUser) {
            if (page.getUuid() == uuid) {
                return page;
            }
        }
        throw new PageException("Page couldn't be found");
    }

    public Page findPageById(int uuid) {
        Optional<Page> page = pageRepository.findByUuid(uuid);
        if (page.isPresent()) {
            return page.get();
        }
        throw new PageException("Page couldn't be found");
    }

    public Page add() {
        FriendshipBook book = friendshipBookService.read();
        if (book != null) {
            val pages = book.getPages();
            val newPage = new Page();
            pages.add(newPage);
            pageRepository.save(newPage);
            friendshipBookRepository.save(book);
            log.debug("New page has been created for user {} ", authenticationService.getUsername());
            return newPage;
        }
        throw new FriendshipBookException("There is no book for user " + authenticationService.getUsername());
    }

    private Page getPage(int uuid) {
        return pageRepository.findByUuid(uuid).orElse(null);
    }

    public List<Page> delete(int uuid) {
        FriendshipBook book = friendshipBookService.read();
        if (book != null) {
            val pagesFromDb = book.getPages();
            if (pageRepository.existsByUuid(uuid)) {
                val pageFromDb = getPage(uuid);
                pagesFromDb.remove(pageFromDb);
                friendshipBookRepository.save(book);
                return pagesFromDb;
            }
            throw new FriendshipBookException("There is no page with uuid "+uuid+" for user " + authenticationService.getUsername());
        }
        throw new FriendshipBookException("There is no book for user " + authenticationService.getUsername());
    }

    public void deleteAllPages() {
        FriendshipBook book = friendshipBookService.read();
        if (book != null) {
            pageRepository.deleteAll(book.getPages());
            log.debug("all pages for user {} have been deleted", book.getUser().getUsername());

            book.setPages(new ArrayList<>());
            friendshipBookRepository.save(book);
            log.debug("Book for user {} has been updated", book.getUser().getUsername());
            return;
        }
        throw new FriendshipBookException("There is no book for user " + authenticationService.getUsername());
    }

    public boolean update(Page apiPage) {
        if (pageRepository.existsByUuid(apiPage.getUuid())) {
            pageRepository.save(apiPage);
            log.debug("updated page with uuid {}", apiPage.getUuid());
            return true;
        }
        return false;
    }

}
