package dhbw.online.bookly.controller;

import dhbw.online.bookly.dto.User;
import dhbw.online.bookly.exception.BooklyException;
import dhbw.online.bookly.service.DeleteAccountService;
import dhbw.online.bookly.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/api/user")
public class UserController extends Controller {

    @Autowired
    private UserService userService;
    @Autowired
    private DeleteAccountService deleteAccountService;

    @GetMapping
    @ApiOperation(value = "Returns the user data of the currently logged in user.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success - reads user data from db", response = User.class),
            @ApiResponse(code = 409, message = "Conflict - the user couldn't be found or something critical happened"),
            @ApiResponse(code = 401, message = "Unauthorized - the credentials are missing or false"), })
    public ResponseEntity read() {
        User user = userService.getUser();
        try {
            if (existsUser()) {
                return ResponseEntity.ok(user);
            }
        } catch (BooklyException ue) {
            log.warn(ue.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @PostMapping
    @ApiOperation(value = "Update the logged in user profile (not the password or username)")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 409, message = "Conflict - the user couldn't be updated"),
            @ApiResponse(code = 401, message = "Unauthorized - the credentials are missing or false"), })
    public ResponseEntity updateProfile(@RequestBody User user) {
        try {
            userService.update(user);
            log.debug("Updated the user {}", user.getUsername());
        } catch (BooklyException e) {
            ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @ApiOperation(value = "Delete the logged in user profile including the friendship book, images and pages")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 409, message = "Conflict - the user couldn't be deleted and logged out."),
            @ApiResponse(code = 401, message = "Unauthorized - the credentials are missing or false"), })
    public ResponseEntity deleteProfile(HttpServletRequest request) {
        User user = userService.getUser();
        deleteAccountService.deleteAll();
        try {
            request.logout();
            log.debug("Logged out the user {}", user.getUsername());
        } catch (ServletException e) {
            ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok().build();
    }

}
