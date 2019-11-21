package dhbw.online.bookly.controller;

import dhbw.online.bookly.dto.FriendshipBook;
import dhbw.online.bookly.dto.User;
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

    @PostMapping
    ResponseEntity create() {
        User user = userService.getUser();
        if (user != null) {
            boolean status = bookService.create(user);
            if (status) {
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
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
            boolean status = bookService.updateTitle(user, title);
            if (status) {
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @DeleteMapping
    ResponseEntity delete() {
        User user = userService.getUser();
        if (user != null) {
            boolean status = bookService.delete(user);
            if (status) {
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
