package dhbw.online.bookly.controller;

import dhbw.online.bookly.dto.Page;
import dhbw.online.bookly.exception.BooklyException;
import dhbw.online.bookly.exception.PageException;
import dhbw.online.bookly.service.PageService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api/public/page")
public class EditPageController extends Controller {

    @Autowired
    private PageService pageService;

    @GetMapping
    @ApiOperation(value = "Returns a single page, accessible for everyone.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success - returns the public page", response = byte[].class),
            @ApiResponse(code = 404, message = "Not found - the page doesn't exist"), @ApiResponse(code = 409, message = "conflict - missing or wrong uuid") })
    public ResponseEntity<Page> getPublicPage(@RequestParam String uuid) {
        try {
            Page page = pageService.findPageById(uuid);
            return ResponseEntity.ok(page);
        } catch (NumberFormatException nfe) {
            log.warn(nfe.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (PageException nfe) {
            log.warn(nfe.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    @ApiOperation(value = "Update a page with its uuid, accessible for everyone")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success - returns list of the current pages"),
            @ApiResponse(code = 409, message = "Conflict - the updatable page or user couldn't be found"),
            @ApiResponse(code = 401, message = "Unauthorized - the credentials are missing or false"), })
    public ResponseEntity update(@RequestBody Page page) {
        if (existsPage(page)) {
            boolean status = pageService.update(page);
            if (status) {
                return ResponseEntity.ok().build();
            }
        }
        log.warn("Couldn't update page for user");
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    @ApiOperation(value = "Returns the image of a specific page by its uuid.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success - returns the image of the page, returns byte array", response = byte[].class),
            @ApiResponse(code = 404, message = "Not found - the image doesn't exist"), @ApiResponse(code = 409, message = "conflict - missing or wrong uuid"),
            @ApiResponse(code = 401, message = "Unauthorized - the credentials are missing or false"), })
    public ResponseEntity<String> getImage(@RequestParam String uuid) throws IOException {
        try {
            Page page = pageService.findPageById(uuid);
            if (existsPageImage(page)) {
                return ResponseEntity.ok().contentType(MediaType.valueOf(page.getPageImage().getMediaType())).body(encodeBase64(page.getPageImage().getData()));
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
    @ApiOperation(value = "Send a new image for the page by sending a file and sending the specific uuid.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 409, message = "Conflict - the saving of the data failed maybe there was corrupted data"),
            @ApiResponse(code = 401, message = "Unauthorized - the credentials are missing or false"), })
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file, @RequestParam String uuid) {
        try {
            if (file == null) {
                throw new PageException("There was no picture in the request for saving.");
            }
            Page page = pageService.findPageById(uuid);
            if (existsPage(page)) {
                pageService.saveImageForPage(page, file);
            }
        } catch (BooklyException | NumberFormatException e) {
            log.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
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
    public ResponseEntity uploadSticker(@RequestParam("file") MultipartFile file,@RequestParam String uuid, @PathVariable int stickerNumber) {
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
    public ResponseEntity deleteSticker(@RequestParam String uuid, @PathVariable int stickerNumber) {
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
