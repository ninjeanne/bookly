package dhbw.online.bookly.controller;

import dhbw.online.bookly.dto.FriendshipBook;
import dhbw.online.bookly.dto.Page;
import dhbw.online.bookly.dto.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Controller {

    protected boolean existsBook(FriendshipBook book) {
        if (book != null) {
            log.debug("Book exists for user {} and with uuid {}", book.getUser().getUsername(), book.getUuid());
            return true;
        } else {
            log.warn("Book doesn't exist!");
            return false;
        }
    }

    protected boolean existsPage(Page page) {
        if (page != null) {
            log.debug("Page exists for this book with uuid {}", page.getUuid());
            return true;
        } else {
            log.warn("Page doesn't exist!");
            return false;
        }
    }

    protected boolean existsCover(FriendshipBook book) {
        if (existsBook(book) && book.getCover() != null) {
            log.debug("Book cover exists for user {} and with uuid {}", book.getUser().getUsername(), book.getUuid());
            return true;
        }
        log.info("Book cover doesn't exist for user {} and with uuid {}", book.getUser().getUsername(), book.getUuid());
        return false;
    }

    protected boolean existsPageImage(Page page) {
        if (existsPage(page) && page.getPageImage() != null) {
            log.debug("Page image exists for page with uuid {}", page.getUuid());
            return true;
        }
        log.info("Page image doesn't exist for page with uuid {}", page.getUuid());
        return false;
    }

    protected boolean existsUser(User user) {
        if (user != null) {
            return true;
        } else {
            log.warn("User is null!");
            return false;
        }
    }

}
