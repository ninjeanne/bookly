package dhbw.online.bookly.controller;

import dhbw.online.bookly.dto.User;
import dhbw.online.bookly.exception.BooklyException;
import dhbw.online.bookly.service.UserService;
import io.swagger.annotations.*;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @ApiOperation(value = "Returns the user data of the currently logged in user.", authorizations = {@Authorization(value = "basicAuth")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - reads user data from db", response = User.class),
            @ApiResponse(code = 409, message = "Conflict - the user couldn't be found or something critical happened"),
            @ApiResponse(code = 401, message = "Unauthorized - the credentials are missing or false"),
    })
    ResponseEntity read() {
        try {
            return ResponseEntity.ok(userService.getUser());
        } catch (BooklyException ue) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ue.getMessage());
        }
    }

    @PutMapping
    @ApiOperation(value = "Update the user data of the currently logged in user. The username can't be be changed.", authorizations = {@Authorization(value = "basicAuth")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success - returns updated user", response = User.class),
            @ApiResponse(code = 409, message = "Conflict - the user couldn't be found or something critical happened"),
            @ApiResponse(code = 401, message = "Unauthorized - the credentials are missing or false"),
    })
    ResponseEntity update(@RequestBody @ApiParam(value = "Updatable user as JSON", required = true) User user) {
        if (user != null) {
            try {
                val updatedUser = userService.update(user);
                return ResponseEntity.ok(updatedUser);
            } catch (BooklyException ue) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(ue.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

}