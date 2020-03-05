package dhbw.online.bookly.controller;

import dhbw.online.bookly.dto.FriendshipBook;
import dhbw.online.bookly.dto.User;
import dhbw.online.bookly.exception.BooklyException;
import dhbw.online.bookly.exception.FriendshipBookException;
import dhbw.online.bookly.service.FriendshipBookService;
import dhbw.online.bookly.service.UserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Base64;

@RestController
@Slf4j
@RequestMapping("/api/friendshipbook")
public class FriendshipBookController extends Controller {

    @Autowired
    private FriendshipBookService bookService;

    @Autowired
    private UserService userService;

    @GetMapping()
    @ApiOperation(value = "Returns the data of the book of a user including all of his pages", authorizations = { @Authorization(value = "basicAuth") })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success - returns the book for the logged in user", response = FriendshipBook.class),
            @ApiResponse(code = 409, message = "Conflict - the content or user couldn't be found"),
            @ApiResponse(code = 401, message = "Unauthorized - the credentials are missing or false"), })
    ResponseEntity read(Principal principal) {
        System.err.println(principal.getName());
        User user = userService.getUser();
        if (existsUser(user)) {
            FriendshipBook book = bookService.read(user);
            if (existsBook(book)) {
                return ResponseEntity.ok(book);
            }
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @GetMapping(value = "/image")
    @ApiOperation(value = "Returns cover image of a book", authorizations = {@Authorization(value = "basicAuth")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - returns the cover image of the book of the logged in user, returns byte array", response = byte[].class),
            @ApiResponse(code = 404, message = "Not found - the image doesn't exist"),
            @ApiResponse(code = 401, message = "Unauthorized - the credentials are missing or false"),
    })
    public ResponseEntity<String> getImage() {
        User user = userService.getUser();
        FriendshipBook book = bookService.read(user);
        if (existsCover(book)) {
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.valueOf(book.getCover().getMediaType()))
                    .body(encodeBase64(book.getCover().getData()));
        } else {
            return ResponseEntity
                    .notFound().build();
        }
    }

    @CrossOrigin
    @PostMapping(value = "/image")
    @ResponseBody
    @ApiOperation(value = "Send a new image as cover for the book of the logged in user", authorizations = {@Authorization(value = "basicAuth")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 409, message = "Conflict - the saving of the data failed maybe there was corrupted data"),
            @ApiResponse(code = 401, message = "Unauthorized - the credentials are missing or false"),
    })
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile file) {

        try {
            if (file == null) {
                throw new FriendshipBookException("There was no picture in the request for saving.");
            }
            User user = userService.getUser();
            FriendshipBook book = bookService.read(user);
            if (existsUser(user) && existsBook(book)) {
                bookService.saveImageForBook(book, file);
            }
        } catch (BooklyException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping
    @ApiOperation(value = "Update the cover title of the friendship book", authorizations = {@Authorization(value = "basicAuth")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - returns the updated book of the logged in user", response = FriendshipBook.class),
            @ApiResponse(code = 409, message = "Conflict - the content couldn't be updated"),
            @ApiResponse(code = 401, message = "Unauthorized - the credentials are missing or false"),
    })
    ResponseEntity update(@RequestParam @ApiParam(value = "New book cover title", example = "My super duper fancy friendship book") String title) {
        if (title == null) {
            log.warn("Couldn't update book title with empty title");
            return ResponseEntity.noContent().build();
        }
        User user = userService.getUser();
        if (existsUser(user)) {
            try {
                FriendshipBook book = bookService.updateTitle(user, title);
                return ResponseEntity.ok(book);
            } catch (BooklyException fbe) {
                log.warn(fbe.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    private String encodeBase64(byte[] data) {
        byte[] encoded = Base64.getEncoder().encode(data);
        return new String(encoded);
    }
}
