package dhbw.online.bookly.controller;

import dhbw.online.bookly.dto.FriendshipBook;
import dhbw.online.bookly.dto.User;
import dhbw.online.bookly.exception.BooklyException;
import dhbw.online.bookly.exception.FriendshipBookException;
import dhbw.online.bookly.service.FriendshipBookService;
import dhbw.online.bookly.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
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

    @GetMapping()
    @ApiOperation(value= "Returns the data of the book of a user including all of his pages", authorizations = {@Authorization(value="basicAuth")})
    ResponseEntity read() {
        User user = userService.getUser();
        FriendshipBook book = bookService.read(user);
        if (book != null)
            return ResponseEntity.ok(book);
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @GetMapping(value = "/image",
            produces = MediaType.IMAGE_JPEG_VALUE)
    @ApiOperation(value= "Returns cover image of a book", authorizations = {@Authorization(value="basicAuth")})
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

    @PostMapping(value = "/image")
    @ResponseBody
    @ApiOperation(value= "Send a new image as cover for the book of the logged in user", authorizations = {@Authorization(value="basicAuth")})
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile file) {

        try {
            if(file==null){
                throw new FriendshipBookException("There was no picture in the request for saving.");
            }
            User user = userService.getUser();
            FriendshipBook book = bookService.read(user);
            bookService.saveImageForBook(book, file);
        } catch (BooklyException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    @ApiOperation(value= "Update the cover title of the friendship book", authorizations = {@Authorization(value="basicAuth")})
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
