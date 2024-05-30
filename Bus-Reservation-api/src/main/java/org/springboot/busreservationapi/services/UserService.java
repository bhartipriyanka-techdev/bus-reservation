package org.springboot.busreservationapi.services;
import org.springboot.busreservationapi.Exception.AdminNotFoundException;
import org.springboot.busreservationapi.Exception.UserNotFoundException;
import org.springboot.busreservationapi.dao.UserDao;
import org.springboot.busreservationapi.dto.ResponseStructure;
import org.springboot.busreservationapi.dto.UserRequest;
import org.springboot.busreservationapi.dto.UserResponse;
import org.springboot.busreservationapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing User operations.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * Maps a UserRequest to a User entity.
     *
     * @param userRequest the user request containing user details
     * @return the User entity
     */
    private User mapToUser(UserRequest userRequest) {
        return User.builder()
                .userName(userRequest.getUserName())
                .userEmail(userRequest.getUserEmail())
                .userAge(userRequest.getUserAge())
                .userGender(userRequest.getUserGender())
                .userPhoneNo(userRequest.getUserPhoneNo())
                .userPassword(userRequest.getUserPassword())
                .build();
    }

    /**
     * Maps a User entity to a UserResponse DTO.
     *
     * @param user the user entity
     * @return the UserResponse DTO
     */
    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .userName(user.getUserName())
                .userAge(user.getUserAge())
                .userEmail(user.getUserEmail())
                .userGender(user.getUserGender())
                .userPhoneNo(user.getUserPhoneNo())
                .userPassword(user.getUserPassword())
                .build();
    }

    /**
     * Adds a new user.
     *
     * @param userRequest the user request containing user details
     * @return a response entity containing the added user and a response structure
     */
    public ResponseEntity<ResponseStructure<UserResponse>> addUser(UserRequest userRequest) {
        ResponseStructure<UserResponse> responseStructure = new ResponseStructure<>();
        User addedUser = userDao.addUser(mapToUser(userRequest));
        responseStructure.setMessage("User Added Successfully!");
        responseStructure.setStatusCode(HttpStatus.CREATED.value());
        responseStructure.setData(mapToUserResponse(addedUser));
        return ResponseEntity.status(HttpStatus.CREATED).body(responseStructure);
    }

    /**
     * Finds a user by their ID.
     *
     * @param userId the ID of the user to find
     * @return a response entity containing the found user and a response structure
     * @throws AdminNotFoundException if the user is not found
     */
    public ResponseEntity<ResponseStructure<UserResponse>> findUserById(int userId) {
        ResponseStructure<UserResponse> responseStructure = new ResponseStructure<>();
        Optional<User> rcvUser = userDao.findUserById(userId);
        if (rcvUser.isPresent()) {
            responseStructure.setStatusCode(HttpStatus.OK.value());
            responseStructure.setData(mapToUserResponse(rcvUser.get()));
            return ResponseEntity.status(HttpStatus.OK).body(responseStructure);
        }
        throw new AdminNotFoundException("Please Check, You entered Invalid User Id");
    }

    /**
     * Finds all users.
     *
     * @return a response entity containing the list of all users and a response structure
     */
    public ResponseEntity<ResponseStructure<List<UserResponse>>> findAllUsers() {
        ResponseStructure<List<UserResponse>> structure = new ResponseStructure<>();
        List<User> users = userDao.findAll();
        List<UserResponse> userResponses = users.stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
        structure.setData(userResponses);
        structure.setMessage("List of All Users");
        structure.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(structure);
    }

    /**
     * Updates an existing user.
     *
     * @param userId the ID of the user to update
     * @param updatedUser the updated user details
     * @return a response entity containing the updated user and a response structure
     * @throws UserNotFoundException if the user is not found
     */
    public ResponseEntity<ResponseStructure<UserResponse>> updateUser(int userId, User updatedUser) {
        ResponseStructure<UserResponse> responseStructure = new ResponseStructure<>();
        Optional<User> existingUserOptional = userDao.findUserById(userId);

        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            // Update the fields of the existing user with the new values
            existingUser.setUserName(updatedUser.getUserName());
            existingUser.setUserAge(updatedUser.getUserAge());
            existingUser.setUserEmail(updatedUser.getUserEmail());
            existingUser.setUserGender(updatedUser.getUserGender());
            existingUser.setUserPhoneNo(updatedUser.getUserPhoneNo());
            existingUser.setUserPassword(updatedUser.getUserPassword());

            // Save the updated user back to the database
            User savedUser = userDao.updateUser(existingUser);

            responseStructure.setMessage("User Updated Successfully");
            responseStructure.setStatusCode(HttpStatus.OK.value());
            responseStructure.setData(mapToUserResponse(savedUser));
            return ResponseEntity.status(HttpStatus.OK).body(responseStructure);
        }
        throw new UserNotFoundException("Please Check, You entered Invalid User Id");
    }

    /**
     * Deletes a user by their ID.
     *
     * @param userId the ID of the user to delete
     * @return a response entity containing a confirmation message and a response structure
     * @throws UserNotFoundException if the user is not found
     */
    public ResponseEntity<ResponseStructure<String>> deleteUser(int userId) {
        ResponseStructure<String> structure = new ResponseStructure<>();
        Optional<User> rcvUser = userDao.findUserById(userId);
        if (rcvUser.isPresent()) {
            structure.setData("User Found");
            structure.setMessage("User deleted Successfully");
            structure.setStatusCode(HttpStatus.OK.value());
            userDao.delete(userId);
            return ResponseEntity.status(HttpStatus.OK).body(structure);
        }
        throw new UserNotFoundException("Please Check, You entered Invalid User Id");
    }

    /**
     * Verifies a user by their email and password.
     *
     * @param email the email of the user
     * @param password the password of the user
     * @return a response entity containing the verified user and a response structure
     * @throws UserNotFoundException if the user is not found
     */
    public ResponseEntity<ResponseStructure<UserResponse>> verifyUserByEmailAndPassword(String email, String password) {
        ResponseStructure<UserResponse> responseStructure = new ResponseStructure<>();
        Optional<User> rcvUser = userDao.verifyUserByEmailAndPassword(email, password);
        if (rcvUser.isPresent()) {
            responseStructure.setMessage("User Found");
            responseStructure.setStatusCode(HttpStatus.OK.value());
            responseStructure.setData(mapToUserResponse(rcvUser.get()));
            return ResponseEntity.status(HttpStatus.OK).body(responseStructure);
        }
        throw new UserNotFoundException("Please Check, You entered Invalid User Email or Password");
    }

    /**
     * Verifies a user by their phone number and password.
     *
     * @param phone the phone number of the user
     * @param password the password of the user
     * @return a response entity containing the verified user and a response structure
     * @throws UserNotFoundException if the user is not found
     */
    public ResponseEntity<ResponseStructure<UserResponse>> verifyUserByPhoneAndPassword(long phone, String password) {
        ResponseStructure<UserResponse> responseStructure = new ResponseStructure<>();
        Optional<User> rcvUser = userDao.verifyUserByPhoneAndPassword(phone, password);
        if (rcvUser.isPresent()) {
            responseStructure.setMessage("User Found");
            responseStructure.setStatusCode(HttpStatus.OK.value());
            responseStructure.setData(mapToUserResponse(rcvUser.get()));
            return ResponseEntity.status(HttpStatus.OK).body(responseStructure);
        }
        throw new UserNotFoundException("Please Check, You entered Invalid User Phone or Password");
    }
}
