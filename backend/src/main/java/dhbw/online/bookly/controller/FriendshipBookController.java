package dhbw.online.bookly.controller;

import dhbw.online.bookly.dto.FriendshipBook;
import dhbw.online.bookly.exception.BooklyException;
import dhbw.online.bookly.exception.FriendshipBookException;
import dhbw.online.bookly.service.FriendshipBookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@CrossOrigin
@RestController
@Slf4j
@RequestMapping("/api/friendshipbook")
public class FriendshipBookController extends Controller {
    @Autowired
    private FriendshipBookService bookService;

    @GetMapping
    @ApiOperation(value = "Return the whole book (including all pages) of the logged in user")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success - returns the book for the logged in user", response = FriendshipBook.class),
            @ApiResponse(code = 409, message = "Conflict - the content or user couldn't be found"),
            @ApiResponse(code = 401, message = "Unauthorized - the credentials are missing or false"), })
    ResponseEntity read() {
        if (existsUser()) {
            FriendshipBook book = bookService.read();
            if (existsBook(book)) {
                return ResponseEntity.ok(book);
            }
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @GetMapping(value = "/image")
    @ApiOperation(value = "Returns cover image of the book of the logged in user")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success - returns the cover image of the book of the logged in user, returns byte array",
            response = byte[].class), @ApiResponse(code = 404, message = "Not found - the image doesn't exist"),
            @ApiResponse(code = 401, message = "Unauthorized - the credentials are missing or false"), })
    public ResponseEntity<String> getImage() {
        FriendshipBook book = bookService.read();
        if (existsCover(book)) {
            return ResponseEntity.ok().contentType(MediaType.valueOf(book.getCover().getMediaType())).body(encodeBase64(book.getCover().getData()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/image")
    @ResponseBody
    @ApiOperation(value = "Send a new image as cover for the book of the logged in user")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 409, message = "Conflict - the saving of the data failed maybe there was corrupted data"),
            @ApiResponse(code = 401, message = "Unauthorized - the credentials are missing or false"), })
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            if (file == null) {
                throw new FriendshipBookException("There was no picture in the request for saving.");
            }
            FriendshipBook book = bookService.read();
            if (existsUser() && existsBook(book)) {
                bookService.saveImageForBook(book, file);
            }
        } catch (BooklyException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    @ApiOperation(value = "Update the cover title of the book of the logged in user")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success - returns the updated book of the logged in user", response = FriendshipBook.class),
            @ApiResponse(code = 409, message = "Conflict - the content couldn't be updated"),
            @ApiResponse(code = 401, message = "Unauthorized - the credentials are missing or false"), })
    ResponseEntity update(@RequestParam @ApiParam(value = "New book cover title", example = "My super duper fancy friendship book") String title) {
        if (title == null) {
            log.warn("Couldn't update book title with empty title");
            return ResponseEntity.noContent().build();
        }
        if (existsUser()) {
            try {
                FriendshipBook book = bookService.updateTitle(title);
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

