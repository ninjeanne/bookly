package dhbw.online.bookly.controller;

import dhbw.online.bookly.dto.Page;
import dhbw.online.bookly.dto.User;
import dhbw.online.bookly.exception.BooklyException;
import dhbw.online.bookly.service.PageService;
import dhbw.online.bookly.service.UserService;
import io.swagger.annotations.*;
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
    @ApiOperation(value = "Add a new page with data into the users friendship book", authorizations = {@Authorization(value = "basicAuth")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - returns list of pages", response = Page.class),
            @ApiResponse(code = 409, message = "Conflict - the content or user couldn't be found"),
            @ApiResponse(code = 401, message = "Unauthorized - the credentials are missing or false"),
    })
    ResponseEntity create() {
        User user = userService.getUser();
        try {
            val pages = pageService.add(user);
            return ResponseEntity.ok(pages);
        } catch (BooklyException fbe) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(fbe.getMessage());
        }
    }

    @GetMapping
    @ApiOperation(value = "Returns explicit all pages of a book", authorizations = {@Authorization(value = "basicAuth")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - returns list of pages", response = Page.class),
            @ApiResponse(code = 409, message = "Conflict - the content or user couldn't be found"),
            @ApiResponse(code = 401, message = "Unauthorized - the credentials are missing or false"),
    })
    ResponseEntity read() {
        User user = userService.getUser();
        val pages = pageService.read(user);
        if (pages != null)
            return ResponseEntity.ok(pages);
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @PutMapping
    @ApiOperation(value = "Update a page. It's important to send the uuid of the page for this request.", authorizations = {@Authorization(value = "basicAuth")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - returns list of the current pages", response = Page.class),
            @ApiResponse(code = 409, message = "Conflict - the updatable page or user couldn't be found"),
            @ApiResponse(code = 401, message = "Unauthorized - the credentials are missing or false"),
    })
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
    @ApiOperation(value = "Delete a page. It's important to send the uuid of the page for this request.", authorizations = {@Authorization(value = "basicAuth")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - returns list of the current pages", response = Page.class),
            @ApiResponse(code = 409, message = "Conflict - the updatable page or user couldn't be found and deleted"),
            @ApiResponse(code = 401, message = "Unauthorized - the credentials are missing or false"),
    })
    ResponseEntity delete(@RequestParam @ApiParam(value = "The uuid of the page that shall be deleted", required = true, example = "2") String uuid) {
        User user = userService.getUser();
        if (uuid != null) {
            try {
                int uuidNumb = Integer.parseInt(uuid);
                val pages = pageService.delete(user, uuidNumb);
                return ResponseEntity.ok(pages);
            } catch (NumberFormatException | BooklyException fbe) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(fbe.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

}
