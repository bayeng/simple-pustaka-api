package com.kunam.pustaka.user;

import com.kunam.pustaka.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<Object> findAllUsers() {
        try {
            List<User> users = userService.findAllUsers();

            if (users.isEmpty()) {
                return new ResponseEntity<>(
                        new ApiResponse<>("error", "No users found", null),
                        HttpStatus.NOT_FOUND
                );
            }

            return new ResponseEntity<>(
                    new ApiResponse<>("success", "Found " + users.size() + " users", users),
                    HttpStatus.OK
            );
        } catch (Exception e) {

            return new ResponseEntity<>(
                    new ApiResponse<>("error", e.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findUserById(@PathVariable Integer id) {
        try {
            User user = userService.findUserById(id);

            if (user == null) {
                return new ResponseEntity<>(
                        new ApiResponse<>("error", "User not found", null),
                        HttpStatus.NOT_FOUND
                );
            }

            return new ResponseEntity<>(
                    new ApiResponse<>("success", "Found user", user),
                    HttpStatus.OK
            );

        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ApiResponse<>("error", e.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostMapping("")
    public ResponseEntity<Object> saveUser(@RequestBody User user) {
        try {
            User result = userService.saveUser(user);

            return new ResponseEntity<>(
                    new ApiResponse<>(
                            "success",
                            "User created successfully",
                            result
                    ), HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ApiResponse<>("error", e.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable Integer id) {
        try {
            User result = userService.updateUser(user, id);
            return new ResponseEntity<>(
                    new ApiResponse<>("success", "User updated successfully", result),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ApiResponse<>("error", e.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Integer id) {

        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(
                    new ApiResponse<>("success", "User deleted successfully", null),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ApiResponse<>("error", e.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
