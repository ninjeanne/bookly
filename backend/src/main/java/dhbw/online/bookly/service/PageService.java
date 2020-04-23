package dhbw.online.bookly.service;

import dhbw.online.bookly.dto.FriendshipBook;
import dhbw.online.bookly.dto.Page;
import dhbw.online.bookly.dto.PageImage;
import dhbw.online.bookly.dto.User;
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
            page.setPageImage(PageImage.builder()
                    .data(data)
                    .size(size)
                    .mediaType(contentType)
                    .build());
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

    public List<Page> add() {
        FriendshipBook book = friendshipBookService.read();
        if (book != null) {
            val pages = book.getPages();
            val newPage = new Page();
            pages.add(newPage);
            pageRepository.save(newPage);
            friendshipBookRepository.save(book);
            log.debug("New page has been created for user {} ", authenticationService.getUsername());
            return pages;
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
        }
        throw new FriendshipBookException("There is no book for user " + authenticationService.getUsername());
    }

    public List<Page> update(Page page) {
        FriendshipBook book = friendshipBookService.read();
        if (book != null) {
            val pages = book.getPages();
            val resultpages = pages.stream()
                    .filter(pageFromDb -> pageFromDb.getUuid() == page.getUuid())
                    .collect(Collectors.toList());
            if (resultpages.isEmpty()) {
                throw new PageException("Requested page with the id " + page.getUuid() + " does not exist! ");
            }
            pages.remove(resultpages.get(0));
            pages.add(page);
            friendshipBookRepository.save(book);
            return pages;
        }
        throw new FriendshipBookException("There is no book for user " + authenticationService.getUsername());
    }

}
