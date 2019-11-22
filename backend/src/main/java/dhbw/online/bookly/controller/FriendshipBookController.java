package dhbw.online.bookly.controller;

import dhbw.online.bookly.dto.FriendshipBook;
import dhbw.online.bookly.dto.User;
import dhbw.online.bookly.exception.BooklyException;
import dhbw.online.bookly.service.FriendshipBookService;
import dhbw.online.bookly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/friendshipbook")
public class FriendshipBookController {

    @Autowired
    private FriendshipBookService bookService;

    @Autowired
    private UserService userService;

    @GetMapping
    ResponseEntity read() {
        User user = userService.getUser();
        FriendshipBook book = bookService.read(user);
        if (book != null)
            return ResponseEntity.ok(book);
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @PutMapping
    ResponseEntity update(@RequestParam String title) {
        if (title == null) {
            return ResponseEntity.noContent().build();
        }
        User user = userService.getUser();
        try {
            FriendshipBook book = bookService.updateTitle(user, title);
            return ResponseEntity.ok(book);
        } catch (BooklyException fbe) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(fbe.getMessage());
        }
    }

}
