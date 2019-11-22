package dhbw.online.bookly.controller;

import dhbw.online.bookly.dto.FriendshipBook;
import dhbw.online.bookly.dto.Page;
import dhbw.online.bookly.dto.User;
import dhbw.online.bookly.exception.FriendshipBookException;
import dhbw.online.bookly.exception.PageException;
import dhbw.online.bookly.service.FriendshipBookService;
import dhbw.online.bookly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/page")
public class PageController {

    @Autowired
    private FriendshipBookService bookService;

    @Autowired
    private UserService userService;

    @PostMapping
    ResponseEntity create(@RequestBody Page page) {
        User user = userService.getUser();
        if (user != null && page != null) {
            try {
                FriendshipBook book = bookService.addPage(user, page);
                return ResponseEntity.ok(book.getPages());
            } catch (FriendshipBookException | PageException fbe) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(fbe.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping
    ResponseEntity read() {
        User user = userService.getUser();
        if (user != null) {
            FriendshipBook book = bookService.read(user);
            if (book != null)
                return ResponseEntity.ok(book.getPages());
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PutMapping
    ResponseEntity update(@RequestBody Page page) {
        User user = userService.getUser();
        if (user != null) {
            try {
                FriendshipBook book = bookService.updatePage(user, page);
                return ResponseEntity.ok(book.getPages());
            } catch (FriendshipBookException | PageException fbe) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(fbe.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @DeleteMapping
    ResponseEntity delete(@RequestBody Page page) {
        User user = userService.getUser();
        if (user != null && page != null) {
            try {
                FriendshipBook book = bookService.deletePage(user, page);
                return ResponseEntity.ok(book.getPages());
            } catch (FriendshipBookException | PageException fbe) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(fbe.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
