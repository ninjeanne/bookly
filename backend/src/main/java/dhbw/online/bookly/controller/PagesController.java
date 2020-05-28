

package dhbw.online.bookly.controller;

import dhbw.online.bookly.dto.Page;
import dhbw.online.bookly.exception.AuthenticationException;
import dhbw.online.bookly.exception.BooklyException;
import dhbw.online.bookly.exception.PageException;
import dhbw.online.bookly.service.PageService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api/page")
public class PagesController extends Controller {

    @Autowired
    private PageService pageService;

    @PostMapping
    @ApiOperation(value = "Add a new page for the logged in user and return it with its uuid.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success - returns list of pages", response = Page.class),
            @ApiResponse(code = 409, message = "Conflict - the content or user couldn't be found"),
            @ApiResponse(code = 401, message = "Unauthorized - the credentials are missing or false"), })
    public ResponseEntity createPage() {
        if (existsUser()) {
            try {
                val newpage = pageService.addNewPage();
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
    @ApiOperation(value = "Returns explicit all pages of the book of the logged in user")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success - returns list of pages", response = Page.class),
            @ApiResponse(code = 409, message = "Conflict - the content or user couldn't be found"),
            @ApiResponse(code = 401, message = "Unauthorized - the credentials are missing or false"), })
    public ResponseEntity readPage() {
        if (existsUser()) {
            val pages = pageService.readPages();
            return ResponseEntity.ok(pages);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @DeleteMapping
    @ApiOperation(value = "Delete one page by its uuid. This action can only be done by the logged in user/owner.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success - returns list of the current pages", response = Page.class),
            @ApiResponse(code = 409, message = "Conflict - the page couldn't be found and deleted"),
            @ApiResponse(code = 401, message = "Unauthorized - the credentials are missing or false"), })
    public ResponseEntity deletePage(@RequestParam @ApiParam(value = "The uuid of the page that shall be deleted", required = true, example = "2") String uuid) {
        if (existsUser()) {
            if (uuid != null) {
                try {
                    val pages = pageService.delete(uuid);
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

    @DeleteMapping("/all")
    @ApiOperation(value = "Deletes all pages of the book of the currently logged in user")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 409, message = "Conflict - the deleting failed"),
            @ApiResponse(code = 401, message = "Unauthorized - the credentials are missing or false"), })
    public ResponseEntity deletePage() {
        pageService.deleteAllPages();
        return ResponseEntity.ok().build();
    }


    private String encodeBase64(byte[] data) {
        byte[] encoded = Base64.getEncoder().encode(data);
        return new String(encoded);
    }

}

