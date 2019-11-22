package dhbw.online.bookly.controller;

import dhbw.online.bookly.dto.Page;
import dhbw.online.bookly.dto.User;
import dhbw.online.bookly.exception.FriendshipBookException;
import dhbw.online.bookly.exception.PageException;
import dhbw.online.bookly.service.PageService;
import dhbw.online.bookly.service.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/page")
public class PageController {

    @Autowired
    private PageService pageService;

    @Autowired
    private UserService userService;

    @PostMapping
    ResponseEntity create(@RequestBody Page page) {
        User user = userService.getUser();
        if (user != null && page != null) {
            try {
                val pages = pageService.add(user, page);
                return ResponseEntity.ok(pages);
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
            val pages = pageService.read(user);
            if (pages != null)
                return ResponseEntity.ok(pages);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PutMapping
    ResponseEntity update(@RequestBody Page page) {
        User user = userService.getUser();
        if (user != null) {
            try {
                val pages = pageService.update(user, page);
                return ResponseEntity.ok(pages);
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
                val pages = pageService.delete(user, page);
                return ResponseEntity.ok(pages);
            } catch (FriendshipBookException | PageException fbe) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(fbe.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
