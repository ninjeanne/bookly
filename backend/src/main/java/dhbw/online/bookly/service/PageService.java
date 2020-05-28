package dhbw.online.bookly.service;

import dhbw.online.bookly.dto.FriendshipBook;
import dhbw.online.bookly.dto.Page;
import dhbw.online.bookly.dto.PageSticker;
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
    private String updateMessage="Sticker updated for page {}";
    @Autowired
    private FriendshipBookService friendshipBookService;
    @Autowired
    private FriendshipBookRepository friendshipBookRepository;
    @Autowired
    private PageRepository pageRepository;
    @Autowired
    private AuthenticationService authenticationService;


    public List<Page> readPages() {
        return friendshipBookService.getBookForLoggedInUser().getPages();
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

    public Page findPageByUserAndId(String uuid) {
        List<Page> pagesByUser = readPages();

        for (Page page : pagesByUser) {
            if (page.getUuid().equals(uuid)) {
                return page;
            }
        }
        throw new PageException("Page couldn't be found");
    }

    public Page findPageById(String uuid) {
        Optional<Page> page = pageRepository.findByUuid(uuid);
        if (page.isPresent()) {
            return page.get();
        }
        throw new PageException("Page couldn't be found");
    }

    public Page addNewPage() {
        FriendshipBook book = friendshipBookService.getBookForLoggedInUser();
        if (book != null) {
            val pages = book.getPages();
            Page newPage = new Page();
            pages.add(newPage);
            friendshipBookRepository.save(book);
            log.debug("New page has been created for user {} ", authenticationService.getUsername());
            return newPage;
        }
        throw new FriendshipBookException("There is no book for user " + authenticationService.getUsername());
    }

    private Page getPage(String uuid) {
        return pageRepository.findByUuid(uuid).orElse(null);
    }

    public List<Page> delete(String uuid) {
        FriendshipBook book = friendshipBookService.getBookForLoggedInUser();
        if (book != null) {
            val pagesFromDb = book.getPages();
            if (pageRepository.existsByUuid(uuid)) {
                val pageFromDb = getPage(uuid);
                pagesFromDb.remove(pageFromDb);
                friendshipBookRepository.save(book);
                pageRepository.delete(pageFromDb);
                return pagesFromDb;
            }
            throw new FriendshipBookException("There is no page with uuid " + uuid + " for user " + authenticationService.getUsername());
        }
        throw new FriendshipBookException("There is no book for user " + authenticationService.getUsername());
    }

    public void deleteAllPages() {
        FriendshipBook book = friendshipBookService.getBookForLoggedInUser();
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

    private void saveStickerOne(Page page,byte[] data, long size, String contentType) {

        page.setPageStickerOne(PageSticker.builder().data(data).size(size).mediaType(contentType).build());
        pageRepository.save(page);
        log.debug(updateMessage, page.getUuid());
    }

    private void saveStickerTwo(Page page,byte[] data, long size, String contentType) {
        page.setPageStickerTwo(PageSticker.builder().data(data).size(size).mediaType(contentType).build());
        pageRepository.save(page);
        log.debug(updateMessage, page.getUuid());
    }

    private void saveStickerThree(Page page,byte[] data, long size, String contentType) {
        page.setPageStickerThree(PageSticker.builder().data(data).size(size).mediaType(contentType).build());
        pageRepository.save(page);
        log.debug(updateMessage, page.getUuid());
    }

    private void saveStickerFour(Page page,byte[] data, long size, String contentType) {
        page.setPageStickerFour(PageSticker.builder().data(data).size(size).mediaType(contentType).build());
        pageRepository.save(page);
        log.debug(updateMessage, page.getUuid());
    }

    public void deleteSticker(Page page, int stickerNumber) {

        if (stickerNumber == 1) {
            page.setPageStickerOne(null);
        }
        if (stickerNumber == 2) {
            page.setPageStickerTwo(null);
        }
        if (stickerNumber == 3) {
            page.setPageStickerThree(null);
        }
        if (stickerNumber == 4) {
            page.setPageStickerFour(null);
        }
        pageRepository.save(page);
        log.debug("Page sticker of page {} with sticker {} has been deleted",page.getUuid(), stickerNumber);

    }


    public void saveSticker(Page page, MultipartFile file, int stickerNumber) {
        try {
            switch (stickerNumber) {
                case 1:
                    saveStickerOne(page,file.getBytes(), file.getSize(), file.getContentType());
                    break;
                case 2:
                    saveStickerTwo(page,file.getBytes(), file.getSize(), file.getContentType());
                    break;
                case 3:
                    saveStickerThree(page,file.getBytes(), file.getSize(), file.getContentType());
                    break;
                case 4:
                    saveStickerFour(page,file.getBytes(), file.getSize(), file.getContentType());
                    break;
                default:
                    throw new FriendshipBookException("Wrong sticker number");
            }
        } catch (IOException e) {
            throw new FriendshipBookException("Sticker " + stickerNumber + " couldn't be saved.");
        }
    }

}
