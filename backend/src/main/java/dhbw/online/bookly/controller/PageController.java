

package dhbw.online.bookly.controller;

import dhbw.online.bookly.dto.Page;
import dhbw.online.bookly.dto.User;
import dhbw.online.bookly.exception.BooklyException;
import dhbw.online.bookly.exception.PageException;
import dhbw.online.bookly.service.PageService;
import dhbw.online.bookly.service.UserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
@RequestMapping("/api/page")
public class PageController extends Controller {
    @Autowired
    private PageService pageService;

    @PostMapping
    @ApiOperation(value = "Add a new page with data into the users friendship book")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success - returns list of pages", response = Page.class),
            @ApiResponse(code = 409, message = "Conflict - the content or user couldn't be found"),
            @ApiResponse(code = 401, message = "Unauthorized - the credentials are missing or false"), })
    ResponseEntity create() {
        if (existsUser()) {
            try {
                val pages = pageService.add();
                return ResponseEntity.ok(pages);
            } catch (BooklyException fbe) {
                log.warn(fbe.getMessage());
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

    @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    @ApiOperation(value = "Returns image of a page")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success - returns the image of the page, returns byte array", response = byte[].class),
            @ApiResponse(code = 404, message = "Not found - the image doesn't exist"), @ApiResponse(code = 409, message = "conflict - missing or wrong uuid"),
            @ApiResponse(code = 401, message = "Unauthorized - the credentials are missing or false"), })
    public ResponseEntity<byte[]> getImage(@RequestParam String uuid) throws IOException {
        try {
            Page page = pageService.findPageByUserAndId(Integer.parseInt(uuid));
            if (existsPageImage(page)) {
                return ResponseEntity.ok().contentType(MediaType.valueOf(page.getPageImage().getMediaType())).body(page.getPageImage().getData());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (NumberFormatException | BooklyException nfe) {
            log.warn(nfe.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping(value = "/image")
    @ResponseBody
    @ApiOperation(value = "Send a new image for a page")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 409, message = "Conflict - the saving of the data failed maybe there was corrupted data"),
            @ApiResponse(code = 401, message = "Unauthorized - the credentials are missing or false"), })
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam String uuid) {
        try {
            if (file == null) {
                throw new PageException("There was no picture in the request for saving.");
            }
            if (existsUser()) {
                Page page = pageService.findPageByUserAndId(Integer.parseInt(uuid));
                if (existsPage(page)) {
                    pageService.saveImageForPage(page, file);
                }
            }
        } catch (BooklyException | NumberFormatException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    @ApiOperation(value = "Update a page. It's important to send the uuid of the page for this request.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success - returns list of the current pages", response = Page.class),
            @ApiResponse(code = 409, message = "Conflict - the updatable page or user couldn't be found"),
            @ApiResponse(code = 401, message = "Unauthorized - the credentials are missing or false"), })
    ResponseEntity update(@RequestBody Page page) {
        if (existsUser() && existsPage(page)) {
            try {
                val pages = pageService.update(page);
                return ResponseEntity.ok(pages);
            } catch (BooklyException fbe) {
                log.warn(fbe.getMessage());
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }
        log.warn("Couldn't update page for user");
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

