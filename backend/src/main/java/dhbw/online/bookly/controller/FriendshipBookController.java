package dhbw.online.bookly.controller;

import dhbw.online.bookly.dto.FriendshipBook;
import dhbw.online.bookly.dto.User;
import dhbw.online.bookly.exception.BooklyException;
import dhbw.online.bookly.service.FriendshipBookService;
import dhbw.online.bookly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.IOException;

@RestController
@RequestMapping("/api/friendshipbook")
public class FriendshipBookController {

    @Autowired
    private FriendshipBookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private ServletContext servletContext;

    @GetMapping
    ResponseEntity read() {
        User user = userService.getUser();
        FriendshipBook book = bookService.read(user);
        if (book != null)
            return ResponseEntity.ok(book);
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @RequestMapping(value = "/image", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage() throws IOException {
        User user = userService.getUser();
        FriendshipBook book = bookService.read(user);
        if (book != null && book.getCover() != null) {
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.valueOf(book.getCover().getMediaType()))
                    .body(book.getCover().getData());
        } else {
            return ResponseEntity
                    .notFound().build();
        }
    }

    @RequestMapping(value = "/image", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile file) {

        try {
            User user = userService.getUser();
            FriendshipBook book = bookService.read(user);
            bookService.saveImageForBook(book, file);
        } catch (BooklyException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(HttpStatus.OK);
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
