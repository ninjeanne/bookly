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
    ResponseEntity read() {
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
    @ApiOperation(value = "Update user profile (not the password or username)")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 409, message = "Conflict - the user couldn't be updated"),
            @ApiResponse(code = 401, message = "Unauthorized - the credentials are missing or false"), })
    ResponseEntity updateProfile(@RequestBody User user) {
        userService.update(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @ApiOperation(value = "Delete the user profile including the friendship book, images and pages")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 409, message = "Conflict - the user couldn't be updated"),
            @ApiResponse(code = 401, message = "Unauthorized - the credentials are missing or false"), })
    ResponseEntity deleteProfile() {
        deleteAccountService.deleteAndLogout();
        return ResponseEntity.ok().build();
    }

}
