

package dhbw.online.bookly.controller;

import dhbw.online.bookly.dto.Page;
import dhbw.online.bookly.exception.AuthenticationException;
import dhbw.online.bookly.exception.BooklyException;
import dhbw.online.bookly.service.PageService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api/page")
public class PagesController extends Controller {
    @Autowired
    private PageService pageService;

    @PostMapping
    @ApiOperation(value = "Add a new page with data into the users friendship book and returns it")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success - returns list of pages", response = Page.class),
            @ApiResponse(code = 409, message = "Conflict - the content or user couldn't be found"),
            @ApiResponse(code = 401, message = "Unauthorized - the credentials are missing or false"), })
    ResponseEntity create() {
        if (existsUser()) {
            try {
                val newpage = pageService.add();
                return ResponseEntity.ok(newpage);
            } catch (BooklyException fbe) {
                log.warn(fbe.getMessage());
            } catch (AuthenticationException ae) {
                log.warn("Authentication error for user {}", ae.getPrincipal());
                log.warn(ae.getMessage());
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @GetMapping
    @ApiOperation(value = "Returns explicit all pages of a book")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success - returns list of pages", response = Page.class),
            @ApiResponse(code = 409, message = "Conflict - the content or user couldn't be found"),
            @ApiResponse(code = 401, message = "Unauthorized - the credentials are missing or false"), })
    ResponseEntity read() {
        if (existsUser()) {
            val pages = pageService.read();
            return ResponseEntity.ok(pages);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @DeleteMapping
    @ApiOperation(value = "Delete a page. It's important to send the uuid of the page for this request.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success - returns list of the current pages", response = Page.class),
            @ApiResponse(code = 409, message = "Conflict - the updatable page or user couldn't be found and deleted"),
            @ApiResponse(code = 401, message = "Unauthorized - the credentials are missing or false"), })
    ResponseEntity delete(@RequestParam @ApiParam(value = "The uuid of the page that shall be deleted", required = true, example = "2") String uuid) {
        if (existsUser()) {
            if (uuid != null) {
                try {
                    int uuidNumb = Integer.parseInt(uuid);
                    val pages = pageService.delete(uuidNumb);
                    return ResponseEntity.ok(pages);
                } catch (NumberFormatException | BooklyException fbe) {
                    log.warn(fbe.getMessage());
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(fbe.getMessage());
                }
            }
            log.warn("Couldn't delete page as the uuid is missing");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
