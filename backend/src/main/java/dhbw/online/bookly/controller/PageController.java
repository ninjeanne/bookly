package dhbw.online.bookly.controller;

import dhbw.online.bookly.dto.Page;
import dhbw.online.bookly.dto.User;
import dhbw.online.bookly.exception.BooklyException;
import dhbw.online.bookly.exception.FriendshipBookException;
import dhbw.online.bookly.exception.PageException;
import dhbw.online.bookly.exception.UserException;
import dhbw.online.bookly.service.PageService;
import dhbw.online.bookly.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
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
    @ApiOperation(value= "Add a new page with data into the users friendship book", authorizations = {@Authorization(value="basicAuth")})
    ResponseEntity create(@RequestBody Page page) {
        User user = userService.getUser();
        if (page != null) {
            try {
                val pages = pageService.add(user, page);
                return ResponseEntity.ok(pages);
            } catch (BooklyException fbe) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(fbe.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @GetMapping
    @ApiOperation(value= "Returns explicit all pages of a book", authorizations = {@Authorization(value="basicAuth")})
    ResponseEntity read() {
        User user = userService.getUser();
        val pages = pageService.read(user);
        if (pages != null)
            return ResponseEntity.ok(pages);
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @PutMapping
    @ApiOperation(value= "Update a page. It's important to send the uuid of the page for this request.", authorizations = {@Authorization(value="basicAuth")})
    ResponseEntity update(@RequestBody Page page) {
        User user = userService.getUser();
        if (page != null) {
            try {
                val pages = pageService.update(user, page);
                return ResponseEntity.ok(pages);
            } catch (BooklyException fbe) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(fbe.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @DeleteMapping
    @ApiOperation(value= "Delete a page. It's important to send the uuid of the page for this request.", authorizations = {@Authorization(value="basicAuth")})
    ResponseEntity delete(@RequestBody Page page) {
        User user = userService.getUser();
        if (page != null) {
            try {
                val pages = pageService.delete(user, page);
                return ResponseEntity.ok(pages);
            } catch (BooklyException fbe) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(fbe.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

}
