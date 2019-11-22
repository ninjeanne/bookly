package dhbw.online.bookly.controller;

import dhbw.online.bookly.dto.FriendshipBook;
import dhbw.online.bookly.dto.User;
import dhbw.online.bookly.exception.FriendshipBookException;
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
        if (user != null) {
            FriendshipBook book = bookService.read(user);
            if (book != null)
                return ResponseEntity.ok(book);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PutMapping
    ResponseEntity update(@RequestParam String title) {
        if (title == null) {
            return ResponseEntity.noContent().build();
        }
        User user = userService.getUser();
        if (user != null) {
            try {
                FriendshipBook book = bookService.updateTitle(user, title);
                return ResponseEntity.ok(book);

            } catch (FriendshipBookException fbe) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(fbe.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
