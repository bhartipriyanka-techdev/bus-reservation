package org.springboot.busreservationapi.controller;
import jakarta.validation.Valid;
import org.springboot.busreservationapi.dto.ResponseStructure;
import org.springboot.busreservationapi.dto.UserRequest;
import org.springboot.busreservationapi.dto.UserResponse;
import org.springboot.busreservationapi.model.User;
import org.springboot.busreservationapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST controller for managing user-related operations.
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Adds a new user.
     *
     * @param userRequest the user request containing user details
     * @return a response entity containing the added user and a response structure
     */
    @PostMapping
    public ResponseEntity<ResponseStructure<UserResponse>> addUser(@RequestBody UserRequest userRequest) {
        return userService.addUser(userRequest);
    }

    /**
     * Finds a user by their ID.
     *
     * @param userId the ID of the user to find
     * @return a response entity containing the found user and a response structure
     */
    @GetMapping("/{userId}")
    public ResponseEntity<ResponseStructure<UserResponse>> findUserById(@PathVariable int userId) {
        return userService.findUserById(userId);
    }

    /**
     * Finds all users.
     *
     * @return a response entity containing the list of all users and a response structure
     */
    @GetMapping
    public ResponseEntity<ResponseStructure<List<UserResponse>>> findAllUsers() {
        return userService.findAllUsers();
    }

    /**
     * Updates an existing user.
     *
     * @param userId      the ID of the user to update
     * @param updatedUser the updated user details
     * @return a response entity containing the updated user and a response structure
     */
    @PutMapping("/{userId}")
    public ResponseEntity<ResponseStructure<UserResponse>> updateUser(@Valid @PathVariable int userId, @RequestBody User updatedUser) {
        return userService.updateUser(userId, updatedUser);
    }

    /**
     * Deletes a user by their ID.
     *
     * @param userId the ID of the user to delete
     * @return a response entity containing a confirmation message and a response structure
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<ResponseStructure<String>> deleteUser(@PathVariable int userId) {
        return userService.deleteUser(userId);
    }

    /**
     * Verifies a user by their email and password.
     *
     * @param email    the email of the user
     * @param password the password of the user
     * @return a response entity containing the verified user and a response structure
     */
    @PostMapping("/verify/email")
    public ResponseEntity<ResponseStructure<UserResponse>> verifyUserByEmailAndPassword(@RequestParam String email, @RequestParam String password) {
        return userService.verifyUserByEmailAndPassword(email, password);
    }

    /**
     * Verifies a user by their phone number and password.
     *
     * @param phone    the phone number of the user
     * @param password the password of the user
     * @return a response entity containing the verified user and a response structure
     */
    @PostMapping("/verify/phone")
    public ResponseEntity<ResponseStructure<UserResponse>> verifyUserByPhoneAndPassword(@RequestParam long phone, @RequestParam String password) {
        return userService.verifyUserByPhoneAndPassword(phone, password);
    }
}
