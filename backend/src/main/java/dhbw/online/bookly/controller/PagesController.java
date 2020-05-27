

package dhbw.online.bookly.controller;

import dhbw.online.bookly.dto.Page;
import dhbw.online.bookly.exception.AuthenticationException;
import dhbw.online.bookly.exception.BooklyException;
import dhbw.online.bookly.exception.PageException;
import dhbw.online.bookly.service.FriendshipBookService;
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
    ResponseEntity createPage() {
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
    ResponseEntity readPage() {
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
    ResponseEntity deletePage(@RequestParam @ApiParam(value = "The uuid of the page that shall be deleted", required = true, example = "2") String uuid) {
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
    ResponseEntity deletePage() {
        pageService.deleteAllPages();
        return ResponseEntity.ok().build();
    }


    @GetMapping(value = "/sticker/{stickerNumber}")
    @ApiOperation(value = "Returns page sticker")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success - returns the sticker of the logged in user, returns byte array",
            response = byte[].class), @ApiResponse(code = 404, message = "Not found - the sticker doesn't exist"),
            @ApiResponse(code = 409, message = "conflict - missing or wrong uuid") })
    public ResponseEntity<String> getSticker(@RequestParam String uuid,@PathVariable int stickerNumber) {
        Page page =pageService.findPageById(uuid);
        if (stickerNumber == 1 && page.getPageStickerOne() != null) {
            return ResponseEntity.ok().contentType(MediaType.valueOf(page.getPageStickerOne().getMediaType())).body(encodeBase64(page.getPageStickerOne().getData()));
        }
        if (stickerNumber == 2 && page.getPageStickerTwo() != null){
            return ResponseEntity.ok().contentType(MediaType.valueOf(page.getPageStickerTwo().getMediaType())).body(encodeBase64(page.getPageStickerTwo().getData()));
        }
        if (stickerNumber == 3 && page.getPageStickerThree() != null){
            return ResponseEntity.ok().contentType(MediaType.valueOf(page.getPageStickerThree().getMediaType())).body(encodeBase64(page.getPageStickerThree().getData()));
        }
        if (stickerNumber == 4 && page.getPageStickerFour() != null){
            return ResponseEntity.ok().contentType(MediaType.valueOf(page.getPageStickerFour().getMediaType())).body(encodeBase64(page.getPageStickerFour().getData()));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/sticker/{stickerNumber}")
    @ResponseBody
    @ApiOperation(value = "Send a new page sticker")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 409, message = "Conflict - the saving of the data failed maybe there was corrupted data")})
    public ResponseEntity<?> uploadSticker(@RequestParam("file") MultipartFile file,@RequestParam String uuid, @PathVariable int stickerNumber) {
        try {
            if (file == null) {
                throw new PageException("There was no sticker in the request for saving.");
            }
            Page page=pageService.findPageById(uuid);
            pageService.saveSticker(page, file, stickerNumber);
        } catch (BooklyException e) {
            log.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/sticker/{stickerNumber}")
    @ResponseBody
    @ApiOperation(value = "Deletes the sticker with a specific number for the page")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 409, message = "Conflict - the saving of the data failed maybe there was corrupted data")})
    public ResponseEntity<?> deleteSticker(@RequestParam String uuid, @PathVariable int stickerNumber) {
        try {
            Page page=pageService.findPageById(uuid);
            pageService.deleteSticker(page, stickerNumber);

        } catch (BooklyException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private String encodeBase64(byte[] data) {
        byte[] encoded = Base64.getEncoder().encode(data);
        return new String(encoded);
    }

}

